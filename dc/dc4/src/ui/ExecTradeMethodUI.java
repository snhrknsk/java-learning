package src.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import src.trade.exec.ITradeLogic;
import src.trade.manager.TaskManager;

public class ExecTradeMethodUI implements ITabComponent, ActionListener {
	private Logger log = Logger.getLogger(ExecTradeMethodUI.class);
	private DefaultTableModel currentExecTableModel = null;
	private JComboBox<ITradeLogic> logicList = null;
	private DefaultTableModel paramTableModel = null;
	private JTable paramTable = null;
	private JTable currentExecTable = null;
	private JButton updateButton = null;
	private JButton deleteButton = null;

	private boolean isExistingLogicSelected = false;
	private ITradeLogic currentSelectedLogic = null;
	private boolean completeInitialize = false;

	private enum Action{update, delete}

	@Override
	public String getTabName() {
		return "アルゴリズム";
	}

	@Override
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		//LEFT side : current exec logic list
		JPanel currentExecPanel = new JPanel();
		currentExecPanel.setLayout(new BorderLayout());
		currentExecPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//current exec logic
		JPanel currentAlgorithmPanel = new JPanel();
		currentAlgorithmPanel.setLayout(new BoxLayout(currentAlgorithmPanel, BoxLayout.Y_AXIS));
		currentAlgorithmPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel label = new JLabel("現在設定");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentAlgorithmPanel.add(label);

		currentExecTableModel = new DefaultTableModel();
		String[] columnTradeHistoryNames = {"実行中のアルゴリズム"};
		currentExecTableModel = new DefaultTableModel(columnTradeHistoryNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		currentExecTable = new JTable(currentExecTableModel);
		currentExecTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentExecTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				if (currentExecTable.getSelectedRowCount() == 1) {
					int index = currentExecTable.getSelectedRow();
					Object logic = currentExecTableModel.getValueAt(index, 0);
					if (logic instanceof ITradeLogic) {
						updateParamTable((ITradeLogic) logic);
					} else {
						throw new IllegalStateException("Exec logic is invalid class");
					}
					logicList.setSelectedIndex(-1);
					isExistingLogicSelected = true;
					updateButton.setText("更新");
					deleteButton.setEnabled(true);
				}
			}
		});
		JScrollPane scrollPanel = new JScrollPane(currentExecTable);
		currentAlgorithmPanel.add(scrollPanel);
		currentExecPanel.add("Center", currentAlgorithmPanel);
		deleteButton = new JButton("削除");
		deleteButton.addActionListener(this);
		deleteButton.setActionCommand(Action.delete.name());
		deleteButton.setEnabled(false);
		currentExecPanel.add("South", deleteButton);
		panel.add(currentExecPanel);
		updateCurrentLogic();

		//RIGHT side : add logic or update current logic settings
		JPanel logicUpdatePanel = new JPanel();
		logicUpdatePanel.setLayout(new BorderLayout());
		logicUpdatePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//add new logic
		JPanel newLogicParam = new JPanel();
		newLogicParam.setLayout(new BoxLayout(newLogicParam, BoxLayout.Y_AXIS));
		newLogicParam.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel addLogicLabel = new JLabel("新規追加");
		addLogicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		newLogicParam.add(addLogicLabel);
		logicList = new JComboBox<>();
		logicList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED && completeInitialize){
					if (logicList.getSelectedItem() instanceof ITradeLogic) {
						updateParamTable((ITradeLogic) logicList.getSelectedItem());
					} else {
						new IllegalStateException("logic list is invalid");
					}
					currentExecTable.clearSelection();
					isExistingLogicSelected = false;
					updateButton.setText("追加");
					deleteButton.setEnabled(false);
				}
			}
		});
		for (TaskManager.LOGIC_SET logic: TaskManager.LOGIC_SET.values()) {
			logicList.addItem(logic.createInstance());
		}
		logicList.setSelectedIndex(-1);
		newLogicParam.add(logicList);
		logicUpdatePanel.add("North", newLogicParam);
		//parameter
		JPanel paramPanel = new JPanel();
		paramPanel.setLayout(new BoxLayout(paramPanel, BoxLayout.Y_AXIS));
		paramPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel paramLabel = new JLabel("パラメータ");
		paramLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		paramPanel.add(paramLabel);
		paramTableModel = new DefaultTableModel();
		String[] columnParamNames = {"引数", "値"};
		paramTableModel = new DefaultTableModel(columnParamNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 1) {return true;}
				return false;
			}
		};
		paramTable = new JTable(paramTableModel);
		JScrollPane scrollPanel2 = new JScrollPane(paramTable);
		paramPanel.add(scrollPanel2);
		logicUpdatePanel.add("Center", paramPanel);

		updateButton = new JButton("更新");
		updateButton.addActionListener(this);
		updateButton.setActionCommand(Action.update.name());
		logicUpdatePanel.add("South", updateButton);

		panel.add(logicUpdatePanel);
		completeInitialize = true;
		return panel;
	}

	@Override
	public void updateComponent() {
		updateCurrentLogic();
		updateParamTable(currentSelectedLogic);
	}

	private void updateCurrentLogic(){
		currentExecTableModel.setNumRows(0);
		List<ITradeLogic> currentTradeList = TaskManager.getInstance().getTradingTask();
		for (ITradeLogic logic : currentTradeList) {
			currentExecTableModel.addRow(new ITradeLogic[]{logic});
		}
	}

	private void updateParamTable(ITradeLogic logic){
		if (logic == null) {
			paramTableModel.setNumRows(0);
			return;
		}
		log.info("Update Parameter : " + logic.getParams());
		paramTableModel.setNumRows(0);
		currentSelectedLogic = logic;
		Map<String, String> params = logic.getParams();
		for (Map.Entry<String, String> entry:params.entrySet()) {
			paramTableModel.addRow(new String[]{entry.getKey(), entry.getValue()});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(Action.update.name()) && currentSelectedLogic != null){
			if(!currentSelectedLogic.setParams(createNewParamMap())){
				return;
			}
			if (!isExistingLogicSelected){
				TaskManager.getInstance().startNewTask(currentSelectedLogic);
				logicList.removeAllItems();
				for (TaskManager.LOGIC_SET logic: TaskManager.LOGIC_SET.values()) {
					logicList.addItem(logic.createInstance());
				}
				logicList.setSelectedIndex(-1);
			} else {
			}
			updateCurrentLogic();
			updateParamTable(null);
			currentSelectedLogic = null;
		} else if (command.equals(Action.delete.name()) && isExistingLogicSelected && currentSelectedLogic != null) {
			TaskManager.getInstance().stopTask(currentSelectedLogic.toString());
			currentExecTableModel.removeRow(currentExecTable.getSelectedRow());
		}
	}

	private Map<String, String> createNewParamMap(){
		//TODO: set parameter in table to currentSelectedLogic
		Map<String, String> paramMap = new HashMap<>();
		if (paramTableModel.getRowCount() > 0 && paramTable.getCellEditor() != null) {
			paramTable.getCellEditor().stopCellEditing();//ボックス内の変更を確定
		}
		for (int i = 0; i < paramTableModel.getRowCount(); i++) {
			paramMap.put(paramTableModel.getValueAt(i, 0).toString(), paramTableModel.getValueAt(i, 1).toString());
		}
		return paramMap;
	}
}
