package com.manig.stockprice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application for Stock API
 *
 * @author Manishankar Goswami
 */
@EnableAutoConfiguration
@SpringBootApplication(
        scanBasePackages = "com.manig.stockprice")
@ComponentScan(
        basePackages = "com.manig.stockprice")

@EnableCaching
public class StockPriceApplication extends SpringBootServletInitializer {

    public static final String API_VERSION = "/v1";

    /**
     * Plain old way of starting the application :)
     *
     * @param args
     */
    public static void main(String[] args) {

        new StockPriceApplication()
                .configure(new SpringApplicationBuilder(StockPriceApplication.class)).run(args);
    }

}
