package test;

import dssc.calculator.NegativeNumberException;
import dssc.calculator.StringCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddNumbersTest {

    @Test
    void testEmptyString() throws NegativeNumberException {
        assertThat(StringCalculator.add(""), is(0));
    }

    @Test
    void testOneNumber() throws NegativeNumberException {
        assertThat(StringCalculator.add("1"), is(1));
    }

    @Test
    void testTwoNumbers() throws NegativeNumberException {
        assertThat(StringCalculator.add("1,2"), is(3));
    }

    @Test
    void testFiveNumbers() throws NegativeNumberException {
        assertThat(StringCalculator.add("1,2,3,4,5"), is(15));
    }

    @Test
    void testNewLines() throws NegativeNumberException {
        assertThat(StringCalculator.add("1\n2,3"), is(6));
    }

    @Test
    void testDifferentDelimiters() throws NegativeNumberException {
        assertThat(StringCalculator.add("//;\n1;2"), is(3));
    }

    @Test
    void testNegativeNumbers() throws NegativeNumberException {
        NegativeNumberException thrown = assertThrows(NegativeNumberException.class, () -> {
            StringCalculator.add("-1,2,3,-2");
        });
        assertTrue(thrown.getMessage().contains("Negatives not allowed: [-1, -2]"));
    }

    @Test
    void testBigNumbers() throws NegativeNumberException {
        assertThat(StringCalculator.add("2,1001"), is(2));
    }

    @Test
    void testLongDelimiters() throws NegativeNumberException {
        assertThat(StringCalculator.add("//[:::]\n1:::2:::3"), is(6));
    }

}
