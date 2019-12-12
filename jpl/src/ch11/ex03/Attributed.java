package ch11.ex03;

public interface Attributed<E> {

	void add(Attr<E> newAttr);

	Attr<E> find(String attrName);

	Attr<E> remove(String attrName);

	java.util.Iterator<Attr<E>> attrs();

	class Attr<E> {
		private final String name;
		private E value = null;

		public Attr(String name) {
			this.name = name;
		}

		public Attr(String name, E value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}

		public Object setValue(E newValue) {
			Object oldVal = value;
			value = newValue;
			return oldVal;
		}

		public String toString() {
			return name + "='" + value + "'";
		}

	}
}
