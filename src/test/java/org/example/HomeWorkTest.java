package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void managerFabric() throws InterruptedException {
        var queue = homeWork.managerFabric();

        queue.add(new Ticket("any1"));//1
        Thread.sleep(1000);
        queue.add(new Ticket("any2"));//2
        Thread.sleep(1000);
        queue.add(new Ticket("pension"));//3
        Thread.sleep(1000);
        queue.add(new Ticket("any3"));//4
        Thread.sleep(1000);
        queue.add(new Ticket("any4"));//5
        Thread.sleep(1000);
        queue.add(new Ticket("test5"));//6
        Thread.sleep(1000);
        queue.add(new Ticket("pension"));//7
        Assertions.assertEquals(3, queue.next().id);
        Assertions.assertEquals(7, queue.next().id);
        Assertions.assertEquals(1, queue.next().id);
        Assertions.assertEquals(2, queue.next().id);
        Assertions.assertEquals(4, queue.next().id);
        Assertions.assertEquals(5, queue.next().id);
        Assertions.assertEquals(6, queue.next().id);

        queue.add(new Ticket("pension")); //8
        Thread.sleep(1000);
        queue.add(new Ticket("any"));//9

        Assertions.assertEquals(8, queue.next().id);
        Assertions.assertEquals(9, queue.next().id);

        queue.add(new Ticket("pension")); //10
        Thread.sleep(1000);
        queue.add(new Ticket("pension")); //11
        Thread.sleep(1000);
        queue.add(new Ticket("pension")); //12

        Assertions.assertEquals(10, queue.next().id);
        Thread.sleep(1000);
        Assertions.assertEquals(11, queue.next().id);
        Thread.sleep(1000);
        Assertions.assertEquals(12, queue.next().id);

        queue.add(new Ticket("any1"));//13
        Thread.sleep(1000);
        queue.add(new Ticket("any2"));//14
        Thread.sleep(1000);
        queue.add(new Ticket("any3"));//15

        Assertions.assertEquals(13, queue.next().id);
        Assertions.assertEquals(14, queue.next().id);
        Assertions.assertEquals(15, queue.next().id);
    }

    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 4);
        List<String> pairs = generatePairs(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }


}