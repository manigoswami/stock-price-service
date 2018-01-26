package com.manig.stockprice.io.output;

import java.util.Date;
import java.util.List;

import com.google.common.base.MoreObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Encapsulates all filters for metrics API
 * 
 * @author rmalurs
 */
@AllArgsConstructor
@Getter
public class FilterSpecification {

    private Date startDate;

    private Date endDate;


    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this).add("startDate", startDate)
                .add("endDate", endDate)
                .toString();
    }
}
