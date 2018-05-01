package model;

import lombok.Data;

@Data
public class ObjectAddress {
    private String road;
    private String suburb;
    private String county;
    private String state;
    private String postalcode;
    private String country;
    private String country_code;
}
