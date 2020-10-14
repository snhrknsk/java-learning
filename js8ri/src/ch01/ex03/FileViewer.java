package ch01.ex03;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileViewer {

	public static List<String> getAllFile(File file, String extension) {
		List<String> result = new ArrayList<>();
		// extensionがエンクロージングからキャプチャされる変数(ラムダ式内で定義していない)
		// extensionは実質final,ここでextension = ".jpeg";を記載したらエラー
		result.addAll(Arrays.asList(file.list((f, s)->s.endsWith(extension))));
		return result;
	}
}
