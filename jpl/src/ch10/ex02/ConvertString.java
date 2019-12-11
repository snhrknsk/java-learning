package ch10.ex02;

public class ConvertString {
	public String convert(String originalStr) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < originalStr.length(); i++) {
			char c = originalStr.charAt(i);
			switch (c) {
			case '\n':
				stringBuilder.append("\\n");
				break;
			case  '\t':
				stringBuilder.append("\\t");
				break;
			case '\b':
				stringBuilder.append("\\b");
				break;
			case '\"':
				stringBuilder.append("\\\"");
				break;
			case '\'':
				stringBuilder.append("\\'");
				break;
			case '\\':
				stringBuilder.append("\\\\");
				break;
			case '\f':
				stringBuilder.append("\\f");
				break;
			case '\r':
				stringBuilder.append("\\r");
				break;
			default:
				stringBuilder.append(c);
				break;
			}
		}
		return stringBuilder.toString();
	}

	public static void main(String...strings) {
		System.out.println("aa\n\r\b\t\\\"");
		System.out.println(new ConvertString().convert("aa\n\r\b\t\\\""));
	}
}
