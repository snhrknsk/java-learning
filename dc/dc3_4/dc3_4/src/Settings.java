package dc3_4.src;

import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Settings {

	private String font;
	private int fontSize;
	private Dimension2D size;
	private Color fontColor;
	private Color backColor;
	private Dimension2D position;

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

	public int getFontSize() {
		return fontSize;
	}

	public Dimension2D getPosition() {
		return position;
	}

	public void setPosition(Dimension2D position) {
		this.position = position;
	}
}
