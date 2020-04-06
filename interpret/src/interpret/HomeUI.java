package interpret;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 起動画面
 *
 */
public class HomeUI extends JFrame implements ActionListener{

	private enum Action{
		Create,
		Edit
	}

	private DefaultTableModel tableModel;
	private JRadioButton instance;
	private JRadioButton array;
	private JTable instanceList;

	public HomeUI() {
		setTitle("Interpret");
		setSize(500, 400);
		initialize();
		setVisible(true);
	}

	private void initialize() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		JLabel className = new JLabel("インスタンスの作成 :");
		gbLayout.setConstraints(className, gbc);
		panel.add(className);

		//instance or array
		ButtonGroup instanceTypeGroup = new ButtonGroup();
		instance = new JRadioButton("Instance", true);
		array = new JRadioButton("Array");
		instanceTypeGroup.add(instance);
		instanceTypeGroup.add(array);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		gbLayout.setConstraints(instance, gbc);
		panel.add(instance);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		gbLayout.setConstraints(array, gbc);
		panel.add(array);

		JButton createObjectButton = new JButton("作成");
		createObjectButton.addActionListener(this);
		createObjectButton.setActionCommand(Action.Create.name());

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		gbLayout.setConstraints(createObjectButton, gbc);
		panel.add(createObjectButton);

		//instance table
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    JLabel paramLabel = new JLabel("Instance : ");
		gbLayout.setConstraints(paramLabel, gbc);
		panel.add(paramLabel);

		String[] columnNames = {"Name", "TYPE"};

		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1) {
					return false;
				}
			    return true;
			  }
		};
		instanceList = new JTable(tableModel);
		instanceList.setRowHeight(18);
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 3;
	    gbc.gridheight = 1;
	    gbc.weighty = 90.0;
	    gbc.insets = insets;

	    JScrollPane scrollPanel = new JScrollPane(instanceList);
		gbLayout.setConstraints(scrollPanel, gbc);
		panel.add(scrollPanel);

		//Edit instance
		JButton editButton = new JButton("編集");
		editButton.addActionListener(this);
		editButton.setActionCommand(Action.Edit.name());
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 2;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    gbLayout.setConstraints(editButton, gbc);
		panel.add(editButton);

		add(panel);
	}

	public void addObject(ObjectManager object) {
		String instanceName = Interpret.trimPackage(object.getTargetClass().toString()).replace("class ", "") + "#" + InstanceManager.getClassIndex();
		tableModel.addRow(new String[] {instanceName, object.getTargetClassName()});
		InstanceManager.getInstance().addInstance(instanceName, object);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(Action.Create.name())) {
			if (instance.isSelected()) {
				new InterpretUI(this);
			} else if (array.isSelected()) {
				new InterpretArrayUI(this);
			}

		} else if (command.equals(Action.Edit.name())) {
			if (instanceList.getSelectedRow() == -1) {
				ExceptionDialog.createExceptionDialog(this, "Select instance you want to edit.");
				return;
			}
			String instanceName = tableModel.getValueAt(instanceList.getSelectedRow(), 0).toString();
			new InterpretInstanceUI(InstanceManager.getInstance().getCreatedObject(instanceName), instanceName);
		}
	}


}
