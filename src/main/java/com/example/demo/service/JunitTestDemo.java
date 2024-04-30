package com.example.demo.service;

public class JunitTestDemo {
    public int add(int a, int b) {
        return a + b;
    }

    // 减法
    public int subtract(int a, int b) {
        return a - b;
    }

    // 乘法
    public int multiply(int a, int b) {
        return a * b;
    }

    // 除法
    public double divide(int a, int b) throws Exception {
        if (b == 0) {
            throw new Exception("除数不能为零！");
        }
        else {
            return a / b;
        }
    }
}
