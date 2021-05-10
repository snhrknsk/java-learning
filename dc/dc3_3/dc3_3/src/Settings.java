package dc3_3.src;

import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Settings {

	private String font;
	private Dimension2D size;
	private Color fontColor;
	private Color backColor;

	public Settings(String font, int fontSize, Dimension2D size, Color fontColor, Color backColor) {
		this.font = font;
		this.fontSize = fontSize;
		this.size = size;
		this.fontColor = fontColor;
		this.backColor = backColor;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setSize(Dimension2D size) {
		this.size = size;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	private int fontSize;
	public int getFontSize() {
		return fontSize;
	}

	public String getFont() {
		return font;
	}

	public Dimension2D getSize() {
		return size;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public Color getBackColor() {
		return backColor;
	}
}
