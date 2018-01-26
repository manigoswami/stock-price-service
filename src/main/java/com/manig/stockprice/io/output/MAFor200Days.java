package com.manig.stockprice.io.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

/**
 * holds moving average related data, used to generate
 * response for 200mda API
 *
 * @author Manishankar Goswami
 */

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MAFor200Days {

    @JsonProperty("Ticker")
    private String ticker;

    @JsonProperty("Avg")
    private String avg;

    private String error;

}
