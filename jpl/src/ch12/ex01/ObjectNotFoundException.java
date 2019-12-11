package ch12.ex01;

public class ObjectNotFoundException extends Exception {

	private Object searchObject = null;

	public ObjectNotFoundException() {
		super();
	}

	public ObjectNotFoundException(Exception e) {
		super(e);
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}

	public ObjectNotFoundException(String message, Exception e) {
		super(message, e);
	}

	public ObjectNotFoundException(Object o) {
		searchObject = o;
	}

	public Object getSearchObject() {
		return searchObject;
	}
}
