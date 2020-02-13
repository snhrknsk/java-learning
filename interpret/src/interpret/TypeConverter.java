package interpret;

public class TypeConverter {

	private enum PrimitiveType{
		TypeInt("int"){
			@Override
			public Object getConvertObj(String value) {
				return Integer.valueOf(value);
			}
		},
		TypeDouble("double"){
			@Override
			public Object getConvertObj(String value) {
				return Double.valueOf(value);
			}
		},
		TypeFloat("float"){
			@Override
			public Object getConvertObj(String value) {
				return Float.valueOf(value);
			}
		},
		TypeByte("byte"){
			@Override
			public Object getConvertObj(String value) {
				return Byte.valueOf(value);
			}
		},
		TypeLong("long"){
			@Override
			public Object getConvertObj(String value) {
				return Long.valueOf(value);
			}
		},
		TypeBoolean("boolean"){
			@Override
			public Object getConvertObj(String value) {
				return Boolean.valueOf(value);
			}
		},
		TypeChar("char"){
			@Override
			public Object getConvertObj(String value) {
				return value.toCharArray()[0];
			}
		},
		TypeShort("short"){
			@Override
			public Object getConvertObj(String value) {
				return Short.valueOf(value);
			}
		},
		TypeString("class java.lang.String"){
			@Override
			public Object getConvertObj(String value) {
				return value;
			}
		},;//PremitiveではないがStringはそのままOK

		public abstract Object getConvertObj(String value);

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
		return null;
	}


}
