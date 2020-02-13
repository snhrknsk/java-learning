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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class InterpretInstanceUI extends JFrame implements ActionListener{

	private ObjectManager targetObjectManager;
	private DefaultTableModel paramTableModel;
	private DefaultTableModel objectParamTableModel;
	private JTable parameterTable;
	private JTable objectParameterTable;
	private JComboBox<String> methodList;

	private enum Action{
		ExcecMethod,
		ChangeObjectParam
	}

	public InterpretInstanceUI(ObjectManager target, String instanceName) {
		this.targetObjectManager = target;
		setTitle(instanceName);
		initialize();
		setVisible(true);
	}

	private void initialize() {

		setSize(700, 500);
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

		//method
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JLabel methodLabel = new JLabel("Methodリスト : ");
		gbLayout.setConstraints(methodLabel, gbc);
		panel.add(methodLabel);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    methodList = new JComboBox<>();

	    methodList.addActionListener(this);
	    methodList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
				     selectmethod(methodList.getSelectedIndex());
				}
			}
		});
	    createMethodList();
		gbLayout.setConstraints(methodList, gbc);
		panel.add(methodList);

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

		paramTableModel = new DefaultTableModel(columnNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1) {
					return false;
				}
			    return true;
			  }
		};

		parameterTable = new JTable(paramTableModel);
		parameterTable.setRowHeight(18);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weighty = 70.0;
	    gbc.insets = insets;

	    JScrollPane scrollPanel = new JScrollPane(parameterTable);
		gbLayout.setConstraints(scrollPanel, gbc);

		panel.add(scrollPanel);

		//Excec method
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.insets = insets;
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    JButton createButton = new JButton("実行");
	    createButton.addActionListener(this);
	    createButton.setActionCommand(Action.ExcecMethod.name());
		gbLayout.setConstraints(createButton, gbc);
		panel.add(createButton);

		//object parameter
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JLabel objectParamLabel = new JLabel("Instance Parameters : ");
		gbLayout.setConstraints(objectParamLabel, gbc);
		panel.add(objectParamLabel);

		String[] columnObjectParamNames = {"Field Name", "TYPE", "VALUE"};

		objectParamTableModel = new DefaultTableModel(columnObjectParamNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1) {
					return false;
				}
			    return true;
			  }
		};

		objectParameterTable = new JTable(objectParamTableModel);
		objectParameterTable.setRowHeight(18);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 3;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JScrollPane scrollPanel2 = new JScrollPane(objectParameterTable);
		gbLayout.setConstraints(scrollPanel2, gbc);
		panel.add(scrollPanel2);

		//変更ボタン
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.insets = insets;
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    JButton changeButton = new JButton("変更");
	    changeButton.addActionListener(this);
	    changeButton.setActionCommand(Action.ChangeObjectParam.name());
		gbLayout.setConstraints(changeButton, gbc);
		panel.add(changeButton);

		this.getContentPane().add(panel);
	}

	private void createMethodList() {
		methodList.addItem("未実装");
	}

	private void selectmethod(int selectedIndex) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(Action.ExcecMethod.name())) {
			System.out.println("Excec method");

		} else if (command.equals(Action.ChangeObjectParam.name())) {//instance作成
			System.out.println("Change Parameter");

		}
	}

}
