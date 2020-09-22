package com.gc.bob.mig.restaurant;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantData {

    @CsvBindByName
    private String name;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String sg;
    @CsvBindByName
    private String sgg;
    @CsvBindByName
    private String zipcode;
    @CsvBindByName
    private String asRoad;
    @CsvBindByName
    private String asArea;
    @CsvBindByName
    private String lat;
    @CsvBindByName
    private String lon;
    @CsvBindByName
    private String phone;
    @CsvBindByName
    private String weekdayStartAt;
    @CsvBindByName
    private String weekdayEndAt;
    @CsvBindByName
    private String saturdayStartAt;
    @CsvBindByName
    private String saturdayEndAt;
    @CsvBindByName
    private String holidayStartAt;
    @CsvBindByName
    private String holidayEndAt;
    @CsvBindByName
    private String deliveryStartAt;
    @CsvBindByName
    private String deliveryEndAt;
}
