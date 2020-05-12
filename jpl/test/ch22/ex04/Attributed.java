package ch22.ex04;

import java.util.Iterator;

public interface Attributed {

	public void add(Attr newAttr);

	public Attr find(String attrName);

	public Attr remove(String attrName);

	Iterator<Attr> attrs();

	class Attr {
		private final String name;
		private Object value = null;

		public Attr(String name) {
			this.name = name;
		}

		public Attr(String name, Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}

		public Object setValue(Object newValue) {
			Object oldVal = value;
			value = newValue;
			return oldVal;
		}

		public String toString() {
			return name + "=" + value;
		}
	}
}
