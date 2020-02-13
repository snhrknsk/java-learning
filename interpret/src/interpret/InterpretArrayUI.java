package interpret;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;


public class InterpretArrayUI extends JFrame implements ActionListener{

	private JTextField classField;
	private DefaultTableModel tableModel;
	private JSpinner arrayNumSpiner;
	private ObjectManager objectManager;
	private HomeUI home;
	private JTable parameterTable;

	private enum ActionItem{
		ClassButton,
		CreateButton
	}

	public InterpretArrayUI(HomeUI home) {
		this.home = home;
		setTitle("Interpret");
		initialize();
		setVisible(true);
	}

	private void initialize() {

		setSize(500, 500);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
	        	dispose();
	        }
		});

		JPanel panel = new JPanel();
		GridBagLayout gbLayout = new GridBagLayout();
		panel.setLayout(gbLayout);
		Insets insets = new Insets(3, 5, 1, 1);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 2.0;
	    gbc.insets = insets;
		JLabel className = new JLabel("Class Name :");
		gbLayout.setConstraints(className, gbc);
		panel.add(className);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.gridwidth = 3;
	    gbc.gridheight = 1;
	    gbc.weightx = 6.0;
	    gbc.weighty = 2.0;
	    gbc.insets = insets;
	    classField = new JTextField();
		gbLayout.setConstraints(classField, gbc);
		panel.add(classField);

		//Constructor
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JLabel arrayName = new JLabel("Array Num : ");
		gbLayout.setConstraints(arrayName, gbc);
		panel.add(arrayName);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 5.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    SpinnerNumberModel model = new SpinnerNumberModel(1, 0, 100, 1);
	    arrayNumSpiner = new JSpinner(model);
	    gbLayout.setConstraints(arrayNumSpiner, gbc);
	    panel.add(arrayNumSpiner);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JButton classButton = new JButton("決定");
	    classButton.setActionCommand(ActionItem.ClassButton.name());
	    classButton.addActionListener(this);
		gbLayout.setConstraints(classButton, gbc);
		panel.add(classButton);

		//Parameter
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JLabel paramLabel = new JLabel("Parameter : ");
		gbLayout.setConstraints(paramLabel, gbc);
		panel.add(paramLabel);

		String[] columnNames = {"Array No.", "TYPE", "VALUE"};

		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1) {
					return false;
				}
			    return true;
			  }
		};

		parameterTable = new JTable(tableModel);
		parameterTable.setRowHeight(18);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 4;
	    gbc.gridheight = 1;
	    gbc.weighty = 70.0;
	    gbc.insets = insets;

	    JScrollPane scrollPanel = new JScrollPane(parameterTable);
		gbLayout.setConstraints(scrollPanel, gbc);

		panel.add(scrollPanel);

		//Create instance
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.insets = insets;
	    gbc.gridx = 2;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    JButton createButton = new JButton("作成");
	    createButton.addActionListener(this);
	    createButton.setActionCommand(ActionItem.CreateButton.name());
		gbLayout.setConstraints(createButton, gbc);
		panel.add(createButton);

		this.getContentPane().add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(ActionItem.ClassButton.name())) {
			Class<?> target;
			try {
				if (TypeUtil.isPrimitive(classField.getText())) {
					target = TypeUtil.getPrimitiveClass(classField.getText());
				} else {
					target = Interpret.searchClass(classField.getText());
				}
				objectManager = new ObjectManager(target);
				createArgTable();
			} catch (ClassNotFoundException e1) {
				ExceptionDialog.createExceptionDialog(this, e1);
			}
		} else if (command.equals(ActionItem.CreateButton.name())) {//instance作成
			if (tableModel.getRowCount() > 0) {
				if (parameterTable.getCellEditor() != null) {
					parameterTable.getCellEditor().stopCellEditing();//ボックス内の変更を確定
				}
			}
			try {
				objectManager.createObject(arrayNumSpiner.getValue(), getTableParam());
				home.addObject(objectManager);
				dispose();
			} catch (Exception e1) {
				ExceptionDialog.createExceptionDialog(this, e1);
			}
		}
	}

	private void createArgTable() {
		tableModel.setRowCount(0);
		for (int i = 0; i < Integer.valueOf(arrayNumSpiner.getValue().toString()); i++) {
			tableModel.addRow(new String[] {((Integer)i).toString(), Interpret.trimPackage( objectManager.getTargetClass().toString() ),"" });
		}
	}

	private List<String> getTableParam(){
		List<String> paramList = new ArrayList<>();
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			paramList.add(tableModel.getValueAt(i, 2).toString());
		}
		return paramList;
	}

}
