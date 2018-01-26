package com.manig.stockprice.config;

import com.manig.stockprice.io.IO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * All bean definitions used in Stock Price API
 *
 * @author Manishankar Goswami
 */
@Configuration

public class BeanConfigurations {

    private static final Logger LOG = LoggerFactory.getLogger(BeanConfigurations.class);

    private static final int CACHE_SIZE = 10000;

    private static final int RECENTLY_USED_EXPIRY_IN_SEC = 60;

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }


    @Bean
    public IO getIO() {
        return new IO();
    }


}
