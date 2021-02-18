package ch08.ex12;

public class TestClass {

    @TestCase(param=5, expected=5)
    @TestCase(param=1, expected=5)
    public static int calc(int n) {
        return n;
    }
}