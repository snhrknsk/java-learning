package ch20.ex08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class EntryReader {

	private List<Long> entryList = new ArrayList<>();
	private File srcFile;

	private final String entryFileName = "EntryFile.txt";
	private final String separator = "%%";

	public EntryReader(File file) {
		srcFile = file;
	}

	/**
	 * %%で区切られたエントリーの開始位置を見つけ各エントリーのテーブルファイルを作成する
	 * @throws IOException
	 */
	public void parseEntry() throws IOException {
		try(RandomAccessFile randFile = new RandomAccessFile(srcFile, "r")){
			String line;

			while((line = randFile.readLine()) != null) {
				if (line.startsWith(separator)) {
					entryList.add(randFile.getFilePointer());
				} else if (entryList.isEmpty()) {
					entryList.add((long) 0);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Path=" + srcFile.getPath());
			throw e;
		} catch (IOException e) {
			throw e;
		}

		String[] split = srcFile.getPath().split("\\\\");
		split[split.length -1] = entryFileName;
		String entryFilePath = String.join("\\\\", split);
		createEntryIndexFile(entryFilePath);
	}

	private File createEntryIndexFile( String path ) throws IOException {
		File file = new File(path);
		file.createNewFile();
		try(FileWriter out = new FileWriter(file)){
			for (Long index : entryList) {
				out.write(index.toString() + System.lineSeparator());
			}
		} catch (IOException e) {
			throw e;
		}
		return file;
	}

	/**
	 * ランダムにエントリーの中身を表示
	 * @throws IOException
	 */
	public String dispRndomEntry() throws IOException {

		int entryNo= (int) (Math.random() * entryList.size());
		long index = entryList.get(entryNo);
		StringBuilder stringBuilder = new StringBuilder();

		try(RandomAccessFile randFile = new RandomAccessFile(srcFile, "r")){
			randFile.seek(index);
			String line;
			while((line = randFile.readLine()) != null) {
				if (line.startsWith(separator)) {
					break;
				}
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
			}
		}  catch (FileNotFoundException e) {
			System.out.println("File not found. Path=" + srcFile.getPath());
			throw e;
		} catch (IOException e) {
			throw e;
		}
		System.out.println("Entry " + entryNo + " :");
		System.out.println( stringBuilder );

		return stringBuilder.toString();
	}
}
