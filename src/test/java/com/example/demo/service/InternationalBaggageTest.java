package com.example.demo.service;

import com.example.demo.dao.Info;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class InternationalBaggageTest {

    InternationalBaggage internationalBaggage;
    private Info info;
    private int[] expected;
    private Integer travellerType;
    private Integer membershipType;
    private Integer cockpitType;
    private Integer area;

    //expeced, travellerType, membershipType, cockpitType, area
    @Parameterized.Parameters
    public static Collection<?> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{32,32,32},1,1,2,1},
                {new int[]{32,32,32},1,2,1,2},
                {new int[]{32,32,23},1,3,2,3},
                {new int[]{32,32,0},1,4,1,3},
                {new int[]{23,23,0},1,4,3,4},
                {new int[]{23,23,23},1,1,3,4},
                {new int[]{23,0,0},1,4,4,1},
                {new int[]{23,0,0,},1,4,4,2},
                {new int[]{23,23,0},1,3,4,3},
                {new int[]{23,23,0},1,4,4,4},
                {new int[]{23,23,23},1,3,4,5},
                {new int[]{32,23,0},2,1,1,2},
                {new int[]{32,23,0},2,2,2,4},
                {new int[]{23,23,0},2,3,2,1},
                {new int[]{23,0,0},2,4,1,1},
                {new int[]{23,0,0},2,4,3,2},
                {new int[]{23,23,0},2,2,4,5}
        });
    }

    public InternationalBaggageTest(int[] expected, Integer travellerType, Integer membershipType, Integer cockpitType, Integer area) {
        this.expected = expected;
        this.travellerType = travellerType;
        this.membershipType = membershipType;
        this.cockpitType = cockpitType;
        this.area = area;
        System.out.println("InternationalBaggageTest");
    }

    @Before
    public void setup(){
        internationalBaggage = new InternationalBaggage();
        info = new Info();
        System.out.println("setUp");
    }

    @Test
    public void calculate_discount() {
        System.out.println("calculate_discount");
        setInfo();
        int[] result = internationalBaggage.calculate_discount(info);
        assertArrayEquals(expected, result);
        System.out.println("返回结果是：" + result);
    }

    public void setInfo(){
        info.setTravellerType(travellerType);
        info.setMembershipType(membershipType);
        info.setCockpitType(cockpitType);
        info.setArea(area);
    }
}