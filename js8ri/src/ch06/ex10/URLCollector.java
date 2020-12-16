package ch06.ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLCollector {

	private static String HREF_TAG ="href=\"(.+?)\"";
	String s = "(";

	public static List<String> getLink(String target) throws MalformedURLException {
		List<String> links = new ArrayList<>();
		URL url = new URL(target);
		Pattern ptn = Pattern.compile(HREF_TAG);

		CompletableFuture.supplyAsync(() -> {
			StringBuilder sb = new StringBuilder();
			try {
				try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {

					String line;
					while((line = br.readLine()) != null) {
						sb.append(line);
						sb.append("\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}).thenApply((s) -> {
			Matcher matcher = ptn.matcher(s);
			while(matcher.find()) {
				links.add(matcher.group(1));
			}
			return links;
		}).thenAccept(link -> {
			link.stream().forEach(System.out::println);
		});
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
		return links;
	}

	public static void main(String[] args) throws MalformedURLException {
		String url = "https://www.yahoo.co.jp/";
		getLink(url);
	}
}
