package com.example.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InternationalBaggageOtherTest {

    InternationalBaggage internationalBaggage;
    private double expected;
    private Integer[] baagage_type;
    private double weight;

    @Parameterized.Parameters
    public static Collection<?> data(){
        return Arrays.asList(new Object[][]{
                {5200,new Integer[]{1,2,2},32.1},
                {0,new Integer[]{1,2,2},1.9},
                {3900,new Integer[]{1,2,2},32},
                {2600,new Integer[]{1,2,2},23},
                {3900,new Integer[]{1,2,3},32.1},
                {0,new Integer[]{1,2,3},1.9},
                {2600,new Integer[]{1,2,3},32},
                {1300,new Integer[]{1,2,3},23},
                {0,new Integer[]{1,3,2},32.1},
                {0,new Integer[]{1,3,2},1.9},
                {3900,new Integer[]{1,3,2},32},
                {490,new Integer[]{1,3,2},23},
                {0,new Integer[]{1,3,3},32.1},
                {0,new Integer[]{1,3,3},1.9},
                {2600,new Integer[]{1,3,3},32},
                {1300,new Integer[]{1,3,3},23},
                {0,new Integer[]{1,3,4},5.1},
                {0,new Integer[]{1,3,4},1.9},
                {1300,new Integer[]{1,3,4},5},
                {0,new Integer[]{1,3,5},1.9},
                {5200,new Integer[]{1,3,5},23.1},
                {3900,new Integer[]{1,3,5},23},
        });
    }

    public InternationalBaggageOtherTest(double expected, Integer[] baagage_type, double weight) {
        this.expected = expected;
        this.baagage_type = baagage_type;
        this.weight = weight;
        System.out.println("InternationalBaggageTest1");
    }

    @Before
    public void setup(){
        internationalBaggage = new InternationalBaggage();
        System.out.println("setUp");
    }

    @Test
    public void calculate_special_baggage() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object o = null;
        Method method = internationalBaggage.getClass().getDeclaredMethod("calculate_special_baggage", new Class[] {Integer[].class, Double.class});
        method.setAccessible(true);
        o = method.invoke(internationalBaggage, new Object[] {baagage_type, weight});
        assertEquals(expected, o);
        System.out.println("返回结果是：" + o.toString());
    }
}
