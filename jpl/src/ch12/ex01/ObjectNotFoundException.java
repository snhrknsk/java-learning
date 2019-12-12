package ch12.ex01;

public class ObjectNotFoundException extends Exception {

	//検索しようとしているオブジェクトをExceptionに持つ
	private Object searchObject = null;

	public ObjectNotFoundException() {
		super();
	}

	public ObjectNotFoundException(Object o, Exception e) {
		super(e);
		searchObject = o;
	}

	public ObjectNotFoundException(Object o, String message) {
		super(message);
		searchObject = o;
	}

	public ObjectNotFoundException(Object o, String message, Exception e) {
		super(message, e);
		searchObject = o;
	}

	public ObjectNotFoundException(Object o) {
		searchObject = o;
	}

	public Object getSearchObject() {
		return searchObject;
	}
}
