package dc3_4.src.config;

import java.util.prefs.Preferences;

import dc3_4.src.Settings;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConfigUtil {

	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String BASE_PATH = System.getProperty("user.dir") + FILE_SEPARATOR;// + "dc3_4" + FILE_SEPARATOR;
	public static final String ICON_PATH = "file:///" + BASE_PATH + "configuration" + FILE_SEPARATOR + "icon.png";

	public static class PrefConfig {

		private enum SavedKey{WINDOW_TOP, WINDOW_LEFT, WINDOW_HEIGHT, WINDOW_WIDTH, FONT, FONT_SIZE, BACK_COLOR, FONT_COLOR}
		private static Preferences pref = Preferences.userRoot().node("digitalWatchFx");

		public static void savePref(Stage stage, Settings property) {
			pref.putDouble(SavedKey.WINDOW_TOP.name(), stage.getY());
			pref.putDouble(SavedKey.WINDOW_LEFT.name(), stage.getX());
			pref.putInt(SavedKey.WINDOW_WIDTH.name(), (int)property.getSize().getWidth());
			pref.putInt(SavedKey.WINDOW_HEIGHT.name(), (int)property.getSize().getHeight());
			pref.putInt(SavedKey.BACK_COLOR.name(), colorToInt(property.getBackColor()));
			pref.putInt(SavedKey.FONT_COLOR.name(), colorToInt(property.getFontColor()));
			pref.putInt(SavedKey.FONT_SIZE.name(), property.getFontSize());
			pref.put(SavedKey.FONT.name(), property.getFont());
		}

		public static Settings loadPref() {
			int fontColor = pref.getInt(SavedKey.FONT_COLOR.name(), colorToInt(Color.BLACK));
			int backColor = pref.getInt(SavedKey.BACK_COLOR.name(), colorToInt(Color.GRAY));
			Settings property = new Settings(pref.get(SavedKey.FONT.name(),
					Font.getDefault().getFamily()),
					pref.getInt(SavedKey.FONT_SIZE.name(), 34),
					new Dimension2D(pref.getInt(SavedKey.WINDOW_WIDTH.name(), 240),
							pref.getInt(SavedKey.WINDOW_HEIGHT.name(), 110)),
					intToColor(fontColor),
					intToColor(backColor));
			Dimension2D posiotion = new Dimension2D((float)pref.getDouble(SavedKey.WINDOW_LEFT.name(), 0), (float)pref.getDouble(SavedKey.WINDOW_TOP.name(), 0));
			property.setPosition(posiotion);
			return property;
		}

		private static int colorToInt(Color c) {
			int r = (int) Math.round(c.getRed() * 255);
			int g = (int) Math.round(c.getGreen() * 255);
			int b = (int) Math.round(c.getBlue() * 255);
			return (255 << 24) | (r << 16) | (g << 8) | b;
		}

		private static Color intToColor(int value) {
			int r = (value >>> 16) & 0xFF;
			int g = (value >>> 8) & 0xFF;
			int b = value & 0xFF;
			return Color.rgb(r,g,b);
		}
	}

}
