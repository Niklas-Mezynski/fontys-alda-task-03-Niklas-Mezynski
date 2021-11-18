package queueTests;

import asortingservice.DoubleLinkedQueue;
import asortingservice.SimpleQueue;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DoubleLinkedQueueTest {

    @Test
    void t01basicTest() {
        SoftAssertions s = new SoftAssertions();
        DoubleLinkedQueue<Integer> queue = new DoubleLinkedQueue<>();
        s.assertThat(queue.isEmpty()).isTrue();
        queue.put(1);
        queue.put(2);
        queue.put(3);
        s.assertThat(queue.size()).isEqualTo(3);
        s.assertThat(queue.isEmpty()).isFalse();
        s.assertThat(queue.get()).isEqualTo(1);
        s.assertThat(queue.get()).isEqualTo(2);
        s.assertThat(queue.get()).isEqualTo(3);
        s.assertThat(queue.isEmpty()).isTrue();
        s.assertThat(queue.size()).isEqualTo(0);
        queue.put(7);
        s.assertThat(queue.get()).isEqualTo(7);
        s.assertAll();
    }

    @Test
    void t02iterator() {
        DoubleLinkedQueue<Integer> queue = new DoubleLinkedQueue<>();
        queue.put(1);
        queue.put(2);
        queue.put(3);
        assertThat(queue).containsExactlyElementsOf(List.of(1, 2, 3));

        queue.get();
        queue.get();
        queue.get();
        queue.put(2);
        queue.put(4);
        queue.put(6);
        assertThat(queue).containsExactlyElementsOf(List.of(2, 4, 6));
    }

    @Test
    void t03removeToManyElements() {
        DoubleLinkedQueue<Integer> integers = new DoubleLinkedQueue<>();
        assertThatCode(integers::get).isExactlyInstanceOf(NullPointerException.class);
    }
}
