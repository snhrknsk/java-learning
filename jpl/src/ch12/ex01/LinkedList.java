package ch12.ex01;

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

	public LinkedList find(Object element) throws ObjectNotFoundException{
		LinkedList targetElement = this;
		while(targetElement != null) {
			if (targetElement.getElement() == element) {
				return targetElement;
			}
			targetElement = targetElement.next;
		}
		throw new ObjectNotFoundException(element);
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
