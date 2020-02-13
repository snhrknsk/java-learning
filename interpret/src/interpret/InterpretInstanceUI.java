package interpret;

import javax.swing.JFrame;

public class InterpretInstanceUI extends JFrame {

	private ObjectManager targetObject;

	public InterpretInstanceUI(ObjectManager target) {
		this.targetObject = target;
		initialize();
		setVisible(true);
	}

	private void initialize() {

		System.out.println(targetObject.getCreatedObject().toString());
		Object temp = targetObject.getCreatedObject();
		setSize(700, 500);
	}

}
