package ch16.ex03;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

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
		Set<Member> set = new LinkedHashSet<>();
		set.addAll(Arrays.asList(c.getDeclaredFields()));
		set.addAll(Arrays.asList(c.getFields()));
		set.addAll(Arrays.asList(c.getDeclaredConstructors()));
		set.addAll(Arrays.asList(c.getConstructors()));
		set.addAll(Arrays.asList(c.getDeclaredMethods()));
		set.addAll(Arrays.asList(c.getMethods()));
		printMembers(set.toArray(new Member[0]));
	}

	public static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static String strip(String word, String regex) {
		word = word.replaceAll(regex, "");
		return word;

	}
}
