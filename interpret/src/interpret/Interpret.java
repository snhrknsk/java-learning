package interpret;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Interpret {

	protected final static String packageName = "interpret.";

	public static void main(String[] args) {
		new HomeUI();
	}

	public static Class<?> searchClass(String className) throws ClassNotFoundException {

		Class<?> searchedClazz;
		try {
			String interpretPackageQuery = className;
			if (!interpretPackageQuery.contains(packageName)) {
				interpretPackageQuery = packageName + interpretPackageQuery;
			}
			searchedClazz = Class.forName(interpretPackageQuery);
		} catch (Exception e) {
			String query = className;
			if (!query.contains(".")) {
				query = "java.lang." + className;
			}
			searchedClazz = Class.forName(query);
		}
		return searchedClazz;
	}

	public static <T> T createObj(Constructor<T> constructor, Object... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (args == null) {
			return constructor.newInstance();
		}
		return constructor.newInstance(args);
	}

	public static <T> Object createArray(Class<?> clazz, int num) {
		return Array.newInstance( clazz, num);
	}

	public static String trimPackage(String target) {
//		String result;
//		result = target.replaceAll("java.lang.", "");
//		result = result.replaceFirst(Interpret.packageName, "");
		String[] temp = target.split("\\.");
		return temp[temp.length - 1];
	}

	public static String trimException(String target) {
		return target.split("throws")[0];
	}

	public static String trimModifier(String target) {
		String result;
		result = target.replaceAll("public", "").replaceAll("protected", "").replaceAll("private", "");
		result = result.replaceAll("static", "");
		return result;
	}

}
