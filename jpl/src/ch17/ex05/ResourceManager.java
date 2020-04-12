package ch17.ex05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class ResourceManager {

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<>();
		refs = new HashMap<>();
	}

	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
			//Map全てをクリア
			for(Entry<Reference<?>, Resource> entry : refs.entrySet()) {
				refs.remove(entry.getKey());
				entry.getValue().release();
				entry.getKey().clear();
			}
		}
	}

	public synchronized Resource getResource(Object key) {
		if (shutdown) {
			throw new IllegalStateException();
		}
		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		//getResourceをトリガーにリソースを開放
		reap();
		return res;
	}

	/**
	 * ReapThreadの代わりに刈り取るメソッド
	 */
	private void reap() {
		while(true) {
			Reference<?> ref = queue.poll();
			if (ref != null) {
				Resource res = refs.get(ref);
				refs.remove(ref);
				res.release();
				ref.clear();
			} else {
				break;
			}
		}
	}

	private static class ResourceImpl implements Resource{

		Object key;
		boolean needRelease = false;

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

}
