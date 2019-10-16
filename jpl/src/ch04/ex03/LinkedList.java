package ch04.ex03;


public class LinkedList implements ILinkedList {

	private Object element = null;//変更可,要素は変わってもよい
	private LinkedList next = null;//変更可,リンクは張り替えてよい

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

	public int size() {
		int size = 0;
		LinkedList list = this;
		size++;
		while((list = list.getNext()) != null)
			size++;
		return size;
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

}
