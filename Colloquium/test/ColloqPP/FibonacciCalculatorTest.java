package ColloqPP;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FibonacciCalculatorTest {
    @Test
    public void testCalculateFibonacciNumbers() {
        FibonacciCalculator calculator = new FibonacciCalculator();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(0);
        });

        List<Integer> expected1 = Arrays.asList(0);
        List<Integer> result1 = calculator.calculate(1);
        Assertions.assertEquals(expected1, result1);

        List<Integer> expected5 = Arrays.asList(0, 1, 1, 2, 3);
        List<Integer> result5 = calculator.calculate(5);
        Assertions.assertEquals(expected5, result5);

        List<Integer> expected10 = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        List<Integer> result10 = calculator.calculate(10);
        Assertions.assertEquals(expected10, result10);
    }
}
