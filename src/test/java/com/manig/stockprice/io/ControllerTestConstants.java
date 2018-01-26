package com.manig.stockprice.io;

import java.time.format.DateTimeFormatter;

public interface ControllerTestConstants {

     final static String TICKER_SYMBOL_FOR_TEST = "FB";

    final static String INVALID_TICKER_SYMBOL_FOR_TEST = "KLM";

     final static String EXPECTED_CLOSE_PRICE_RESPONSE =
            "{\"Prices\":[{\"Ticker\":\"FB\",\"DateClose\":" +
                    "[[\"2017-10-12\",\"172.55\"],[\"2017-10-11\",\"172.74\"]]}]}";

     final static String EXPECTED_CLOSE_PRICE_RESPONSE_WHEN_DATE_ADVANCED=
             "{\"code\":\"DATA_NOT_FOUND_FOR_DATE\",\"message\":\"No Data for ticker found for "
                     + "requested dates. The oldest available date is 2012-05-18";


     final static String EXPECTED_200DMA_RESPONSE =
            "{\"200dma\":{\"Ticker\":\"FB\",\"Avg\"";


     final static String EXPECTED_200DMA_RESPONSE_WHEN_DATE_ADVANCED =
             "{\"code\":\"DATA_NOT_FOUND_FOR_DATE\",\"message\":\"No Data for ticker FB found for"
                     + " requested dates. The oldest available date is 2012-05-18";


     final static String EXPECTED_200DMA_COLLECTION_RESPONSE =
            "{\"200dma\":[{\"Ticker\":\"FB\",\"Avg\":\"40.79\"}," +
                    "{\"Ticker\":\"AAPL\",\"Avg\":\"38.57\"},{\"Ticker\":" +
                    "\"GOOG\",\"Avg\":\"233.95\"}]}";

     final static String EXPECTED_EMBEDDED_200DMA_COLLECTION_RESPONSE =
             "{\"200dma\":[{\"Ticker\":\"FB\",\"Avg\":\"40.79\"},"
                     + "{\"error\":\"No Data for ticker YHOO found for requested dates. "
                     + "The oldest available date is 1996-04-12 and newest available date is "
                     + "2017-06-16\"},{\"Ticker\":\"GOOG\",\"Avg\":\"233.95\"}]}";

     final static String EXPECTED_RESPONSE_WITH_ONE_INVALID_TICKER_200_DMA =
             "{\"200dma\":[{\"Ticker\":\"FB\",\"Avg\":\"89.83\"},"
                     + "{\"error\":\"Invalid ticker symbol KLM\"},"
                     + "{\"Ticker\":\"GOOG\",\"Avg\":\"556.6\"}]}";




     static DateTimeFormatter parseFormatter
            = DateTimeFormatter.ofPattern("uuuu-MM-dd");

     static DateTimeFormatter invalidParseFormatter
            = DateTimeFormatter.ofPattern("MM-dd-uuuu");

     static final String[] TICKERS_AS_ARRAY = new String[]{"FB", "AAPL", "GOOG"};

     static final String PRECONDITION_RESPONE_EXPECTED=
             "{\"code\":\"PRECONDITION_FAILED\",\"message\":\"startDate must be before endDate\"}";

     static final String INVALID_START_DATE_RESPONSE_EXPECTED =
             "{\"code\":\"BAD_REQUEST\",\"message\":\"Invalid request. "
                     + "Verify value for startDate supplied\"}";

     static final String WITH_ONE_TICKER_NO_DATA = "FB,YHOO,GOOG";

    static final String INVALID_WITH_ONE_TICKER_NO_DATA = "FB,KLM,GOOG";

    static final String VALID_TICKERS = "FB,AAPL,GOOG";
}
