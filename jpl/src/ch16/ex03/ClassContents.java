package ch16.ex03;

import java.lang.reflect.Member;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printAll(c);
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class : " + args[0]);
		}
	}

	public static void printAll(Class<?> c) {
		printMembers(c.getDeclaredFields());
		printMembers(c.getFields());
		printMembers(c.getDeclaredConstructors());
		printMembers(c.getConstructors());
		printMembers(c.getDeclaredMethods());
		printMembers(c.getMethods());
	}

	public static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(trim(decl, "java.lang."));
		}
	}

	private static String trim(String word, String regex) {
		word = word.replaceAll(regex, "");
		return word;

	}
}
