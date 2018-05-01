package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
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
}
