package com.klm.demo.artificats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

abstract class CoordinatesMixin {

    @JsonCreator
    CoordinatesMixin(@JsonProperty("latitude") double latitude,
                     @JsonProperty("longitude") double longitude){
    }

}
