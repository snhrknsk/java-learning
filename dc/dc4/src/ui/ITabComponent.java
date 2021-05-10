package src.ui;

import javax.swing.JPanel;

/** If you create implemented class, add {@link HomeUI}'s tab panel */
public interface ITabComponent {

	public String getTabName();
	public JPanel createPanel();
	public void updateComponent();
	default public void updateByConstantInterval(){};
}
