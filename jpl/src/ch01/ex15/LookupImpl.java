package ch01.ex15;

import java.util.Arrays;

public class LookupImpl implements ExtendedLookup {

	private String[] names = new String[0];
	private Object[] values = new Object[0];
	private int size = 0;

	@Override
	public Object find(String name) {
		for (int i = 0; i < size; i++) {
			if (name.equals(names[i])) {
				return values[i];
			}
		}
		return null;
	}

	@Override
	public void add(String name, Object value) {
		if ( find(name) != null) {
			updateValue(name, value);
		} else if (names.length > size) {
			names[size] = name;
			values[size] = value;
			size++;
		} else {
			names = Arrays.copyOf(names, names.length + 1);
			names[names.length - 1] = name;
			values = Arrays.copyOf(values, values.length + 1);
			values[values.length -1] = value;
			size++;
		}
	}

	@Override
	public Object remove(String name) {
		Object removedValue = null;
		for (int i = 0; i < size; i++) {
			if (names[i].equals(name)) {
				removedValue = values[i];
				int numMoved = names.length - i - 1;
				if (numMoved >= 0) {
					System.arraycopy(names, i+1, names, i,numMoved);
		        	System.arraycopy(values, i+1, values, i,numMoved);
		        	names[--size] = null;
			        values[size] = null;
				}
				break;
			}
		}
		return removedValue;
	}

	private void updateValue(String name, Object newValue) {
		for (int i = 0; i < size; i++) {
			if (name.equals(names[i])) {
				values[i] = newValue;
				return;
			}
		}
	}

}
