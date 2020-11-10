package ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentParallelImage {

	private final Image image;
	private final List<UnaryOperator<Color>> pendingOperations;
	private final int width;
	private final int height;

	private LatentParallelImage (Image image) {
		this.image = image;
		this.pendingOperations = new ArrayList<>();
		this.width = (int) image.getWidth();
		this.height = (int) image.getHeight();
	}

	public static LatentParallelImage from(Image in) {
		return new LatentParallelImage(in);
	}

	LatentParallelImage transform(UnaryOperator<Color> f) {
		pendingOperations.add(f);
		return this;
	}

	public Image toImage() {
		int n = Runtime.getRuntime().availableProcessors();
		Color[][] src = new Color[height][width];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				src[y][x] = image.getPixelReader().getColor(x, y);
			}
		}
		Color[][] result = new Color[height][width];
		try {
			ExecutorService pool = Executors.newCachedThreadPool();
			for (int i = 0; i < n; i++) {
				int fromY = i * height / n;
				int toY = (i + 1) * height / n;
				pool.submit(() -> {
					for (int x = 0; x < width; x++) {
						for (int y = fromY; y < toY; y++) {
							Color c = src[y][x];
							for (UnaryOperator<Color> f : pendingOperations) {
								c = f.apply(c);
							}
							result[y][x] = c;
						}
					}
				});
			}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, result[y][x]);
			}
		}
		return out;
	}
}
