package com.gc.bob.mig.restaurant;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Location {
    private String type;
    private List<Double> coordinates;
}