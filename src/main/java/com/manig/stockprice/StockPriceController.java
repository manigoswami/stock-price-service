package com.manig.stockprice;

import com.manig.stockprice.io.output.ClosePriceByDates;
import com.manig.stockprice.io.output.MovingAverage;
import com.manig.stockprice.io.output.MovingAverageCollection;
import com.manig.stockprice.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.time.LocalDate;

/**
 * Primary controller for Stock Price related APIs
 *
 * @author Manishankar Goswami
 */

@RestController
@RequestMapping("/api")
public class StockPriceController {

    private static final Logger LOG = LoggerFactory.getLogger(StockPriceController.class);

    @Autowired
    private ClosePriceService closePriceService;

    @Autowired
    private MovingAverageService movingAverageService;


    @CrossOrigin
    @RequestMapping(value = "/v1/{tickerSymbol}/close-price", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClosePriceByDates getClosePriceByDates(
            @PathVariable("tickerSymbol") String tickerSymbol,
            @RequestParam("startDate")
            @DateTimeFormat(pattern = "uuuu-MM-dd")
                    LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(pattern = "uuuu-MM-dd")
                    LocalDate endDate)
            throws IllegalArgumentException {

        LOG.info("close price call for ticker {}, with filter",
                tickerSymbol);

        return closePriceService.getClosePriceByDates(tickerSymbol, startDate, endDate);
    }

    @CrossOrigin
    @RequestMapping(value = "/v1/{tickerSymbol}/moving-average-200", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MovingAverage getMovingAverageFor200Days(
            @PathVariable("tickerSymbol") String tickerSymbol,
            @RequestParam("startDate")
            @DateTimeFormat(pattern = "uuuu-MM-dd")
                    LocalDate startDate
    ) throws IllegalArgumentException, IOException {

        LOG.info("close price call for ticker {}",
                tickerSymbol);

        return movingAverageService.getMovingAverage(tickerSymbol, startDate);
    }


    @CrossOrigin
    @RequestMapping(value = "/v1/collection/moving-average-200", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MovingAverageCollection getMovingAverageFor200DaysCollection(
            @RequestParam("tickers") String tickerSymbols,
            @RequestParam("startDate")
            @DateTimeFormat(pattern = "uuuu-MM-dd")
                    LocalDate startDate
    ) throws IllegalArgumentException, IOException {

        LOG.info("close price call for ticker {}",
                tickerSymbols);

        return movingAverageService.getMovingAverageinBatch(
                Utils.convertToList(tickerSymbols), startDate);
    }
}
/*
@ToDO:
1. Basic Framework (DONE)
2. Working example with Mocked Data for all 3 end-points (DONE)
3. Unit test basic setup (DONE)
4. Caching (DONE)

5. Think about pagination and impln if required
6. Complete test cases including caching
7. Error handling
8. Integrate REST
9. Documentation
10. README
 */
