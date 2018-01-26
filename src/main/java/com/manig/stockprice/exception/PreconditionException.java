package com.manig.stockprice.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * is used in cases of 412
 *
 * @author Manishankar Goswami
 */
@Getter
@Setter
public class PreconditionException extends RuntimeException {
    public PreconditionException(String message) {
        super(message);
    }
}
