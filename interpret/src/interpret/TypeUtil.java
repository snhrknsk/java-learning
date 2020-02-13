package interpret;

public class TypeUtil {

	private enum PrimitiveType{
		TypeInt("int"){
			@Override
			public Object getConvertObj(String value) {
				return Integer.valueOf(value);
			}

			@Override
			public Class<?> getPrimitiveClass() {
				return int.class;
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
		},
		TypeString("class java.lang.String"){
			@Override
			public Object getConvertObj(String value) {
				return value;
			}
			@Override
			public Class<?> getPrimitiveClass() {
				throw new UnsupportedOperationException();
			}
		},;//PremitiveではないがStringはそのままOK

		public abstract Object getConvertObj(String value);
		public abstract Class<?> getPrimitiveClass();

		private String typeName;
		private PrimitiveType(String type) {
			this.typeName = type;
		}
		public String getTypeName() {
			return typeName;
		}
	}

	public static Object convertType(String value, String type) {
		if (InstanceManager.getInstance().getCreatedObject(value) != null) {
			System.out.println("InstanceManager : " + value);
			return InstanceManager.getInstance().getCreatedObject(value).getCreatedObject();
		}
		for (PrimitiveType pri : PrimitiveType.values()) {
			if (pri.getTypeName().equals(type)) {
				return pri.getConvertObj(value);
			}
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
