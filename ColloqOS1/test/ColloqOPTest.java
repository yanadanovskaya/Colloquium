import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ColloqOPTest {
    @Test
    public void testGetFactorials() {
        List<Long> expected = Arrays.asList(1L, 1L, 2L, 6L, 24L, 120L);
        List<Long> actual = ColloqOP.getFactorials(5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetFactorialsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ColloqOP.getFactorials(-1);
        });
    }

    @Test
    public void testRemoveDuplicates() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> actual = ColloqOP.removeDuplicates(numbers);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveDuplicatesNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ColloqOP.removeDuplicates(null);
        });
    }

    @Test
    public void testReverseLinkedList() {
        ColloqOP.Node<Integer> head = new ColloqOP.Node<>(1);
        head.next = new ColloqOP.Node<>(2);
        head.next.next = new ColloqOP.Node<>(3);

        ColloqOP.Node<Integer> expected = new ColloqOP.Node<>(3);
        expected.next = new ColloqOP.Node<>(2);
        expected.next.next = new ColloqOP.Node<>(1);

        ColloqOP.Node<Integer> actual = ColloqOP.reverseLinkedList(head);
        assertLinkedListEquals(expected, actual);
    }

    private <T> void assertLinkedListEquals(ColloqOP.Node<T> expected, ColloqOP.Node<T> actual) {
        while (expected != null && actual != null) {
            Assertions.assertEquals(expected.data, actual.data);
            expected = expected.next;
            actual = actual.next;
        }
        Assertions.assertNull(expected);
        Assertions.assertNull(actual);
    }

}