package ch17.ex03;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	final Thread reaper;
	boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<>();
		refs = new HashMap<>();
		reaper = new ReaperThread();
		reaper.start();
	}

	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
			reaper.interrupt();
		}
	}

	public synchronized Resource getResource(Object key) {
		if (shutdown) {
			throw new IllegalStateException();
		}
		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}


	private static class ResourceImpl implements Resource{

		//Objectで管理
		Object key;
		boolean needRelease= false;

		public ResourceImpl(Object key) {
			this.key = key;
			needRelease = true;
		}

		@Override
		public void use(Object key) {
			if (this.key != key) {
				throw new IllegalArgumentException("wrong key");
			}

		}

		@Override
		public void release() {
			if (needRelease) {
				needRelease = false;
			}
		}
	}

	class ReaperThread extends Thread{
		public void run() {
			while(true) {
				try {
					Reference<?> ref = queue.remove();
					Resource res = null;
					synchronized (ResourceManager.this) {
						res = refs.get(ref);
						refs.remove(ref);
						res.release();
						ref.clear();
					}
				} catch(InterruptedException e) {
					break;
				}
			}
		}
	}
}
