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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * インスタンスの表示編集
 */
public class InterpretInstanceUI extends JFrame implements ActionListener{

	private ObjectManager targetObjectManager;
	private DefaultTableModel paramTableModel;
	private DefaultTableModel objectParamTableModel;
	private JTable parameterTable;
	private JTable objectParameterTable;
	private JComboBox<String> methodBoxList;
//	private Field[] memList;
	private List<Field> memberList;
//	private Method[] methods;
	private List<Method> methodList;
	private Object targetObject;
	private int selectedMethodIndex = 0;

	private enum Action{
		ExcecMethod,
		ChangeObjectParam
	}

	public InterpretInstanceUI(ObjectManager target, String instanceName) {
		this.targetObjectManager = target;
		this.targetObject = targetObjectManager.getCreatedObject();
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

		String[] columnNames = {"Arg No.", "TYPE", "VALUE"};

		paramTableModel = new DefaultTableModel(columnNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1) {
					return false;
				}
			    return true;
			  }
		};

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
	    methodBoxList = new JComboBox<>();

	    methodBoxList.addActionListener(this);
	    methodBoxList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					selectedMethodIndex = methodBoxList.getSelectedIndex();
				    selectmethod(selectedMethodIndex);
				}
			}
		});
	    createMethodList();
		gbLayout.setConstraints(methodBoxList, gbc);
		panel.add(methodBoxList);

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
	    gbc.weightx = 2.5;
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
		createFields();

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

		methodList = Arrays.asList(targetObjectManager.getTargetClass().getDeclaredMethods());
		methodList.sort(new Comparator<Method>() {
			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		for (Method method : methodList) {
			methodBoxList.addItem(createMethodName(method));
		}
	}

	private String createMethodName(Method method) {
		StringBuilder methodName = new StringBuilder(method.getName());
		methodName.append("(");
		for (int i = 0; i < method.getParameterCount(); i++) {
			methodName.append(Interpret.trimPackage(method.getParameters()[i].getType().getName()));
			if (i != method.getParameterCount() - 1) {
				methodName.append(", ");
			}
		}
		methodName.append(")");
		return methodName.toString();
	}

	private void selectmethod(int selectedIndex) {
		paramTableModel.setRowCount(0);
		Parameter[] params = methodList.get(selectedIndex).getParameters();
		for (int i = 0; i < params.length; i++) {
			paramTableModel.addRow(new String[] {((Integer)i).toString(), Interpret.trimPackage(params[i].getType().getName()),"" });
		}
	}

	private void createFields() {
		objectParamTableModel.setRowCount(0);
//		memList = targetObjectManager.getTargetClass().getDeclaredFields();
		memberList = Arrays.asList(targetObjectManager.getTargetClass().getDeclaredFields());
		memberList.sort(new Comparator<Field>() {

			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		for (Field member : memberList) {
			try {
				member.setAccessible(true);
				objectParamTableModel.addRow(new String[] {Interpret.trimPackage(member.getName()), Interpret.trimPackage(member.getDeclaringClass().toString()), member.get(targetObject).toString()});
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(Action.ExcecMethod.name())) {
			System.out.println("Excec method");
			//テーブルに入力された変更確定
			if (paramTableModel.getRowCount() > 0 && parameterTable.getCellEditor() != null) {
				parameterTable.getCellEditor().stopCellEditing();
			}
			executeMethod();
		} else if (command.equals(Action.ChangeObjectParam.name())) {//instance作成
			System.out.println("Change Parameter");

		}
	}

	private void executeMethod() {
		Method execMethod = methodList.get(methodBoxList.getSelectedIndex());
		try {
			List<String> paramList = getMethodTableParam();
			Object[] paramArray = null;
			if (paramList.size() != 0) {
				paramArray = new Object[paramList.size()];
			} else {
				paramArray = new Object[0];
			}
			for (int i = 0; i < paramList.size(); i++) {
				paramArray[i] = TypeUtil.convertType(paramList.get(i), paramTableModel.getValueAt(i, 1).toString());
			}
			Object returnValue = execMethod.invoke(targetObject, paramArray);
			String returnMessage = Interpret.trimPackage(execMethod.getReturnType().getName()) + " : " + returnValue.toString();
			MessageDialog.createMessageDialog(this, returnMessage);
			createFields();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
			MessageDialog.createExceptionDialog(this, e1);
		}
	}

	private List<String> getMethodTableParam() {
		List<String> paramList = new ArrayList<>();
		for (int i = 0; i < paramTableModel.getRowCount(); i++) {
			paramList.add(paramTableModel.getValueAt(i, 2).toString());
		}
		return paramList;
	}

}
