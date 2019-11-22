package test;

import dssc.calculator.StringCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AddNumbersTest {

    @Test
    void testEmptyString() {
        assertThat(StringCalculator.add(""), is(0));
    }

    @Test
    void testOneNumber() {
        assertThat(StringCalculator.add("1"), is(1));
    }

    @Test
    void testTwoNumbers() {
        assertThat(StringCalculator.add("1,2"), is(3));
    }

    @Test
    void testFiveNumbers() {
        assertThat(StringCalculator.add("1,2,3,4,5"), is(15));
    }

    @Test
    void testNewLines() {
        assertThat(StringCalculator.add("1\n2,3"), is(6));
    }
}
