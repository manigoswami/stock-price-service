package com.manig.stockprice.io.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

/**
 * holds moving average data which is used to generate
 * response for 200dma api
 *
 * @author Manishankar Goswami
 */

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovingAverage {

    @JsonProperty("200dma")
    private MAFor200Days maFor200Days;

}
