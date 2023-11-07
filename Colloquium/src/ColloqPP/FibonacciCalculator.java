package ColloqPP;

import java.util.ArrayList;
import java.util.List;

public class FibonacciCalculator {
    public List<Integer> calculate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть положительным числом.");
        }

        List<Integer> fibonacciNumbers = new ArrayList<>();

        if (n >= 1) {
            fibonacciNumbers.add(0);
        }

        if (n >= 2) {
            fibonacciNumbers.add(1);
        }

        int previous = 0;
        int current = 1;

        for (int i = 2; i < n; i++) {
            int next = previous + current;
            fibonacciNumbers.add(next);
            previous = current;
            current = next;
        }

        return fibonacciNumbers;
    }
    public static void main(String[] args) {
        FibonacciCalculator calculator = new FibonacciCalculator();

        try {
            int n = 10;
            List<Integer> fibonacciNumbers = calculator.calculate(n);

            System.out.println("Первые " + n + " чисел Фибоначчи:");
            for (int number : fibonacciNumbers) {
                System.out.print(number + " ");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

