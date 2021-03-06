package com.manig.stockprice.io.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * holds error messages generated by quandl APIs
 *
 * @author Manishankar Goswami
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    @JsonProperty("quandl_error")
    private QuandlError quandlError;

    public Error(QuandlError quandlError) {

        this.quandlError = quandlError;
    }

    public Error() {

    }

    @JsonProperty("quandl_error")
    public QuandlError getQuandlError() {
        return quandlError;
    }

    @JsonProperty("quandl_error")
    public void setQuandlError(QuandlError quandlError) {
        this.quandlError = quandlError;
    }

}
