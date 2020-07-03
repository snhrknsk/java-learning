package ch16.ex02;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;


public class TypeDesc {
	public static void main(String[] args) {
		TypeDesc desc = new TypeDesc();
		for (String name : args) {
			try {
				Class<?> startClass = Class.forName(name);
				desc.printType(startClass, 0, basic);
			} catch (ClassNotFoundException e) {
				System.err.println(e);
			}
		}
	}

	private static String[]
			basic = { "class", "interface", "enum", "annotation" },
			supercl = { "extends", "implements" },
			iFace = { null, "extends" };

	private void printType(Type type, int depth, String[] labels) {
		//Objectだったら終了
		if (type == null || type.equals(Object.class)) {
			return;
		}

		Class<?> cls = null;
		if (type instanceof Class<?>) {
			cls= (Class<?>) type;
		} else if (type instanceof ParameterizedType) {
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		} else {
			throw new Error("Unexpected non-class type");
		}

		for (int i = 0; i < depth; i++) {
			System.out.print("  ");
		}
		int kind = cls.isAnnotation() ? 3 :
			cls.isEnum() ? 2 :
			cls.isInterface() ? 1 :
			0;
		System.out.print(labels[kind] + " ");
		System.out.print(cls.getCanonicalName());

		TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			System.out.print("<");
			for (TypeVariable<?> param : params) {
				System.out.print(param.getName());
				System.out.print(", ");
			}
			System.out.println("\b\b>");
		} else {
			System.out.println();
		}

		//ネストしたクラスの表示
		Class<?> nestedClass = cls.getDeclaringClass();
		if (nestedClass != null) {
			System.out.println("  nest in " + nestedClass.getCanonicalName());
		}

		Type[] interfaces = cls.getGenericInterfaces();
		for(Type iface : interfaces) {
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}
		printType(cls.getGenericSuperclass(), depth + 1, supercl);

	}
}
