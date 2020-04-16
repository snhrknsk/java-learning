package ch20.ex06;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import org.junit.Test;


public class OperationTest {

	@Test
	public void test() {
		File file = new File("./test/ch20/ex06/Test.txt");
		Double expected = 3.0;
		Operation operation = new Operation();
		try (FileReader fileReader = new FileReader(file)){
			operation.calc(fileReader);
			assertEquals(expected, operation.getResult("bar"));
			expected = 5.0;
			assertEquals(expected, operation.getResult("hoge"));
			expected = -5.0;
			assertEquals(expected, operation.getResult("foo"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = InvalidPropertiesFormatException.class)
	public void exceptionTest() throws InvalidPropertiesFormatException {
		File file = new File("./test/ch20/ex06/Test2.txt");
		Operation operation = new Operation();
		try (FileReader fileReader = new FileReader(file)){
			operation.calc(fileReader);
			operation.disResult();
		} catch (IOException e) {
			if (e instanceof InvalidPropertiesFormatException) {
				throw (InvalidPropertiesFormatException)e;
			}
		}
	}

}
