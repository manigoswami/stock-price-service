package com.manig.stockprice.io.input;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * this holds the data which is received from the Quandl API
 * for closePrice.
 *
 * @author Manishankar Goswami
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dataset"
})
public class ClosePriceInput {

    @JsonProperty("dataset")
    private Dataset dataset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ClosePriceInput() {

    }

    public ClosePriceInput(Dataset dataset, Map<String, Object> additionalProperties) {

    }

    @JsonProperty("dataset")
    public Dataset getDataset() {
        return dataset;
    }

    @JsonProperty("dataset")
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dataset", dataset)
                .append("additionalProperties", additionalProperties).toString();
    }

}
