package com.manig.stockprice.exception;

import com.manig.stockprice.StockPriceController;
import com.manig.stockprice.utils.Utils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

/**
 * this class acts as a watch for all exceptions generated
 * out of StockPrice APIs.
 *
 * @author Manishankar Goswami
 */

@ControllerAdvice
public class StockPriceContollerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(StockPriceController.class);

    private static final String INVALID_REQUEST_MESSAGE =
            "Invalid request. Verify value for %s supplied";

    @ExceptionHandler(TickerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleTickerNotFoundException(final TickerNotFoundException ex) {

        LOG.error("Ticker not found thrown", ex);
        return new ErrorResponse("TICKER_NOT_FOUND", "The ticker was not found");
    }

    @ExceptionHandler(PreconditionException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody public ErrorResponse handlePreconditionException(final PreconditionException ex) {

        LOG.error("Precondition exception", ex);
        return new ErrorResponse("PRECONDITION_FAILED", ex.getLocalizedMessage());
    }

    @ExceptionHandler(NoDataForDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody public ErrorResponse handleNoDataException(final NoDataForDateException ex) {

        LOG.error("No data for date exception", ex);
        return new ErrorResponse("DATA_NOT_FOUND_FOR_DATE", ex.getLocalizedMessage());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody public ErrorResponse handleClientException(final HttpClientErrorException ex)
            throws IOException, TickerNotFoundException {

        String errorMsg = "Encountered invalid argument";
        LOG.error("Encountered client exception: " + ex.getResponseBodyAsString());

        if (Utils.isTickerInvalid(ex)) {
            errorMsg = "Invalid ticker symbol";
        }

        return new ErrorResponse("DATA_NOT_FOUND", errorMsg);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRequestException(final MethodArgumentTypeMismatchException ex) {

        LOG.error("Invalid request", ex);
        String fieldName = ex.getName();

        return new ErrorResponse("BAD_REQUEST", String.format(INVALID_REQUEST_MESSAGE, fieldName));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleConversionException(
            final MissingServletRequestParameterException ex) {

        LOG.error("Missing End Date", ex);
        return new ErrorResponse("BAD_REQUEST", ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody public ErrorResponse handleThrowable(final Throwable ex) {

        LOG.error("Unexpected error", ex.getMessage());
        return new ErrorResponse("INTERNAL_SERVER_ERROR",
                "An unexpected internal server error occured");
    }

    /**
     * holds error response generated by our API
     *
     * @author Manishankar Goswami
     */
    @Data public static class ErrorResponse {

        private final String code;
        private final String message;
    }
}
