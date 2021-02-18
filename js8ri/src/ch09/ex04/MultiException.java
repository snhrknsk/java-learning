package ch09.ex04;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class MultiException {

	/*
	 * 複数例外をキャッチすることで恩恵を得られる
	 * java.xml
	 * 各々のエラーに対してCatche節を書かなくて良いのはコードの見やすさや簡潔さのために良い
	 */

	public static void multiCatch() {

		// 複数まとめてキャッチ
		try {
			DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(""));
		} catch (ParserConfigurationException | SAXException | IOException e) {

		}
		// 各々キャッチ
		try {
			DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(""));
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		} catch (IOException e) {
		}
	}

	/*
	 * 共通の例外となるスーパークラスにより恩恵を得られる
	 */


}
