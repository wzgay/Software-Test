package com.example.demo.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JunitTestDemoTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("单元测试开始前相关操作...");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("单元测试结束后相关操作...");
    }

    @Test
    public void add() {
        assertEquals(8, new JunitTestDemo().add(2,6));
    }

    @Test
    public void subtract() {
        assertEquals(7, new JunitTestDemo().subtract(9,2));
    }

    @Test
    public void multiply() {
        assertEquals(36, new JunitTestDemo().multiply(6,6));
    }

    @Test
    public void divide() throws Exception {
        assertEquals(2, new JunitTestDemo().divide(8,4),0.001);
    }
}
