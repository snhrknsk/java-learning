package interpret;

import java.awt.Dimension;
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
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private List<Field> memberList;
	private List<Method> methodList;
	private Object targetObject;
	private int selectedMethodIndex = 0;
	private Map<String, String> preModifiedFieldMap = new HashMap<>();

	protected enum Action{
		ExcecMethod,
		ChangeObjectParam
	}

	public InterpretInstanceUI(HomeUI home, ObjectManager target, String instanceName) {
		this.targetObjectManager = target;
		this.targetObject = targetObjectManager.getCreatedObject();
		setTitle(instanceName);
		initialize();
		setLocation(home.getLocation().x + 20, home.getLocation().y + 20);
		setVisible(true);
	}

	private void initialize() {


		setSize(900, 500);
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
	    methodBoxList.setPreferredSize(new Dimension(180, 40));
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

	/**
	 * methodのテーブル
	 */
	private void createMethodList() {

		Class<?> clazz = targetObject.getClass();
		Set<String> methodSet = new HashSet<>();
		methodList = new ArrayList<>();
		while (clazz != null) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
            	if (!methodSet.contains(createMethodName(method))) {
    				methodList.add(method);
    				methodSet.add(createMethodName(method));
				}
			}
            clazz = clazz.getSuperclass();
        }
		methodList.sort(new Comparator<Method>() {
			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		for (Method method : methodList) {
			methodBoxList.addItem(trimLimitStrLength(createMethodName(method)));
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

	private String trimLimitStrLength(String methodName){
		if (methodName.length() > 40) {
			return methodName.substring(0, 40) + "...";
		}
		return methodName;
	}

	private void selectmethod(int selectedIndex) {
		paramTableModel.setRowCount(0);
		Parameter[] params = methodList.get(selectedIndex).getParameters();
		for (int i = 0; i < params.length; i++) {
			paramTableModel.addRow(new String[] {((Integer)i).toString(), Interpret.trimPackage(params[i].getType().getName()),"" });
		}
	}

	/**
	 * Fieldのテーブル
	 */
	private void createFields() {
		objectParamTableModel.setRowCount(0);
		Class<?> clazz = targetObject.getClass();
		Set<String> memberSet = new HashSet<>();
		memberList = new ArrayList<>();
		while(clazz != null) {
			Field[] members = clazz.getDeclaredFields();
			for (Field field : members) {
				if (!memberSet.contains(field.getName())) {
					memberList.add(field);
					memberSet.add(field.getName());
				}
			}
			clazz = clazz.getSuperclass();
		}
//		memberList = Arrays.asList(targetObject.getClass().getDeclaredFields());
		memberList.sort(new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		for (Field member : memberList) {
			try {
				member.setAccessible(true);
				Field modifiersField = Field.class.getDeclaredField("modifiers");          // Fieldクラスはmodifiersでアクセス対象のフィールドのアクセス判定を行っているのでこれを更新する。
		        modifiersField.setAccessible(true);                                        // modifiers自体もprivateなのでアクセス可能にする。
		        modifiersField.setInt(member, member.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);
				objectParamTableModel.addRow(new String[] {Interpret.trimPackage(member.getName()), Interpret.trimPackage(member.getType().getName()), (member.get(targetObject) != null ? member.get(targetObject).toString() : null ) });
				preModifiedFieldMap.put(member.getName(), (member.get(targetObject) != null ? member.get(targetObject).toString() : null ));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(Action.ExcecMethod.name())) {
			if (paramTableModel.getRowCount() > 0 && parameterTable.getCellEditor() != null) {//テーブルに入力された変更確定
				parameterTable.getCellEditor().stopCellEditing();
			} else if (methodList.size() == 0) {
				MessageDialog.createExceptionDialog(this, "No method.");
				return;
			}
			executeMethod();
		} else if (command.equals(Action.ChangeObjectParam.name())) {
			if (objectParamTableModel.getRowCount() > 0 && objectParameterTable.getCellEditor() != null) {
				objectParameterTable.getCellEditor().stopCellEditing();
			} else if (memberList.size() == 0) {
				MessageDialog.createMessageDialog(this, "No field.");
			}
			setUpdateFieldValue();
		}
	}

	/**
	 * フィールド更新
	 */
	private void setUpdateFieldValue() {
		for (int i = 0; i < memberList.size(); i++) {
			String currencValue = (objectParamTableModel.getValueAt(i, 2) != null ? objectParamTableModel.getValueAt(i, 2).toString() : "" );
			String preValue = preModifiedFieldMap.get(objectParamTableModel.getValueAt(i, 0)) != null ? preModifiedFieldMap.get(objectParamTableModel.getValueAt(i, 0)) : "";
			Field targetField = memberList.get(i);

			try {
				targetField.setAccessible(true);
				if (!preValue.equals(currencValue)) {
					if (targetField.getType().equals(String.class)) {
						targetField.set(targetObject, TypeUtil.convertType(currencValue, targetField.getType().getName()));
					} else {
						targetField.set(targetObject, TypeUtil.convertType(currencValue, targetField.getType().getName()));
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				MessageDialog.createExceptionDialog(this, e);
				try {
					objectParamTableModel.setValueAt(targetField.get(targetObject).toString(), i, 2);
				} catch (IllegalArgumentException | IllegalAccessException e1) {
				}
			}
		}
	}

	/**
	 * 選択されたメソッドを実行
	 */
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
			String returnType = execMethod.getReturnType().getName() == null ? "void" : execMethod.getReturnType().getName();
			String returnValueStr = returnValue != null ? returnValue.toString() : "";
			String returnMessage = Interpret.trimPackage(returnType) + " : " + returnValueStr;
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
