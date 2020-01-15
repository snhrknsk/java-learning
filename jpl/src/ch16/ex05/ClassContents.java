package ch16.ex05;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields());
			printMembers(c.getConstructors());
			printMembers(c.getMethods());
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class : " + args[0]);
		}
	}

	public static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			if (m instanceof AnnotatedElement) {
				Annotation[] annotations = ((AnnotatedElement) m).getAnnotations();
				for (Annotation annotation : annotations) {
					System.out.print(annotation);
				}
				System.out.println();
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
