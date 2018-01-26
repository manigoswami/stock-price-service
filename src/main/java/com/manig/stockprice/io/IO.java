package com.manig.stockprice.io;


import com.manig.stockprice.exception.NoDataForDateException;
import com.manig.stockprice.io.input.ClosePriceInput;
import com.manig.stockprice.io.input.Dataset;
import com.manig.stockprice.io.output.ClosePriceByDates;
import com.manig.stockprice.io.output.MAFor200Days;
import com.manig.stockprice.io.output.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstracts out all IO related operations
 *
 * @author Manishankar Goswami
 */

@CacheConfig(cacheNames = {"close-price-by-dates"})
public class IO {

    private static final Logger LOG = LoggerFactory.getLogger(IO.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CacheManager cacheManager;

    @Cacheable(value = "close-price-by-dates")
    public ClosePriceInput doIO(String serviceEndPoint) {

        LOG.info("Making request for service end-point {}", serviceEndPoint);
        return restTemplate.getForObject(serviceEndPoint, ClosePriceInput.class);
    }

    public ClosePriceByDates populateOutput(ClosePriceInput input) {
        String msg;
        Dataset dataset = input.getDataset();
        List<List<String>> data = dataset.getData();
        LOG.info("Data size received: " + data.size());

        if (data.isEmpty()) {
            if (!dataset.getNewestAvailableDate().isEmpty()) {
                String newestAvailableDate = dataset.getNewestAvailableDate();
                String oldestAvailableDate = dataset.getOldestAvailableDate();
                msg = String.format(
                        "No Data for ticker found for requested dates. " +
                                "The oldest available date is %s and newest available date is %s",
                        oldestAvailableDate, newestAvailableDate);
                throw new NoDataForDateException(msg);
            }
        }

        Price price = Price.builder().ticker(dataset.getDatasetCode()).dateClose(data).build();

        List<Price> prices = new ArrayList<>();
        prices.add(price);

        return ClosePriceByDates.builder().prices(prices).build();
    }

    public MAFor200Days computeMovingAvgAndPopulateOutput(ClosePriceInput input,
            String ticker, boolean throwable) throws NoDataForDateException {
        String msg = "No Data found for the requested date";

        List<Double> closePrices = new ArrayList<>();

        Dataset dataset = input.getDataset();

        List<List<String>> data = dataset.getData();

        if (data.isEmpty()) {
            if (!dataset.getNewestAvailableDate().isEmpty()) {
                String newestAvailableDate = dataset.getNewestAvailableDate();
                String oldestAvailableDate = dataset.getOldestAvailableDate();
                msg = String.format(
                        "No Data for ticker %s found for requested dates. " +
                                "The oldest available date is %s and newest available date is %s",
                        ticker, oldestAvailableDate, newestAvailableDate);
            }
            if(throwable) {
                throw new NoDataForDateException(msg);
            }

            return MAFor200Days.builder().error(msg).build();
        }

        for (List<String> innerNode : data) {
            closePrices.add(Double.valueOf(innerNode.get(1)));
        }

        return MAFor200Days.builder()
                .ticker(ticker)
                .avg(new DecimalFormat("#.##")
                        .format(((closePrices.stream()
                                .reduce((a, b) -> (a + b)).get()) / 200))).build();
    }
}
