package com.manig.stockprice.io.output;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * holds data which is used generate response
 * for collection/200dma API
 *
 * @author Manishankar Goswami
 */

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovingAverageCollection {

    @JsonProperty("200dma")
    private List<MAFor200Days> maFor200Days;

}
