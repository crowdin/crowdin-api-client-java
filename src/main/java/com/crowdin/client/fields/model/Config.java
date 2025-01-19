package com.crowdin.client.fields.model;

import lombok.Data;

import java.util.List;

@Data
public class Config {
    private List<Option> options;
    private List<Location> locations;
    private Integer min;
    private Integer max;
    private String units;

    @Data
    public static class Option {
        private String label;
        private String value;
    }

    @Data
    public static class Location {
        private String place;
    }
}
