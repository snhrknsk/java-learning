package interpret;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExceptionDialog{

	public static void createExceptionDialog(JFrame owner, Exception e) {
		JOptionPane.showMessageDialog(owner, e);
		e.printStackTrace();
	}

	public static void createExceptionDialog(JFrame owner, String message) {
		JOptionPane.showMessageDialog(owner, message);
	}

}
