package com.example.demo.service;

import com.example.demo.dao.Info;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
@RunWith(Parameterized.class)
public class DomesticBaggageTest {

    DomesticBaggage domesticBaggage;
    private Info info;
    private double yuan_pre_kilogram = 500 * 0.015;
    private double expected;
    private Integer travellerType;
    private Integer membershipType;
    private Integer cockpitType;
    private double weight;

    @Parameterized.Parameters
    public static Collection<?> data(){
        return Arrays.asList(new Object[][]{
                {7.5,2,1,1,41},
                {0,2,1,1,40},
                {7.5,2,2,2,31},
                {0,2,3,3,30},
                {7.5,2,4,4,11},
                {0,2,4,2,10},
                {7.5,1,1,1,71},
                {0,1,1,1,70},
                {7.5,1,3,1,61},
                {0,1,2,1,60},
                {7.5,1,4,1,41},
                {0,1,4,1,40},
                {7.5,1,1,2,61},
                {0,1,1,2,60},
                {7.5,1,2,2,51},
                {0,1,3,2,50},
                {7.5,1,4,2,31},
                {0,1,4,2,30},
                {7.5,1,1,3,51},
                {0,1,1,4,50},
                {7.5,1,3,4,41},
                {0,1,2,3,40},
                {7.5,1,4,3,21},
                {0,1,4,4,20}
        });
    }

    public DomesticBaggageTest(double expected, Integer travellerType, Integer membershipType, Integer cockpitType, double weight) {
        this.expected = expected;
        this.travellerType = travellerType;
        this.membershipType = membershipType;
        this.cockpitType = cockpitType;
        this.weight = weight;
        System.out.println("DomesticBaggageTest");
    }

    @Before
    public void setup(){
        domesticBaggage = new DomesticBaggage();
        info = new Info();
        System.out.println("setUp");
    }

    @Test
    public void calculate_baggage_in_free() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("calculate_baggage_in_free");
        setInfo();
        double result = domesticBaggage.calculate_baggage_in_free(info, weight, membershipType, yuan_pre_kilogram);
        assertEquals(expected, result, 0);
        System.out.println("返回结果是：" + result);
    }

    public void setInfo(){
        info.setTravellerType(travellerType);
        info.setMembershipType(membershipType);
        info.setCockpitType(cockpitType);
    }
}