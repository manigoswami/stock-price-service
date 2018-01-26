package com.manig.stockprice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manig.stockprice.io.input.Error;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * holds utility methods
 *
 * @author Manishankar Goswami
 */
public class Utils {

    public static final String COMMA_DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String TICKER_NOT_FOUND_ERROR_CODE = "QECx02";

    private static final List<String> VALID_COLUMNS = Arrays
            .asList(new String[]{
                    "Date", "Open", "High", "Low", "Close", "Volume",
                    "Ex-Dividend", "Split Ratio", "Adj. Open", "Adj. High",
                    "Adj. Low", "Adj. Close", "Adj. Volume"});

    public static final String COLUMN_NAME = "Close";

    public static boolean isTickerInvalid(HttpClientErrorException ex) throws IOException {

        Error error = mapper.readValue(ex.getResponseBodyAsString(), Error.class);

        if(error.getQuandlError().getCode().equals(TICKER_NOT_FOUND_ERROR_CODE)){
            return true;
        }

        return false;
    }
    public static List<String> convertToList(String tickers) {
        return Arrays.asList(tickers.split(COMMA_DELIMITER))
                .stream()
                .map(item -> StringUtils.trimAllWhitespace(item))
                .collect(Collectors.toList());
    }

    public static boolean isStartLessThanEndDate(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    public static int getColumnIndex(String columnName) {
        // @ToDo: Ideally it should call the metadata api to retrieve column and cache
        return VALID_COLUMNS.indexOf(columnName);
    }
}
