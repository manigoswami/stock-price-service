package com.manig.stockprice.io.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * holds price related data which is used to generate
 * response for closePrice
 *
 * @author Manishankar Goswami
 */

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {

    @JsonProperty("Ticker")
    private String ticker;

    @JsonProperty("DateClose")
    private List<List<String>> dateClose = null;

}

