package ch13.ex04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TypeObjectGenerator {

	private final File file;
	private final List<Object> list = new ArrayList<>();

	public TypeObjectGenerator(String filePath) {
		this.file = new File(filePath);
	}

	/**
	 * ファイルの１行に記載のあるオブジェクトを生成しオブジェクトのリストを作成する<br>
	 * 記載方法が"type value"の形式になっていない、もしくはtypeの型に合わないvalueが記載されている場合は無視される
	 */
	public List<Object> create() {
		try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
			String line;
			while((line = reader.readLine()) != null) {
				String[] strs = line.split("\\s");
				if (strs.length != 2) {
					continue;
				}
				switch(strs[0]) {
				case "Boolean":
					list.add(new Boolean(strs[1]));
					break;
				case "Character":
					list.add(new Character(strs[1].charAt(0)));
					break;
				case "Short":
					list.add(new Short(strs[1]));
					break;
				case "Byte":
					list.add(new Byte(strs[1]));
					break;
				case "Integer":
					list.add(new Integer(strs[1]));
					break;
				case "Long":
					list.add(new Long(strs[1]));
					break;
				case "Float":
					list.add(new Float(strs[1]));
					break;
				case "Double":
					list.add(new Double(strs[1]));
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}


}
