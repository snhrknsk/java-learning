package ch24.ex01;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class GlobalRes_en extends ResourceBundle {

	private static Hashtable<String, String> lookup = null;

	@Override
	protected synchronized Object handleGetObject(String key) {
		if (lookup == null) {
			lookupLoad();
		}
        if (key == null) {
            throw new NullPointerException();
        }
        return lookup.get(key);
	}

	@Override
	public synchronized Enumeration<String> getKeys() {
		if (lookup == null) {
			lookupLoad();
		}
        return lookup.keys();
	}

	private void lookupLoad() {
		lookup = new Hashtable<>();
		lookup.put(GlobalRes.HELLO, "Hello");
		lookup.put(GlobalRes.GOODBYE, "GoodBye");
	}
}
