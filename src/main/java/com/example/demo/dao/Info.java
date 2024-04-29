package com.example.demo.dao;

public class Info {
    private Integer  travellerType;
    private Integer  membershipType;
    private Integer  cockpitType;
    private String flightType;
    private Double weight1;
    private Integer[] baggage1Type;
    private Double weight2;
    private Integer[] baggage2Type;
    private Double weight3;
    private Integer[] baggage3Type;
    private Integer area;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(Integer travellerType) {
        this.travellerType = travellerType;
    }

    public Integer getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(Integer membershipType) {
        this.membershipType = membershipType;
    }

    public Integer getCockpitType() {
        return cockpitType;
    }

    public void setCockpitType(Integer cockpitType) {
        this.cockpitType = cockpitType;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public Double getWeight1() {
        return weight1;
    }

    public void setWeight1(Double weight1) {
        this.weight1 = weight1;
    }

    public Integer[] getBaggage1Type() {
        return baggage1Type;
    }

    public void setBaggage1Type(Integer[] baggage1Type) {
        this.baggage1Type = baggage1Type;
    }

    public Double getWeight2() {
        return weight2;
    }

    public void setWeight2(Double weight2) {
        this.weight2 = weight2;
    }

    public Integer[] getBaggage2Type() {
        return baggage2Type;
    }

    public void setBaggage2Type(Integer[] baggage2Type) {
        this.baggage2Type = baggage2Type;
    }

    public Double getWeight3() {
        return weight3;
    }

    public void setWeight3(Double weight3) {
        this.weight3 = weight3;
    }

    public Integer[] getBaggage3Type() {
        return baggage3Type;
    }

    public void setBaggage3Type(Integer[] baggage3Type) {
        this.baggage3Type = baggage3Type;
    }
}
