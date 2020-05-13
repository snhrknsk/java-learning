package interpret;

import java.lang.reflect.Array;

public class TypeUtil {

	enum PrimitiveType{
		TypeInt("int"){
			@Override
			public Object getConvertObj(String value) {
				return Integer.valueOf(value);
			}

			@Override
			public Class<?> getPrimitiveClass() {
				return int.class;
			}

			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setInt(arrayObject, index, Integer.valueOf(value));
			}
		},
		TypeDouble("double"){
			@Override
			public Object getConvertObj(String value) {
				return Double.valueOf(value);
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return double.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setDouble(arrayObject, index, Double.valueOf(value));
			}
		},
		TypeFloat("float"){
			@Override
			public Object getConvertObj(String value) {
				return Float.valueOf(value);
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return float.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setFloat(arrayObject, index, Float.valueOf(value));
			}
		},
		TypeByte("byte"){
			@Override
			public Object getConvertObj(String value) {
				return Byte.valueOf(value);
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return byte.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setByte(arrayObject, index, Byte.valueOf(value));
			}
		},
		TypeLong("long"){
			@Override
			public Object getConvertObj(String value) {
				return Long.valueOf(value);
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return long.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setLong(arrayObject, index, Long.valueOf(value));
			}
		},
		TypeBoolean("boolean"){
			@Override
			public Object getConvertObj(String value) {
				return Boolean.valueOf(value);
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return long.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setBoolean(arrayObject, index, Boolean.valueOf(value));
			}
		},
		TypeChar("char"){
			@Override
			public Object getConvertObj(String value) {
				return value.toCharArray()[0];
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return char.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setChar(arrayObject, index, value.toCharArray()[0]);
			}
		},
		TypeShort("short"){
			@Override
			public Object getConvertObj(String value) {
				return Short.valueOf(value);
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return char.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				Array.setShort(arrayObject, index, Short.valueOf(value));
			}
		},
		TypeString("class java.lang.String"){
			@Override
			public Object getConvertObj(String value) {
				return value;
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return String.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				throw new UnsupportedOperationException();
			}
		},
		TypeString2("java.lang.String"){
			@Override
			public Object getConvertObj(String value) {
				return value;
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return String.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				throw new UnsupportedOperationException();
			}
		},
		TypeTrimString("String"){
			@Override
			public Object getConvertObj(String value) {
				return value;
			}
			@Override
			public Class<?> getPrimitiveClass() {
				return String.class;
			}
			@Override
			public void setPrimitiveArray(Object arrayObject, int index, String value) {
				throw new UnsupportedOperationException();
			}
		},;//PremitiveではないがStringはそのままOK

		public abstract Object getConvertObj(String value);
		public abstract Class<?> getPrimitiveClass();
		public abstract void setPrimitiveArray(Object arrayObject,int index, String value);

		private String typeName;
		private PrimitiveType(String type) {
			this.typeName = type;
		}
		public String getTypeName() {
			return typeName;
		}
	}

	public static PrimitiveType getPrimitiveType(String name) {
		for (PrimitiveType element : PrimitiveType.values()) {
			if (element.getTypeName().equals(name)) {
				return element;
			}
		}
		return null;
	}

	public static Object convertType(String value, String type) {
		if (InstanceManager.getInstance().getCreatedObject(value) != null) {
			return InstanceManager.getInstance().getCreatedObject(value).getCreatedObject();
		}
		for (PrimitiveType pri : PrimitiveType.values()) {
			if (pri.getTypeName().equals(type)) {
				return pri.getConvertObj(value);
			}
		}
		if (value.equals("null")) {
			return null;
		}
		throw new IllegalArgumentException(type + " is not match(" + value + ")");
	}

	public static boolean isPrimitive(String type) {
		for (PrimitiveType element : PrimitiveType.values()) {
			if (element.getTypeName().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static Class<?> getPrimitiveClass(String type) {
		for (PrimitiveType element : PrimitiveType.values()) {
			if (element.getTypeName().equals(type)) {
				return element.getPrimitiveClass();
			}
		}
		throw new IllegalArgumentException(type + "is not primitive type.");
	}
}
