package chap2.excercise2_11;

import chap2.excercise2_5.Vehicle;

public class LinkedList {
	private Object element = null;
	private LinkedList next = null;

	public LinkedList(Object element) {
		this.element = element;
	}

	public Object getElement() {
		return element;
	}

	public LinkedList getNext() {
		return next;
	}

	public void add(Object obj) {
		LinkedList last = getLast();
		last.setNext(obj);
	}

	private LinkedList getLast() {
		LinkedList list = this;
		while(list.next != null){
			list = list.next;
		}
		return list;
	}

	private void setNext(Object obj) {
		next = new LinkedList(obj);
	}

	@Override
	public String toString() {
		LinkedList list = this;
		StringBuilder sb = new StringBuilder("[" + list.getElement());
		while((list = list.getNext()) != null) {
			sb.append("," + list.getElement());
		}
		return sb.append("]").toString();
	}

	public static void main(String[] args) {
		Vehicle vehicle1 = new Vehicle("owner1");
		vehicle1.setDirection(0);
		vehicle1.setSpeed(10);
		Vehicle vehicle2 = new Vehicle("owner2");
		vehicle2.setDirection(180);
		vehicle2.setSpeed(20);
		Vehicle vehicle3 = new Vehicle("owner3");
		vehicle3.setDirection(45);
		vehicle3.setSpeed(40);
		LinkedList list = new LinkedList(vehicle1);
		list.add(vehicle2);
		list.add(vehicle3);

		System.out.println(list.toString());
	}
}
