package dc3_2.src;

import com.sun.javafx.geom.Dimension2D;

import javafx.scene.paint.Color;

public class Settings {

	private String font;
	private int fontSize;
	public int getFontSize() {
		return fontSize;
	}

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
