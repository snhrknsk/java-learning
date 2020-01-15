package ch16.ex04;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

public class AnnotationViewer {

	public static void main(String[] args) {
		for (String name : args) {
			try {
				Class<?> cls = Class.forName(name);
				System.out.println(cls);
				printAnnotations(cls);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
		}
	}

	private static void printAnnotations(Type type) {
		Annotation[] annotations = type instanceof AnnotatedElement ? ((AnnotatedElement) type).getAnnotations() : new Annotation[0];
		for (Annotation annotation : annotations) {
			System.out.println("  " + annotation);
		}
	}
}
