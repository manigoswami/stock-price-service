package com.manig.stockprice.utils;

import com.manig.stockprice.io.ControllerTestConstants;
import com.manig.stockprice.io.output.ClosePriceByDates;
import com.manig.stockprice.io.output.MAFor200Days;
import com.manig.stockprice.io.output.MovingAverage;
import com.manig.stockprice.io.output.MovingAverageCollection;
import com.manig.stockprice.io.output.Price;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerTestUtil {
    public static MovingAverageCollection populateWithMACollectionDummyValue() {

        List<String> tickers = Arrays.asList(ControllerTestConstants.VALID_TICKERS);
        List<MAFor200Days> mAverages = new ArrayList<>();

        for (String ticker : tickers) {
            MAFor200Days maFor200Days = MAFor200Days.builder().ticker(ticker).avg("42.34").build();
            mAverages.add(maFor200Days);
        }

        MovingAverageCollection maBatch = MovingAverageCollection.builder().maFor200Days(mAverages).build();
        return maBatch;
    }

    public static MovingAverage populateWithMADummyValue() {
        MAFor200Days maFor200Days = MAFor200Days.builder().ticker("FB").avg("42.34").build();

        MovingAverage ma = MovingAverage.builder().maFor200Days(maFor200Days).build();
        return ma;
    }

    public static ClosePriceByDates populateWithClosePriceDummyValue() {
        List<String> priceNode1 = new ArrayList<>();
        priceNode1.add("2017-10-11");
        priceNode1.add("21.34");

        List<String> priceNode2 = new ArrayList<>();
        priceNode2.add("2017-10-12");
        priceNode2.add("22.34");


        List<List<String>> primary = new ArrayList<>();
        primary.add(priceNode1);
        primary.add(priceNode2);

        Price price = Price.builder().ticker("FB").dateClose(primary).build();

        List<Price> prices = new ArrayList<>();
        prices.add(price);

        return ClosePriceByDates.builder().prices(prices).build();
    }

    public static ClosePriceByDates populateWithClosePriceDummyValueNew() {
        List<String> priceNode1 = new ArrayList<>();
        priceNode1.add("2017-10-11");
        priceNode1.add("25.75");

        List<String> priceNode2 = new ArrayList<>();
        priceNode2.add("2017-10-12");
        priceNode2.add("42.21");


        List<List<String>> primary = new ArrayList<>();
        primary.add(priceNode1);
        primary.add(priceNode2);

        Price price = Price.builder().ticker("FB").dateClose(primary).build();

        List<Price> prices = new ArrayList<>();
        prices.add(price);

        return ClosePriceByDates.builder().prices(prices).build();
    }

}
