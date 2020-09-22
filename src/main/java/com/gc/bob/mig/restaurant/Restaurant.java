package com.gc.bob.mig.restaurant;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Document(collation = "restaurant")
@Getter
@Builder
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String type;
    private Address address;
    private Location location;
    private Schedule schedule;
    private LocalDateTime updatedAt;
    private String registrationType;
    private String status;






}
