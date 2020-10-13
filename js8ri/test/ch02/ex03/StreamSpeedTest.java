package ch02.ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class StreamSpeedTest {

	private static String path = "./test/ch02/ex03/LongText.txt";// "./LongText.txt";
	// "./test/ch02/ex03/LongText.txt" //Eclipse上でのテスト実行パス

	@Test
	void test() {

		try {
			List<String> words = Arrays.asList(new String(Files.readAllBytes(Paths.get(path))).split("[\\P{L}]+"));
			// Stream利用
			long streamResult = StreamSpeed.measureWordCountSpeed(words.stream());
			System.out.println("Sequential:\t" + streamResult + "[ns]" );

			// Parallel Stream利用
			long parallelStreamResult = StreamSpeed.measureWordCountSpeed(words.parallelStream());
			System.out.println("Parallel:\t" + parallelStreamResult + "[ns]" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
