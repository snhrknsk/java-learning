package ch14.ex08;

public class FriendlyYield {
	private FriendlyYield partner;
	private String name;

	public FriendlyYield(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hug() trying to invoke " + partner.name + ".hugBack");
		Thread.yield();
		partner.hugBack();
	}

	private synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
	}

	private void becomeFriend(FriendlyYield partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		FriendlyYield jareth = new FriendlyYield("jareth");
		FriendlyYield cory = new FriendlyYield("cory");

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
