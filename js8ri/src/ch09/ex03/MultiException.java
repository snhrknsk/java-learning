package ch09.ex03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public class MultiException {

	private boolean isFileNotFound = true;

	public void process() throws IOException {
		try {
			if (isFileNotFound) {
				throw new FileNotFoundException();
			}
			throw new UnknownHostException();
		}catch (FileNotFoundException | UnknownHostException e) {
			throw e;
		}
	}
}
