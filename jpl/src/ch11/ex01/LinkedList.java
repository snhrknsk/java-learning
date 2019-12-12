package ch11.ex01;


public class LinkedList<E> {
	private E element = null;
	private LinkedList<E> next = null;

	public LinkedList(E element) {
		this.element = element;
	}

	public Object getElement() {
		return element;
	}

	public LinkedList<E> getNext() {
		return next;
	}

	public void add(E obj) {
		LinkedList<E> last = getLast();
		last.setNext(obj);
	}

	private LinkedList<E> getLast() {
		LinkedList<E> list = this;
		while(list.next != null){
			list = list.next;
		}
		return list;
	}

	private void setNext(E obj) {
		next = new LinkedList<E>(obj);
	}
}
