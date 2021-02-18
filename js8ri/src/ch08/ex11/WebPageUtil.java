package ch08.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Stream;

public class WebPageUtil {

	private static InputStream basicAuthentication(URL url, String user, String password) throws IOException {

		URLConnection connection = url.openConnection();
		Base64.Encoder encoder = Base64.getEncoder();
		String auth = user + ":" + password;
		auth = encoder.encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + auth);
		connection.connect();
		return connection.getInputStream();
	}

	public static void main(String[] args) {
		try {
			// ベーシック認証のサンプルサイト
			URL url = new URL("http://leggiero.sakura.ne.jp/xxxxbasic_auth_testxxxx/secret/kaiin_page_top.htm");
			String user = "kaiin";
			String password = "naisho";
			try(InputStream is = basicAuthentication(url, user, password)) {
				try(InputStreamReader inputStreamReader = new InputStreamReader(is)){
					Stream<String> stream= new BufferedReader(inputStreamReader).lines();
					stream.forEach(System.out::println);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
