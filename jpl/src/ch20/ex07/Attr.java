package ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Attr implements Serializable{
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Attr(DataInputStream in) throws ClassNotFoundException, IOException {
		try(ObjectInputStream ois = new ObjectInputStream(in)) {
			Object obj = ois.readObject();
			if (obj instanceof Attr) {
				name = ((Attr) obj).getName();
				value = ((Attr) obj).getValue();
			} else {
				throw new ClassCastException();
			}
		}
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "='" + value + "'";
	}

	public void writeStream(DataOutputStream out) throws IOException {
		try(ObjectOutputStream oos = new ObjectOutputStream(out)){
			oos.writeObject(this);
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Attr attr = new Attr("Integer", new Integer(5));
		File file = new File("./temp.txt");
		try (FileOutputStream fileStream = new FileOutputStream(file.getPath())){
			try (DataOutputStream out = new DataOutputStream(fileStream)){
				attr.writeStream(out);
			}
		}
		Attr restore ;
		try (FileInputStream fileInput = new FileInputStream(file.getPath())){
			try(DataInputStream in = new DataInputStream(fileInput)){
				restore = new Attr(in);
				System.out.println(restore.getName() + " : " + restore.getValue());
			}
		}
		file.delete();
	}
}
