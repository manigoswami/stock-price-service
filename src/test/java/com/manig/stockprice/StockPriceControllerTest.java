package com.manig.stockprice;

import com.manig.stockprice.exception.TickerNotFoundException;
import com.manig.stockprice.io.ControllerTestConstants;
import com.manig.stockprice.io.output.ClosePriceByDates;
import com.manig.stockprice.io.output.MovingAverage;
import com.manig.stockprice.utils.ControllerTestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

/**
 * Test cases for StockPriceController
 *
 * @author manig
 */

@RunWith(SpringRunner.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ActiveProfiles("test")
@WebMvcTest(value = StockPriceController.class, secure = false)
public class StockPriceControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(StockPriceControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClosePriceService closePriceService;

    @Mock
    private MovingAverageService movingAverageService;

    @InjectMocks
    private StockPriceController stockPriceController;


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getClosePriceByDatesCase1() throws Exception {

        LocalDate startDate = LocalDate.parse("2017-10-11", ControllerTestConstants.parseFormatter);
        LocalDate endDate = LocalDate.parse("2017-10-12", ControllerTestConstants.parseFormatter);

        ClosePriceByDates closePriceByDates = ClosePriceByDates.builder().build();
        when(closePriceService
                .getClosePriceByDates(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate,
                        endDate)).thenReturn(ControllerTestUtil.populateWithClosePriceDummyValue());

        //TEST CASE 1: VALID RESPONSE
        String url = String.format("/api/v1/%s/close-price?startDate=%s&endDate=%s",
                ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate, endDate);

        LOG.info("getClosePriceByDates() TEST CASE 1::URL: {}", url);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andDo(document("getClosePriceByDates")).andReturn();

        JSONAssert.assertEquals(ControllerTestConstants.EXPECTED_CLOSE_PRICE_RESPONSE,
                result.getResponse().getContentAsString(), false);
    }
    @Test
    public void getClosePriceByDatesCase2() throws Exception {
        //TEST CASE 2: INVALID DATE REQUEST

        LocalDate startDate = LocalDate.parse("2017-10-11", ControllerTestConstants.parseFormatter);
        LocalDate endDate = LocalDate.parse("2017-10-12", ControllerTestConstants.parseFormatter);

        String url = String.format("/api/v1/%s/close-price?startDate=23-12-2017&endDate=24-12-2017",
                ControllerTestConstants.TICKER_SYMBOL_FOR_TEST);

        LOG.info("getClosePriceByDates() TEST CASE 2::URL: {}", url);

        when(closePriceService
                .getClosePriceByDates(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate,
                        endDate)).thenReturn(ControllerTestUtil.populateWithClosePriceDummyValue());

        RequestBuilder requestBuilderInvalid = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

        MvcResult resultInvalid = mockMvc.perform(requestBuilderInvalid)
                .andDo(document("getClosePriceByDates-InvalidDate")).andReturn();

        Assert.assertEquals(400, resultInvalid.getResponse().getStatus());

        Assert.assertEquals(ControllerTestConstants.INVALID_START_DATE_RESPONSE_EXPECTED,
                resultInvalid.getResponse().getContentAsString());
    }
    @Test
    public void getClosePriceByDatesCase3() throws Exception {
        //TEST CASE 3: END DATE BEFORE START REQUEST

        LocalDate startDate = LocalDate.parse("2017-10-11", ControllerTestConstants.parseFormatter);
        LocalDate endDate = LocalDate.parse("2017-10-12", ControllerTestConstants.parseFormatter);

        String url =String.format(
                "/api/v1/%s/close-price?startDate=%s&endDate=%s",
                ControllerTestConstants.TICKER_SYMBOL_FOR_TEST,
                endDate, startDate);

        LOG.info("getClosePriceByDates() TEST CASE 3::URL: {}", url);

        when(closePriceService
                .getClosePriceByDates(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate,
                        endDate)).thenReturn(ControllerTestUtil.populateWithClosePriceDummyValue());

        RequestBuilder requestBuilderInvalidDate = MockMvcRequestBuilders.get(
                url).accept(
                MediaType.APPLICATION_JSON);

        MvcResult resultInvalidDate = mockMvc.perform(requestBuilderInvalidDate)
                .andDo(document("getClosePriceByDates-InvalidRequest")).andReturn();

        Assert.assertEquals(412, resultInvalidDate.getResponse().getStatus());

        Assert.assertEquals(ControllerTestConstants.PRECONDITION_RESPONE_EXPECTED,
                resultInvalidDate.getResponse().getContentAsString());

    }
    @Test
    public void getClosePriceByDatesCase4() throws Exception {

        LocalDate startDate = LocalDate.parse("2017-10-11",
                ControllerTestConstants.parseFormatter);
        LocalDate endDate = LocalDate.parse("2017-10-12",
                ControllerTestConstants.parseFormatter);

        when(closePriceService.getClosePriceByDates(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST,
                startDate, endDate)).thenThrow(new TickerNotFoundException(
                        ControllerTestConstants.TICKER_SYMBOL_FOR_TEST));

        RequestBuilder requestBuilderNotFound = MockMvcRequestBuilders.get(
                String.format("/api/v1/%s/close-price?startDate=%s&endDate=%s",
                        ControllerTestConstants.INVALID_TICKER_SYMBOL_FOR_TEST,
                        startDate, endDate)).accept(
                MediaType.APPLICATION_JSON);

        MvcResult resultNotFound = mockMvc.perform(requestBuilderNotFound)
                .andDo(document("getClosePriceByDates-NotFound"))
                .andReturn();

        Assert.assertEquals(404, resultNotFound.getResponse().getStatus());
    }

    @Test
    public void getClosePriceByDatesCase5() throws Exception {
        //TEST CASE 5: Advance dates

        LocalDate startDate = LocalDate.parse("2019-12-12", ControllerTestConstants.parseFormatter);
        LocalDate endDate = LocalDate.parse("2019-12-14", ControllerTestConstants.parseFormatter);

        String url =String.format(
                "/api/v1/%s/close-price?startDate=%s&endDate=%s",
                ControllerTestConstants.TICKER_SYMBOL_FOR_TEST,
                startDate, endDate);

        LOG.info("getClosePriceByDates() TEST CASE 5::URL: {}", url);

        when(closePriceService
                .getClosePriceByDates(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate,
                        endDate)).thenReturn(ControllerTestUtil.populateWithClosePriceDummyValue());

        RequestBuilder requestBuilderInvalidDate = MockMvcRequestBuilders.get(
                url).accept(
                MediaType.APPLICATION_JSON);

        MvcResult resultInvalidDate = mockMvc.perform(requestBuilderInvalidDate)
                .andDo(document("getClosePriceByDates-Dates-In-Advance")).andReturn();

        Assert.assertEquals(404, resultInvalidDate.getResponse().getStatus());

        /* Not comparing the end date in response as that is dynamic and will change based on
           current date. */
        Assert.assertEquals(ControllerTestConstants
                        .EXPECTED_CLOSE_PRICE_RESPONSE_WHEN_DATE_ADVANCED,
                resultInvalidDate.getResponse().getContentAsString().substring(0, 130));

    }

    @Test
    public void getMovingAverageFor200DaysCase1() throws Exception {

        LocalDate startDate = LocalDate.parse("2017-10-11",
                ControllerTestConstants.parseFormatter);

        MovingAverage movingAverage = MovingAverage.builder().build();
        when(movingAverageService
                .getMovingAverage(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate))
                .thenReturn(ControllerTestUtil.populateWithMADummyValue());

        //TEST CASE 1: VALID REQUEST
        String url = String.format("/api/v1/%s/moving-average-200?startDate=%s",
                ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate);

        LOG.info("getMovingAverageFor200Days() TEST CASE 1::URL: {}", url);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(document("getMovingAverageFor200Days")).andReturn();

        Assert.assertEquals(ControllerTestConstants.EXPECTED_200DMA_RESPONSE,
                result.getResponse().getContentAsString().substring(0, 30));
    }
    @Test
    public void getMovingAverageFor200DaysCase2() throws Exception {
        //TEST CASE 2: INVALID DATE REQUEST

        LocalDate startDate = LocalDate.parse("2017-10-11",
                ControllerTestConstants.parseFormatter);

        String url = String.format("/api/v1/%s/moving-average-200?startDate=23-12-2017",
                ControllerTestConstants.TICKER_SYMBOL_FOR_TEST);

        LOG.info("getMovingAverageFor200Days() TEST CASE 2::URL: {}", url);

        when(movingAverageService
                .getMovingAverage(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate))
                .thenReturn(ControllerTestUtil.populateWithMADummyValue());

        RequestBuilder requestBuilderInvalid = MockMvcRequestBuilders.get(
                url).accept(
                MediaType.APPLICATION_JSON);

        MvcResult resultInvalid = mockMvc.perform(requestBuilderInvalid)
                .andDo(document("getMovingAverageFor200Days-Invalid Date"))
                .andReturn();

        Assert.assertEquals(400, resultInvalid.getResponse().getStatus());
        Assert.assertEquals(
                ControllerTestConstants.INVALID_START_DATE_RESPONSE_EXPECTED,
                resultInvalid.getResponse().getContentAsString());

    }
    @Test
    public void getMovingAverageFor200DaysCase3() throws Exception {
        //TEST CASE 3: INVALID TICKER - NOT FOUND

        LocalDate startDate = LocalDate.parse("2016-12-11",
                ControllerTestConstants.parseFormatter);

        String url = String.format("/api/v1/%s/moving-average-200?startDate=%s",
                ControllerTestConstants.INVALID_TICKER_SYMBOL_FOR_TEST, startDate);

        LOG.info("getMovingAverageFor200Days() TEST CASE 3::URL: {}", url);

        when(movingAverageService
                .getMovingAverage(ControllerTestConstants.INVALID_TICKER_SYMBOL_FOR_TEST, startDate))
                .thenReturn(ControllerTestUtil.populateWithMADummyValue());

        RequestBuilder requestBuilderInvalid = MockMvcRequestBuilders.get(
                url).accept(
                MediaType.APPLICATION_JSON);

        MvcResult resultInvalid = mockMvc.perform(requestBuilderInvalid)
                .andDo(document("getMovingAverageFor200Days-NotFound"))
                .andReturn();

        Assert.assertEquals(404, resultInvalid.getResponse().getStatus());
    }

    @Test
    public void getMovingAverageFor200DaysCase4() throws Exception {
        //TEST CASE 3: DATES IN FUTURE - NOT FOUND

        LocalDate startDate = LocalDate.parse("2019-12-12",
                ControllerTestConstants.parseFormatter);

        String url = String.format("/api/v1/%s/moving-average-200?startDate=%s",
                ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate);

        LOG.info("getMovingAverageFor200Days() TEST CASE 4::URL: {}", url);

        when(movingAverageService
                .getMovingAverage(ControllerTestConstants.TICKER_SYMBOL_FOR_TEST, startDate))
                .thenReturn(ControllerTestUtil.populateWithMADummyValue());

        RequestBuilder requestBuilderInvalid = MockMvcRequestBuilders.get(
                url).accept(
                MediaType.APPLICATION_JSON);

        MvcResult resultInvalid = mockMvc.perform(requestBuilderInvalid)
                .andDo(document("getMovingAverageFor200Days-Dates-In-Future"))
                .andReturn();

        Assert.assertEquals(404, resultInvalid.getResponse().getStatus());

        Assert.assertEquals(ControllerTestConstants
                        .EXPECTED_200DMA_RESPONSE_WHEN_DATE_ADVANCED,
                resultInvalid.getResponse().getContentAsString().substring(0, 133));
    }

    @Test
    public void getMovingAverageFor200DaysCollectionCase1() throws Exception {

        LocalDate startDate = LocalDate.parse("2017-10-11",
                ControllerTestConstants.parseFormatter);
        //CASE 1: ALL VALID TICKER CASE
        String url = String.format("/api/v1/collection/moving-average-200?tickers=%s&startDate=%s",
                ControllerTestConstants.VALID_TICKERS, startDate);
        LOG.info("getMovingAverageFor200DaysCollection() TEST CASE 1::URL: {}", url);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(document("getMovingAverageFor200DaysCollection")).andReturn();

        Assert.assertEquals(200,
                result.getResponse().getStatus());
    }
    @Test
    public void getMovingAverageFor200DaysCollectionCase2() throws Exception {

        LocalDate startDate = LocalDate.parse("2017-10-11",
                ControllerTestConstants.parseFormatter);

        //CASE 2: NO DATA FOR ONLY ONE TICKER, REST HAS DATA
        String url = String.format(
                "/api/v1/collection/moving-average-200?tickers=%s&startDate=%s",
                ControllerTestConstants.WITH_ONE_TICKER_NO_DATA, startDate);
        LOG.info("getMovingAverageFor200DaysCollection() TEST CASE 1::URL: {}", url);

        when(movingAverageService.getMovingAverageinBatch(Arrays.asList(
                ControllerTestConstants.TICKERS_AS_ARRAY),
                startDate)).thenReturn(ControllerTestUtil.populateWithMACollectionDummyValue());

        RequestBuilder noDatarequestBuilder = MockMvcRequestBuilders.get(
                url)
                .accept(
                        MediaType.APPLICATION_JSON);

        MvcResult noDataForOneCaseResult = mockMvc.perform(noDatarequestBuilder)
                .andDo(document("getMovingAverageFor200DaysCollection-NoData Case")).andReturn();


        Assert.assertEquals(200,
                noDataForOneCaseResult.getResponse().getStatus());


    }

    @Test
    public void getMovingAverageFor200DaysCollectionCase3() throws Exception {

        LocalDate startDate = LocalDate.parse("2016-10-11",
                ControllerTestConstants.parseFormatter);

        //CASE 3: ONE TICKER INVALID, REST FINE
        String url = String.format(
                "/api/v1/collection/moving-average-200?tickers=%s&startDate=%s",
                ControllerTestConstants.INVALID_WITH_ONE_TICKER_NO_DATA, startDate);
        LOG.info("getMovingAverageFor200DaysCollection() TEST CASE 1::URL: {}", url);

        when(movingAverageService.getMovingAverageinBatch(Arrays.asList(
                ControllerTestConstants.TICKERS_AS_ARRAY),
                startDate)).thenReturn(ControllerTestUtil.populateWithMACollectionDummyValue());

        RequestBuilder noDatarequestBuilder = MockMvcRequestBuilders.get(
                url)
                .accept(
                        MediaType.APPLICATION_JSON);

        MvcResult noDataForOneCaseResult = mockMvc.perform(noDatarequestBuilder)
                .andDo(document("getMovingAverageFor200DaysCollection-Invalid-Ticker"))
                .andReturn();

        Assert.assertEquals(200, noDataForOneCaseResult.getResponse().getStatus());

    }
}
