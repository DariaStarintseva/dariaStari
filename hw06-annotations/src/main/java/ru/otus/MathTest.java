package ru.otus;

@SuppressWarnings({"java:S106", "java:S1192"})
public class MathTest {

    int number1;
    int number2;

    @Before
    public void init() {
        System.out.println("Initializing test...");
    }

    @Test
    public void sumTest() {
        System.out.println("Running sumTest...");
        number1 = 2;
        number2 = 3;
        int actual = number1 + number2;
        int expected = 6;
        if (actual != expected) {
            throw new AssertionError("Expected: " + expected + ", actual: " + actual);
        }
    }

    @Test
    public void multiplicationTest() {
        System.out.println("Running multiplicationTest...");
        number1 = 4;
        number2 = 5;
        int actual = number1 * number2;
        int expected = 20;
        if (actual != expected) {
            throw new AssertionError("Expected: " + expected + ", actual: " + actual);
        }
    }

    @Test
    public void divisionTest() {
        System.out.println("Running divisionTest...");
        number1 = 15;
        number2 = 4;
        int actual = number1 / number2;
        int expected = 5;
        if (actual != expected) {
            throw new AssertionError("Expected: " + expected + ", actual: " + actual);
        }
    }

    @Test
    public void subtractionTest() {
        System.out.println("Running subtractionTest...");
        number1 = 10;
        number2 = 4;
        int actual = number1 - number2;
        int expected = 6;
        if (actual != expected) {
            throw new AssertionError("Expected: " + expected + ", actual: " + actual);
        }
    }

    @After
    public void shutdown() {
        System.out.println("Shutting down test...");
        System.out.println();
    }
}
