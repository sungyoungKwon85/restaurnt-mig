package com.gc.bob.mig.restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigExecutor {

    private final RestaurantRepository restaurantRepository;

    @PostConstruct
    public void load() {
        List<RestaurantData> restaurantData = new ArrayList<>();
        try (Reader reader = new BufferedReader(new FileReader("restaurantData.csv"))) {
            CsvToBean<RestaurantData> csvToBean = new CsvToBeanBuilder(reader)
                .withType(RestaurantData.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

            restaurantData = csvToBean.parse();
        } catch (Exception ex) {
            log.error("Exception occurred at FileReader restaurantData.csv, {}", ex);
        }

        log.info("restaurantData.size = {}", restaurantData.size());

        List<Restaurant> restaurants = restaurantData.stream()
            .map(e -> Restaurant.builder()
                .name(e.getName())
                .type(e.getType())
                .registrationType("BOB")
                .status("OPEN")
                .updatedAt(LocalDateTime.now())
                .address(Address.builder()
                    .sg(e.getSg())
                    .sgg(e.getSgg())
                    .asRoad(e.getAsRoad())
                    .asArea(e.getAsArea())
                    .zipcode(e.getZipcode()).build())
                .location(Location.builder()
                    .type("Point")
                    .coordinates(Arrays.asList(Double.parseDouble(e.getLon()), Double.parseDouble(e.getLat()))).build())
                .schedule(Schedule.builder()
                    .weekdayStartAt(LocalTime.parse(e.getWeekdayStartAt()))
                    .weekdayEndAt(LocalTime.parse(e.getWeekdayEndAt()))
                    .saturdayStartAt(LocalTime.parse(e.getSaturdayStartAt()))
                    .saturdayEndAt(LocalTime.parse(e.getSaturdayEndAt()))
                    .holidayStartAt(LocalTime.parse(e.getHolidayStartAt()))
                    .holidayEndAt(LocalTime.parse(e.getHolidayEndAt()))
                    .deliveryStartAt(LocalTime.parse(e.getDeliveryStartAt()))
                    .deliveryEndAt(LocalTime.parse(e.getDeliveryEndAt())).build())
                .build())
            .collect(Collectors.toList());

        log.info("restaurants.size = {}", restaurants.size());

        restaurantRepository.saveAll(restaurants);

    }
}
