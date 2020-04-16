package ch19.ex01;

/**
 * ある要素を持つオブジェクト<br>
 * 次の要素への参照を持つ<br>
 * @author ShinoharaKensuke
 *
 */
public class LinkedList {

	private Object element = null;
	private LinkedList next = null;

	/**
	 * {@link element}
	 * @param このインスタンスに持たせる要素
	 */
	public LinkedList(Object element) {
		this.element = element;
	}

	/**
	 * このインスタンスが持つ要素を返す
	 * @return このインスタンスが持つ要素
	 */
	public Object getElement() {
		return element;
	}

	/**
	 * このインスタンスが持つ次の{@link LinkedList}への参照を返す
	 * @return
	 * このインスタンスが持つ次の{@link LinkedList}<br>
	 * 次への参照がない場合はNullを返す
	 */
	public LinkedList getNext() {
		return next;
	}

	/**
	 * このリストの{@link #getLast}を用いて得た最後の{@link LinkedList}インスタンスの参照に、Objectを要素に持つ{@link LinkedLint}を追加する
	 * @param
	 * 追加する{@link LinkedList}の要素
	 */
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

	/**
	 * このリストが持つ要素数を数える<br>
	 * 要素数は現在のインスタンスから{@link #getNext()}を呼び出したどってnullが返ってくるまでの要素数
	 * @return リストの要素数
	 */
	public int size() {
		int size = 1;
		LinkedList list = this;
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
