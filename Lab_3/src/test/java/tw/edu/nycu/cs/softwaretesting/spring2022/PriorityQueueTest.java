package tw.edu.nycu.cs.softwaretesting.spring2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue priorityQueue;

    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new Object[]{3, 1, 4, 2, 0}, new Object[]{0, 1, 2, 3, 4}),
                Arguments.of(new Object[]{-3, -1, -4, -2, 0}, new Object[]{-4, -3, -2, -1, 0}),
                Arguments.of(new Object[]{"-3", "-1", "-4", "-2", "0"}, new Object[]{"-1", "-2", "-3", "-4", "0"}),
                Arguments.of(new Object[]{"d", "a", "c", "e", "b"}, new Object[]{"a", "b", "c", "d", "e"}),
                Arguments.of(new Object[]{'零', 'ㄧ', '二', '三', '四'}, new Object[]{'ㄧ', '三', '二', '四', '零'})
        );
    }

    @BeforeEach
    void setUp() {
        priorityQueue = new PriorityQueue();
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    void testPriorityQueuePoll(Object[] randomArray, Object[] correctArray) {
        Object s;
        int index = 0;
        Object[] resultArray = new Object[randomArray.length];
        priorityQueue.addAll(List.of(randomArray));
        while ((s = priorityQueue.poll()) != null) {
            resultArray[index++] = s;
        }
        assertArrayEquals(correctArray, resultArray);
    }

    @Test
    void assertThrowNullPointerExceptionWhenOfferIsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            priorityQueue.offer(null);
        });
        assertEquals(null, exception.getMessage());
    }

    @Test
    void assertThrowClassCastExceptionWhenAddBadObjs() {
        ClassCastException exception = assertThrows(ClassCastException.class, () -> {
            priorityQueue.add(new Object[]{1, 1});
        });
        assertTrue(exception.getMessage().contains("cannot be cast to class java.lang.Comparable"));
    }

    @Test
    void assertThrowArrayStoreException() {
        priorityQueue.add(-0);
        ArrayStoreException exception = assertThrows(ArrayStoreException.class, () -> {
            priorityQueue.toArray(new String[]{});
        });
        assertTrue(exception.getMessage().contains("arraycopy: element type mismatch: can not cast one of the elements of"));
    }
}