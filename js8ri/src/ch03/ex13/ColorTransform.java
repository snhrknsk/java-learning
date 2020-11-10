package ch03.ex13;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransform {

	Color apply(int x, int y, LatentImage colorAtXY);
}
