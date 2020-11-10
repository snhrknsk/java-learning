package ch03.ex15;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransform {

	Color apply(int x, int y, Color colorAtXY);
}
