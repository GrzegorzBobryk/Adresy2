package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponse {
    private int place_id;
    private String licence;
    private String osm_type;
    private int osm_id;
    private float lat;
    private float lon;
    private String display_name;
    private ObjectAddress address;

    public int getPlace_id() {
        return place_id;
    }

    public String getLicence() {
        return licence;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public int getOsm_id() {
        return osm_id;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public ObjectAddress getObjectAddress() {
        return address;
    }
}
