package ch01.ex16;

import java.io.IOException;

public class BadDataSetException extends Exception {

	private String badDataName;

	public BadDataSetException(String name, IOException cause) {
		super(cause);
		this.badDataName = name;
	}

	public String getBadDataName() {
		return badDataName;
	}
}
