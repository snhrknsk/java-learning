package chap2.excercise2_2;

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
}
