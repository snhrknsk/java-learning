package chap1.exercise1_15;

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
			values[size] = values;
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
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name)) {
				removedValue = values[i];
				int numMoved = names.length - i - 1;
				if (numMoved > 0) {
					System.arraycopy(names, i+1, names, i,numMoved);
		        	System.arraycopy(values, i+1, values, i,numMoved);
		        	names[--size] = null;
			        values[size] = null;
				}
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

//	public static void main(String[] args) {
//
//		LookupImpl look = new LookupImpl();
//		look.add("test", new String("test"));
//		look.add("test2", new String("test2"));
//		look.add("test3", new String("test3"));
//		look.add("test2", new String("test2sub"));
//		System.out.println( (String)look.find("test2"));
//		look.remove("test3");
//		System.out.println((String)look.find("test3"));
//		look.add("test4", new String("test4"));
//		look.add("test5", new String("test5"));
//		System.out.println((String)look.find("test5"));
//	}

}
