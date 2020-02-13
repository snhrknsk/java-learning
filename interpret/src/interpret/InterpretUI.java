package interpret;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * インスタンスを作成するUI
 */
public class InterpretUI extends JFrame implements ActionListener {

	private JComboBox<String> constructors;
	private JTextField classField;
	private DefaultTableModel tableModel;
	private ObjectManager objectManager;
	private HomeUI home;
	private JTable parameterTable;

	private enum ActionItem{
		ClassButton,
		CreateButton
	}

	public InterpretUI(HomeUI home) {
		this.home = home;
		setTitle("Interpret");
		initialize();
		setVisible(true);
	}

	/**
	 * UIの作成
	 */
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
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		JLabel className = new JLabel("Class Name :");
		gbLayout.setConstraints(className, gbc);
		panel.add(className);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 6.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    classField = new JTextField();
		gbLayout.setConstraints(classField, gbc);
		panel.add(classField);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 2;
	    gbc.gridy = 0;
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

		//Constructor
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JLabel constructorName = new JLabel("Constructor :");
		gbLayout.setConstraints(constructorName, gbc);
		panel.add(constructorName);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.weightx = 7.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    constructors = new JComboBox<>();

	    constructors.addActionListener(this);
	    constructors.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
				     selectConstructor(constructors.getSelectedIndex());
				}
			}
		});
		gbLayout.setConstraints(constructors, gbc);
		panel.add(constructors);

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

		String[] columnNames = {"Arg No.", "TYPE", "VALUE"};

		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1) {
					return false;
				}
			    return true;
			  }
//			@Override
//			  public void setValueAt(Object val, int rowIndex, int columnIndex) {
//			    data[rowIndex][columnIndex] = val;
//			    fireTableCellUpdated(rowIndex, columnIndex);
//			  }
		};
//		tableModel.addTableModelListener(new TableModelListener() {
//
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				if (e.getType() == TableModelEvent.UPDATE) {
//					System.out.println(tableModel.getValueAt(e.getFirstRow(), e.getColumn()));
////					tableModel.setValueAt(tableModel.getValueAt(e.getFirstRow(), e.getColumn()), e.getFirstRow(), e.getColumn());
//				}
//			}
//		});
		parameterTable = new JTable(tableModel);
		parameterTable.setRowHeight(18);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 4;
	    gbc.gridheight = 1;
	    gbc.weighty = 100.0;
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

	private void selectConstructor(int index) {
		System.out.println(index);
		tableModel.setRowCount(0);
		List<String> params = objectManager.getConstructorParam(index);
		for (int i = 0; i < params.size(); i++) {
			tableModel.addRow(new String[] {((Integer)i).toString(), params.get(i),"" });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(ActionItem.ClassButton.name())) {
			System.out.println("search class");
			try {
				Class<?> target = Interpret.searchClass(classField.getText());
				objectManager = new ObjectManager(target);
				createComboBox();
			} catch (ClassNotFoundException e1) {
				ExceptionDialog.createExceptionDialog(this, e1);
			}
		} else if (command.equals(ActionItem.CreateButton.name())) {//instance作成
			System.out.println("create instance");
			if (tableModel.getRowCount() > 0) {
				parameterTable.getCellEditor().stopCellEditing();//ボックス内の変更を確定
			}
			try {
				objectManager.createObject(constructors.getSelectedIndex(), getTableParam());
				home.addObject(objectManager);
				dispose();
			} catch (Exception e1) {
				ExceptionDialog.createExceptionDialog(this, e1);
			}
		}
	}

	private void createComboBox() {
		constructors.removeAllItems();
		List<String> constructorsList = objectManager.getConstructor();
		for (String string : constructorsList) {
			constructors.addItem(string);
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
