package com.fy.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
public class MyFirstJUnitJupiterTests {

    /**
     * 基础测试
     */
    @Test
    void name() {
        log.info("my first junit5 test");
    }

    /**
     * 参数化测试： 必须提供valuesource
     * @param s
     */
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c", "d"})
    void paramerizedTest(String s){
        log.info("paramerizedTest : {}", s);
    }

    /**
     * 重复一定次数的测试
     * @param repetitionInfo，重复的信息
     */
    @RepeatedTest(value = 3)
    public void repeatedTest(RepetitionInfo repetitionInfo){
        log.info("repeatedTest: {}", repetitionInfo);
    }
}
