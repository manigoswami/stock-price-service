package com.manig.stockprice.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * This class is used in situation where no data is
 * found due to various conditions
 *
 * @author Manishankar Goswami
 */

@Getter
@Setter
public class NoDataForDateException extends RuntimeException {

    private Date startDate;
    private Date endDate;
    private String ticker;
    private Date suggestedDate;

    private final String MESSAGE_WITH_START_END_DATE =
            "No data for the supplied date found. Start Date: %s, EndDate %s. "
                    + "Try querying for date %s";

    public NoDataForDateException(Date startDate, Date endDate, String ticker,
            Date suggestedDate) {
        super(String.format("No data for ticker %s found for Start Date: %s, EndDate %s . "
                + "Suggested date for this ticker is: %s",
                ticker, startDate, endDate, suggestedDate));
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticker = ticker;
        this.suggestedDate = suggestedDate;
    }

    public NoDataForDateException(Date startDate, Date endDate,
            String ticker) {
        super(String.format("No data for ticker %s found for Start Date: %s, EndDate %s .",
                ticker, startDate, endDate));
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticker = ticker;
    }

    public NoDataForDateException(Date startDate, String ticker, Date suggestedDate) {
        super(String.format("No data for ticker %s found for Start Date: %s. Suggested "
                + "date for this ticker is: %s", ticker, startDate, suggestedDate));
        this.startDate = startDate;
        this.suggestedDate = suggestedDate;
        this.ticker = ticker;
    }
    public NoDataForDateException(String msg) {
        super(msg);
    }
}
