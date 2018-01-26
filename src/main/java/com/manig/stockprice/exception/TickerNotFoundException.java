package com.manig.stockprice.exception;

import lombok.Getter;

/**
 * This exception class is used only when ticker
 * is not found
 *
 * @author Manishankar Goswami
 */

@Getter
public class TickerNotFoundException extends RuntimeException{

    private final String msg;

    public TickerNotFoundException(final String msg) {
        super("Ticker not be found for symbol: " + msg);
        this.msg = msg;
    }
}
