package ch20.ex09;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

public class FileParser {

	private final List<String> filePathList;

	public FileParser(String... path) {
		filePathList = Arrays.asList(path);
	}

	public void dispFileInfo() {
		for (String filePath : filePathList) {
			File file = new File(filePath);
			BasicFileAttributes attrs;
			try {
				attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

				System.out.println("Name          : " + file.getName());
				System.out.println("Parent Path   : " + file.getParent());
				System.out.println("Path          : " + file.getPath());
				System.out.println("Absolute Path : " + file.getAbsolutePath());
				System.out.println("File Type     : " + (file.isDirectory() ? "Directory" : file.isFile() ? "File" : attrs.isSymbolicLink() ? "Synbolic link" : "Other") );
				System.out.println("Hidden File   : " + file.isHidden());
				System.out.println("Created Date  : " + attrs.creationTime());
				System.out.println("Last Modified : " + attrs.lastModifiedTime());
				System.out.println("Last Access   : " + attrs.lastAccessTime());

				if (file.isDirectory()) {
					System.out.println("Children directory/file : ");
					for( String child : file.list()) {
						System.out.println(" " + child);
					}
				}
				if (file.isFile()) {
					System.out.println("Length        : " + file.length());
					System.out.println("Read          : " + file.canRead());
					System.out.println("Write         : " + file.canWrite());
					System.out.println("Execute       : " + file.canExecute());
					System.out.println("Partition Size: " + file.getTotalSpace());
					System.out.println("Empty Size    : " + file.getFreeSpace());
				}
				System.out.println("########################################################");
			} catch (IOException e) {
			}
		}
	}
}
