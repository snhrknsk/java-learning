package ch10.ex01;

public class ConvertString {

	public String convert(String originalStr) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < originalStr.length(); i++) {
			char c = originalStr.charAt(i);
			if (c == '\n') {
				stringBuilder.append("\\n");
			} else if (c == '\t') {
				stringBuilder.append("\\t");
			} else if (c == '\b') {
				stringBuilder.append("\\b");
			} else if (c == '\"') {
				stringBuilder.append("\\\"");
			} else if (c == '\'') {
				stringBuilder.append("\\\'");
			} else if (c == '\\') {
				stringBuilder.append("\\\\");
			} else if (c == '\f') {
				stringBuilder.append("\\f");
			} else if (c == '\r') {
				stringBuilder.append("\\r");
			} else {
				stringBuilder.append(c);
			}
		}
		return stringBuilder.toString();
	}

	public static void main(String...strings) {
		System.out.println(new ConvertString().convert("\n\t\b\"\'\\\f\r"));
	}
}
