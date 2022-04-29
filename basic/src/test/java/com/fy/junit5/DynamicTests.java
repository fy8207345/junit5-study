package com.fy.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
public class DynamicTests {

    @TestFactory
    public List<DynamicTest> dynamicTestList(){
        return Arrays.asList(
                DynamicTest.dynamicTest("test1", () -> Assertions.assertTrue(true)),
                DynamicTest.dynamicTest("test1", this::doTest),
                DynamicTest.dynamicTest("test1", () -> Assertions.assertFalse(false))
        );
    }

    private void doTest(){
        log.info("doTest");
    }

    @TestFactory
    public Stream<DynamicTest> dynamicTestStream(){
        return Stream.of("a", "b", "c", "d")
                .map(text -> DynamicTest.dynamicTest(text, () -> log.info("dynamicTestStream: {}", text)));
    }

    @TestFactory
    public Stream<DynamicTest> dynamicTestStream2(){
        Iterator<Integer> integerIterator = new Iterator<Integer>() {
            final Random random = new Random();
            int current = 0;
            @Override
            public boolean hasNext() {
                current = random.nextInt(100);
                return current % 7 != 0;
            }

            @Override
            public Integer next() {
                return current;
            }
        };

        Function<Integer, String> displayNameGenerator = integer -> "input: " + integer;
        ThrowingConsumer<Integer> executor = input -> Assertions.assertTrue(input % 7 != 0);
        return DynamicTest.stream(integerIterator, displayNameGenerator, executor);
    }
}
