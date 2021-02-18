package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebPageUtil {

	public static void saveWebPageContent(String url, String dest) throws IOException {

		URI u = URI.create(url);
		try (InputStream in = u.toURL().openStream()) {
			Files.copy(in, Paths.get(dest));
		}
	}
}
