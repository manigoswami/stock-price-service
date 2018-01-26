package com.manig.stockprice;

import com.manig.stockprice.exception.PreconditionException;
import com.manig.stockprice.exception.TickerNotFoundException;
import com.manig.stockprice.io.IO;
import com.manig.stockprice.utils.Utils;
import com.manig.stockprice.io.output.MAFor200Days;
import com.manig.stockprice.io.output.MovingAverage;
import com.manig.stockprice.io.output.MovingAverageCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds Moving Average related use-case logic
 *
 * @author Manishankar Goswami
 */

@Service
public class MovingAverageService {
    private static final Logger LOG = LoggerFactory.getLogger(MovingAverageService.class);

    private static final int MAX_TICKER_LIMIT = 1000;

    private static final String
            MAX_TICKER_PRE_CONDITION_FAILED_EXCEPTION_MSG =
            "Max number of ticker symbol supported by " +
            "batch API is %d.";

    @Autowired
    private IO io;

    @Value("${operations.serviceURL}")
    String serviceEndPoint;

    public MovingAverage getMovingAverage(String ticker, LocalDate startDate)
            throws TickerNotFoundException, IOException {
        LocalDate endDate = startDate.plusDays(200);

        return MovingAverage.builder().maFor200Days(
                fetch200DaysMA(ticker, startDate, endDate, true)).build();
    }

    private MAFor200Days fetch200DaysMA(String ticker, LocalDate startDate, LocalDate endDate,
            boolean throwable) throws IOException {
        String url = String.format(serviceEndPoint, ticker,
                Utils.getColumnIndex(Utils.COLUMN_NAME), startDate, endDate);
        String errMsgIfAny = String.format("Unknown issue with the request. "
                + "Ticker: %s, StartDate: %s", ticker, startDate);

        LOG.info("URL: " + url);

        // had to be intercepted here to make way for embedding error message.
        try {
            io.doIO(url);
        }catch(HttpClientErrorException ex){
            if(!throwable){
                if(Utils.isTickerInvalid(ex)){
                    errMsgIfAny = String.format("Invalid ticker symbol %s", ticker);
                }
                return MAFor200Days.builder().error(errMsgIfAny).build();
            }
        }

        return io.computeMovingAvgAndPopulateOutput(io.doIO(url), ticker, throwable);
    }

    public MovingAverageCollection getMovingAverageinBatch(List<String> tickers,
            LocalDate startDate) throws IOException {
        if (tickers.size() > MAX_TICKER_LIMIT) {
            throw new PreconditionException(
                    String.format(
                            MAX_TICKER_PRE_CONDITION_FAILED_EXCEPTION_MSG, MAX_TICKER_LIMIT));
        }

        List<MAFor200Days> mAverages = new ArrayList<>();

        LocalDate endDate = startDate.plusDays(200);

        for (String ticker : tickers) {
            mAverages.add(fetch200DaysMA(ticker, startDate, endDate, false));
        }
        return MovingAverageCollection.builder().maFor200Days(mAverages).build();
    }
}
