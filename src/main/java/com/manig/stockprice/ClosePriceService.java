package com.manig.stockprice;

import com.manig.stockprice.exception.PreconditionException;
import com.manig.stockprice.io.IO;
import com.manig.stockprice.io.output.ClosePriceByDates;
import com.manig.stockprice.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Holds ClosePrice related service logic
 *
 * @author Manishankar Goswami
 */

@Service
public class ClosePriceService {

    private static final Logger LOG = LoggerFactory.getLogger(ClosePriceService.class);


    @Autowired
    private IO io;


    @Value("${operations.serviceURL}")
    String serviceEndPoint;

    public ClosePriceByDates getClosePriceByDates(String ticker, LocalDate startDate,
            LocalDate endDate) throws PreconditionException {

        if (!Utils.isStartLessThanEndDate(startDate, endDate)) {
            throw new PreconditionException("startDate must be before endDate");
        }

        String url = String.format(serviceEndPoint, ticker,
                Utils.getColumnIndex(Utils.COLUMN_NAME), startDate, endDate);
        LOG.info("URL: " + url);

        return io.populateOutput(io.doIO(url));
    }

}
