package ch08.ex12;

import java.lang.reflect.Method;

public class TestMain {

	public static void main(String[] argv) throws Exception {
		Class<?> clazz = TestClass.class;
		for (Method method : clazz.getMethods()) {
			TestCases testCases = method.getAnnotation(TestCases.class);
			if (testCases == null) {
				continue;
			}
			for (TestCase testCase : testCases.value()) {
				System.out.println("Test case: " + testCase.param() + ", Retrun: " + testCase.expected() + " : ");
				try {
					int ret = (Integer)method.invoke(null, testCase.param());
					if (ret == testCase.expected()) {
						System.out.println("OK");
					} else {
						System.out.println("NG, Return" + ret);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
