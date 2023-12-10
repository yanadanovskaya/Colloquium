import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColloqOP {
    public static void main(String[] args) {

        List<Long> factorials = getFactorials(5);
        System.out.println(factorials);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(2);
        List<Integer> uniqueNumbers = removeDuplicates(numbers);
        System.out.println(uniqueNumbers);


        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);


        Node<Integer> reversedHead = reverseLinkedList(head);
        printLinkedList(reversedHead);
    }

    // Получение первых n факториалов
    public static List<Long> getFactorials(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n должно быть неотрицательным числом");
        }

        List<Long> factorials = new ArrayList<>();
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            factorials.add(factorial);
        }

        return factorials;
    }

    // Удаление дубликатов из списка
    public static List<Integer> removeDuplicates(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Список чисел не должен быть null");
        }

        Set<Integer> uniqueNumbers = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            if (!uniqueNumbers.contains(number)) {
                result.add(number);
                uniqueNumbers.add(number);
            }
        }

        return result;
    }

    // Развертывание связанного списка с использованием рекурсии
    public static <T> Node<T> reverseLinkedList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    public static <T> void printLinkedList(Node<T> head) {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}




