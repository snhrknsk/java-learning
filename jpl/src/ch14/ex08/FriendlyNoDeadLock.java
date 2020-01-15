package ch14.ex08;

public class FriendlyNoDeadLock {
	private FriendlyNoDeadLock partner;
	private String name;
	private static Object lock = new Object();

	public FriendlyNoDeadLock(String name) {
		this.name = name;
	}

	public void hug() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " in " + name + ".hug() trying to invoke " + partner.name + ".hugBack");
			partner.hugBack();
		}
	}

	private void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
	}

	private void becomeFriend(FriendlyNoDeadLock partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		FriendlyNoDeadLock jareth = new FriendlyNoDeadLock("jareth");
		FriendlyNoDeadLock cory = new FriendlyNoDeadLock("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		new Thread(new Runnable() {

			@Override
			public void run() {
				jareth.hug();
			}
		}, "Thread1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				cory.hug();
			}
		},"Thread2").start();;
	}
}
