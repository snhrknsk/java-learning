package dc2_4.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class WatchProperties {

	private Color backColor;
	private Color fontColor;
	private Font font;
	private Dimension size;

	public WatchProperties(Color backColor, Color fontColor, Font font, Dimension size) {
		this.backColor = backColor;
		this.fontColor = fontColor;
		this.font = font;
		this.size = size;
	}

	public WatchProperties(WatchProperties origin) {
		this.backColor = origin.getBackColor();
		this.fontColor = origin.getFontColor();
		this.font = origin.getFont();
		this.size = origin.getSize();
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Color getBackColor() {
		return backColor;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public Font getFont() {
		return font;
	}

	public Dimension getSize() {
		return size;
	}
}
