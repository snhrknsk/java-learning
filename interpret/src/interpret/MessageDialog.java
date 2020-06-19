package interpret;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageDialog {

	public static void createExceptionDialog(JFrame owner, Exception e) {
		if (e.getCause() != null) {
			createExceptionDialog(owner, e.getCause().toString());
			return;
		}
		JOptionPane.showMessageDialog(owner, e);
		e.printStackTrace();
	}

	public static void createExceptionDialog(JFrame owner, String message) {
		JOptionPane.showMessageDialog(owner, message);
	}

	public static void createMessageDialog(JFrame owner, String message) {
		JOptionPane.showMessageDialog(owner, message);
	}
}
