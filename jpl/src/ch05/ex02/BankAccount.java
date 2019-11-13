package ch05.ex02;

public class BankAccount {
	private long number;
	private long balance;
	private History history;

	BankAccount(long number) {
		history = new History(10);
		this.number = number;
	}

	public class Action {
		private String act;
		private long amount;

		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}

		public String toString() {
			return number + ":" + act + " " + amount;
		}
	}

	public static class History implements Cloneable {
		private java.util.LinkedList<Action> actions;
		private int pointer;
		private final int length;

		History(int length) {
			this.length = length;
			actions = new java.util.LinkedList<Action>();
		}

		void add(Action newAction) {
			actions.addFirst(newAction);
			if (actions.size() > length) {
				actions.removeLast();
			}
		}

		public Action next() {
			if (pointer < actions.size()) {
				return actions.get(pointer++);
			} else {
				return null;
			}
		}

		@Override
		protected History clone() {
			History history = null;
			try {
				history = (History) super.clone();
			} catch (CloneNotSupportedException unreachable) {
			}
			return history;
		}
	}

	public History history() {
		return history.clone();
	}

	public void deposit(long amount) {
		balance += amount;
		history.add(new Action("deposit", amount));
	}

	public void withdraw(long amount) {
		balance -= amount;
		history.add(new Action("withdraw", amount));
	}

	public long getBalance() {
		return balance;
	}

}
