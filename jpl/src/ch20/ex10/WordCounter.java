package ch20.ex10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {

	private Map<String, Integer> treeMap;

	public WordCounter(File file) throws IOException {
		readFile(file);
	}

	public void readFile(File file) throws IOException {

		treeMap = new TreeMap<>(new HashMap<>());
		try (FileReader reader = new FileReader(file)) {
			StreamTokenizer tokenizer = new StreamTokenizer(reader);
			while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
				if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
					String word = tokenizer.sval;
					treeMap.put(word, treeMap.get(word) == null ? 1 : treeMap.get(word) + 1);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public void dispWordCount() {
		for (String key : treeMap.keySet()) {
			System.out.println(key + " : " + treeMap.get(key));
		}
	}

	public int getCount(String word) {
		return treeMap.get(word) == null ? 0 : treeMap.get(word);
	}

}
