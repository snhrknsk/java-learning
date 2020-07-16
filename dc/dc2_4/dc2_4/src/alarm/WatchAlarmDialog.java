package dc2_4.src.alarm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import dc2_4.src.DigitalWatch;
import dc2_4.src.config.GlobalConfig;

public class WatchAlarmDialog extends JDialog implements ActionListener{

	private static int index = 0;
	private final DigitalWatch watch;
	private final WatchAlarmConfig config;
	private JComboBox<Integer> hourSelector;
	private JComboBox<Integer> minutesSelector;
	private JComboBox<String> soundSelector;
	private DefaultTableModel alarmTableModel;
	private JTable alarmTable;
	private JTextField alarmNameField;

	private static final Font CONTENT_FONT =  new Font("Dialog",Font.PLAIN,12);
	private static final String ALARM_NAME = "Alarm";

	private enum Action{
		OK, SAVE
	}

	public WatchAlarmDialog(DigitalWatch dialog) {
		super(dialog, true);
		watch = dialog;
		config = WatchAlarmConfig.getInstance();
		initialize();
		setVisible(true);
	}

	private void initialize() {
		setTitle("Alarm Configuration");
		setSize(350, 350);
		setFont(CONTENT_FONT);
		setLocation(watch.getLocation().x + 10, watch.getLocation().y + 10);

//		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Box.createRigidArea(new Dimension(1,4)));
		JLabel alarmLabel = new JLabel("AlarmName:                                                                                   ");
		JPanel alarmLabelPanel = new JPanel();
		alarmLabelPanel.setLayout(new BoxLayout(alarmLabelPanel, BoxLayout.X_AXIS));
		alarmLabelPanel.add(alarmLabel);
		panel.add(alarmLabelPanel);

		String textFieldString = ALARM_NAME + index;
		for (Entry<String, AlarmData> element : config.getAlarmData().entrySet()) {
			if (textFieldString.equals(element.getKey())) {
				index++;
			}
		}
		alarmNameField = new JTextField(ALARM_NAME + index);
		JPanel alarmFieldPanel = new JPanel();
		alarmFieldPanel.setLayout(new BoxLayout(alarmFieldPanel, BoxLayout.X_AXIS));
		alarmFieldPanel.add(Box.createRigidArea(new Dimension(5,1)));
		alarmFieldPanel.add(alarmNameField);
		alarmFieldPanel.add(Box.createRigidArea(new Dimension(5,1)));
		panel.add(alarmFieldPanel);

		panel.add(Box.createRigidArea(new Dimension(1,4)));
		JLabel sound = new JLabel("Sound:                                                                                              ");
		JPanel soundLabelPanel = new JPanel();
		soundLabelPanel.setLayout(new BoxLayout(soundLabelPanel, BoxLayout.X_AXIS));
		soundLabelPanel.add(sound);
		panel.add(soundLabelPanel);

		createSoundSelector();
		JPanel soundSelectPanel = new JPanel();
		soundSelectPanel.setLayout(new BoxLayout(soundSelectPanel, BoxLayout.X_AXIS));
		soundSelectPanel.add(Box.createRigidArea(new Dimension(5,1)));
		soundSelectPanel.add(soundSelector);
		soundSelectPanel.add(Box.createRigidArea(new Dimension(5,1)));
		panel.add(soundSelectPanel);

		panel.add(Box.createRigidArea(new Dimension(1,4)));
		JLabel time = new JLabel("Time :                                                                                               ");
		time.setAlignmentX(Component.LEFT_ALIGNMENT);
		JPanel timeLabelPanel = new JPanel();
		timeLabelPanel.setLayout(new BoxLayout(timeLabelPanel, BoxLayout.X_AXIS));
		timeLabelPanel.add(time);
		panel.add(timeLabelPanel);

		createTimeSelector();
		JPanel timeSelectPanel = new JPanel();
		timeSelectPanel.add(Box.createRigidArea(new Dimension(5,1)));
		timeSelectPanel.setLayout(new BoxLayout(timeSelectPanel, BoxLayout.X_AXIS));
		timeSelectPanel.add(hourSelector);
		timeSelectPanel.add(new JLabel(" : "));
		timeSelectPanel.add(minutesSelector);
		timeSelectPanel.add(Box.createRigidArea(new Dimension(5,1)));

		JButton button = new JButton("  Save  ");
		button.setActionCommand(Action.SAVE.name());
		button.addActionListener(this);
		timeSelectPanel.add(button);
		timeSelectPanel.add(Box.createRigidArea(new Dimension(5,1)));
		panel.add(timeSelectPanel);

		panel.add(Box.createRigidArea(new Dimension(1,5)));
		JLabel alarm = new JLabel("Alarm List :                                                                                     ");
		alarm.setAlignmentX(Component.LEFT_ALIGNMENT);
		JPanel alarmTablePanel = new JPanel();
		alarmTablePanel.setLayout(new BoxLayout(alarmTablePanel, BoxLayout.X_AXIS));
		alarmTablePanel.add(alarm);
		panel.add(alarmTablePanel);

		panel.add(Box.createRigidArea(new Dimension(5,1)));
		String[] columnObjectParamNames = {"ON/OFF", "Alarm", "Time", "Sound"};
		alarmTableModel = new AlarmTableModel(columnObjectParamNames, 0) {
			@Override public boolean isCellEditable(int row, int column) {
				if (column == 1 || column == 2 || column == 3) {
					return false;
				}
			    return true;
			  }
		};
		createAlarmTable();
		alarmTable = new JTable(alarmTableModel);
		alarmTable.setRowHeight(18);
	    JScrollPane scrollPanel = new JScrollPane(alarmTable);
	    JPanel tablePanel = new JPanel();
	    tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
	    tablePanel.add(Box.createRigidArea(new Dimension(5,1)));
		tablePanel.add(scrollPanel);
		tablePanel.add(Box.createRigidArea(new Dimension(5,1)));

		JPopupMenu tableMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Delete");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = alarmTable.getSelectedRow();
				if (index != -1) {
					String identifier = (String)alarmTableModel.getValueAt(index, 1);
					config.deleteAlarm(identifier);
					alarmTableModel.removeRow(index);
				}
			}
		});
		//右クリックで対象行を選択
		tableMenu.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						int rowPoint = alarmTable.rowAtPoint(SwingUtilities.convertPoint(tableMenu, new Point(0, 0), alarmTable));
						if (rowPoint > -1) {
							alarmTable.setRowSelectionInterval(rowPoint, rowPoint);
						}
					}
				});
			}
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});
		tableMenu.add(menuItem);
		alarmTable.setComponentPopupMenu(tableMenu);

		panel.add(tablePanel);

		JPanel endButtonPanel = new JPanel();
		FlowLayout endButtonLayout = new FlowLayout();
		endButtonLayout.setAlignment(FlowLayout.RIGHT);
		endButtonPanel.setLayout(endButtonLayout);
		JButton endButton = new JButton("   OK   ");
		endButton.setActionCommand(Action.OK.name());
		endButton.addActionListener(this);
		endButtonPanel.add(endButton);
		panel.add(endButtonPanel);

		add(panel);
	}

	private void createTimeSelector() {
		hourSelector = new JComboBox<>();
		for (int i = 0; i < 24; i++) {
			hourSelector.addItem(i);
		}
		minutesSelector = new JComboBox<>();
		for (int i = 0; i < 60; i++) {
			minutesSelector.addItem(i);
		}
	}

	private void createSoundSelector() {
		soundSelector = new JComboBox<>();
		File soundFile = new File(GlobalConfig.getSoundDirectoryPath());
		if (soundFile == null || !soundFile.exists()) {
			System.out.println("File Directory Not Found");
			return;
		}
		for (String file : soundFile.list()) {
			if (file.contains(".wav")) {
				soundSelector.addItem(file);
			}
		}
	}

	private void createAlarmTable() {
		Map<String, AlarmData> alarmData = config.getAlarmData();
		for (Entry<String, AlarmData> data : alarmData.entrySet()) {
			AlarmData alarm = data.getValue();
			String time = adjustTimeNum(alarm.getHour()) + " : " + adjustTimeNum(alarm.getMinute());
			alarmTableModel.addRow(new Object[] {alarm.isOn(), data.getKey(), time, alarm.getSound() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		if (command.equals(Action.OK.name())) {
			for (int i = 0; i < alarmTableModel.getRowCount(); i++) {
				String identifier = (String)alarmTableModel.getValueAt(i, 1);
				Boolean isON = (Boolean)alarmTableModel.getValueAt(i, 0);
				config.setAlarmEnable(isON, identifier);
			}
			dispose();
		} else if (command.equals(Action.SAVE.name())) {
			if (validateSettings()) {
				String time = adjustTimeNum((Integer)hourSelector.getSelectedItem()) + " : " + adjustTimeNum((Integer)minutesSelector.getSelectedItem());
				alarmTableModel.addRow(new Object[] {true, alarmNameField.getText(), time, soundSelector.getSelectedItem()});
				//追加のみ/変更はできない
				AlarmData data = new AlarmData((Integer)hourSelector.getSelectedItem(), (Integer)minutesSelector.getSelectedItem(), (String)soundSelector.getSelectedItem(), true);
				config.addAlarmConf(data, alarmNameField.getText());
				if (config.existAlarmName(alarmNameField.getText())) {
					index++;
				}
				alarmNameField.setText(ALARM_NAME + index);
			}
		}
	}

	private boolean validateSettings() {
		if (alarmNameField.getText().isEmpty()) {
			return false;
		}
		if (soundSelector.getSelectedItem() == null || soundSelector.getSelectedIndex() == -1) {
			return false;
		}
		return true;
	}

	private String adjustTimeNum(Integer time) {
		if (time < 10) {
			return "0" + time;
		}
		return time.toString();
	}

	public class AlarmTableModel extends DefaultTableModel{
	    AlarmTableModel(String[] columnNames, int rowNum){
	        super(columnNames, rowNum);
	    }
	    public Class<?> getColumnClass(int col){
	        return getValueAt(0, col).getClass();
	    }
	}
}
