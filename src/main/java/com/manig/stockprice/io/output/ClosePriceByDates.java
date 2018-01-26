package com.manig.stockprice.io.output;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * It holds the close price metrics by dates
 *
 * @author manig
 *
 */

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClosePriceByDates {

    @JsonProperty("Prices")
    List<Price> prices;
}

