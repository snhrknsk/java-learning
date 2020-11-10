package ch03.ex07;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorGenerator {

	/**
     * 大文字・小文字を区別、空白を含めて比較するコンパレータを生成
     */
    public static Comparator<String> naturalOrder() {
        return (s1, s2) -> s1.compareTo(s2);
    }

    /**
     * 逆の順序で比較するコンパレータを生成
     */
    public static Comparator<String> reverseOrder() {
        // return comparator.reversed();
        return (s1, s2) -> s2.compareTo(s1);
    }

    /**
     * 大文字小文字を区別しないコンパレータを生成
     */
    public static Comparator<String> ignoreCase(Comparator<String> comparator) {
        return (s1, s2) -> comparator.compare(s1.toUpperCase(), s2.toUpperCase());
    }

    /**
     * 空白を除外するコンパレータを生成
     */
    public static Comparator<String> ignoreSpace(Comparator<String> comparator) {
        return (s1, s2) -> comparator.compare(s1.replaceAll("\\s", ""), s2.replaceAll("\\s", ""));
    }

    public static void main(String[] args) {
        Comparator<String> natural = naturalOrder();
        Comparator<String> reversed = reverseOrder();
        Comparator<String> caseAndSpaceInsensitive = ignoreCase(ignoreSpace(natural));
        String[] strings = {"aa a", "aAb", "bb", " Ba"};

        Arrays.sort(strings, natural);
        System.out.println("natural:" + Arrays.toString(strings));
        Arrays.sort(strings, reversed);
        System.out.println("reversed:" + Arrays.toString(strings));
        Arrays.sort(strings, caseAndSpaceInsensitive);
        System.out.println("Ignore case and space:" + Arrays.toString(strings));
    }
}
