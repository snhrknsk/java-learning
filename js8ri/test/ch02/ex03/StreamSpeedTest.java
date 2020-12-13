package ch02.ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class StreamSpeedTest {

	private static String path = "./test/ch02/ex03/LongText.txtk";
	// "./test/ch02/ex03/LongText.txt" //Eclipse上でのテスト実行パス

	@Test
	void test() {

		try {
			String words = new String(Files.readAllBytes(Paths.get(path)));
			// Stream利用
			long streamResult = StreamSpeed.measureWordCountSpeed(Pattern.compile("[\\P{L}]+").splitAsStream(words));
			System.out.println("Sequential:\t" + streamResult + "[ns]" );

			// Parallel Stream利用
			long parallelStreamResult = StreamSpeed.measureWordCountSpeed(Pattern.compile("[\\P{L}]+").splitAsStream(words));
			System.out.println("Parallel:\t" + parallelStreamResult + "[ns]" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
