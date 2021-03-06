package com.manig.stockprice.io.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;



/**
 * Dataset generated from quandl API holding closePrice
 *
 * @author Manishankar Goswami
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dataset {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("dataset_code")
    private String datasetCode;
    @JsonProperty("database_code")
    private String databaseCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("refreshed_at")
    private String refreshedAt;
    @JsonProperty("newest_available_date")
    private String newestAvailableDate;
    @JsonProperty("oldest_available_date")
    private String oldestAvailableDate;
    @JsonProperty("column_names")
    private List<String> columnNames = null;
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("type")
    private String type;
    @JsonProperty("premium")
    private Boolean premium;
    @JsonProperty("limit")
    private Object limit;
    @JsonProperty("transform")
    private Object transform;
    @JsonProperty("column_index")
    private Integer columnIndex;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("data")
    private List<List<String>> data = null;
    @JsonProperty("collapse")
    private Object collapse;
    @JsonProperty("order")
    private Object order;
    @JsonProperty("database_id")
    private Integer databaseId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Dataset() {

    }

    public Dataset(Integer id, String datasetCode, String databaseCode, String name,
            String description, String refreshedAt, String newestAvailableDate,
            String oldestAvailableDate, List<String> columnNames, String frequency, String type,
            Boolean premium, Object limit, Object transform, Integer columnIndex, String startDate,
            String endDate, List<List<String>> data, Object collapse, Object order,
            Integer databaseId, Map<String, Object> additionalProperties) {

        this.id = id;
        this.datasetCode = datasetCode;
        this.databaseCode = databaseCode;
        this.name = name;
        this.description = description;
        this.refreshedAt = refreshedAt;
        this.newestAvailableDate = newestAvailableDate;
        this.oldestAvailableDate = oldestAvailableDate;
        this.columnNames = columnNames;
        this.frequency = frequency;
        this.type = type;
        this.premium = premium;
        this.limit = limit;
        this.transform = transform;
        this.columnIndex = columnIndex;
        this.startDate = startDate;
        this.endDate = endDate;
        this.data = data;
        this.collapse = collapse;
        this.order = order;
        this.databaseId = databaseId;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("dataset_code")
    public String getDatasetCode() {
        return datasetCode;
    }

    @JsonProperty("dataset_code")
    public void setDatasetCode(String datasetCode) {
        this.datasetCode = datasetCode;
    }

    @JsonProperty("database_code")
    public String getDatabaseCode() {
        return databaseCode;
    }

    @JsonProperty("database_code")
    public void setDatabaseCode(String databaseCode) {
        this.databaseCode = databaseCode;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("refreshed_at")
    public String getRefreshedAt() {
        return refreshedAt;
    }

    @JsonProperty("refreshed_at")
    public void setRefreshedAt(String refreshedAt) {
        this.refreshedAt = refreshedAt;
    }

    @JsonProperty("newest_available_date")
    public String getNewestAvailableDate() {
        return newestAvailableDate;
    }

    @JsonProperty("newest_available_date")
    public void setNewestAvailableDate(String newestAvailableDate) {
        this.newestAvailableDate = newestAvailableDate;
    }

    @JsonProperty("oldest_available_date")
    public String getOldestAvailableDate() {
        return oldestAvailableDate;
    }

    @JsonProperty("oldest_available_date")
    public void setOldestAvailableDate(String oldestAvailableDate) {
        this.oldestAvailableDate = oldestAvailableDate;
    }

    @JsonProperty("column_names")
    public List<String> getColumnNames() {
        return columnNames;
    }

    @JsonProperty("column_names")
    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    @JsonProperty("frequency")
    public String getFrequency() {
        return frequency;
    }

    @JsonProperty("frequency")
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("premium")
    public Boolean getPremium() {
        return premium;
    }

    @JsonProperty("premium")
    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    @JsonProperty("limit")
    public Object getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Object limit) {
        this.limit = limit;
    }

    @JsonProperty("transform")
    public Object getTransform() {
        return transform;
    }

    @JsonProperty("transform")
    public void setTransform(Object transform) {
        this.transform = transform;
    }

    @JsonProperty("column_index")
    public Integer getColumnIndex() {
        return columnIndex;
    }

    @JsonProperty("column_index")
    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("end_date")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("data")
    public List<List<String>> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @JsonProperty("collapse")
    public Object getCollapse() {
        return collapse;
    }

    @JsonProperty("collapse")
    public void setCollapse(Object collapse) {
        this.collapse = collapse;
    }

    @JsonProperty("order")
    public Object getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Object order) {
        this.order = order;
    }

    @JsonProperty("database_id")
    public Integer getDatabaseId() {
        return databaseId;
    }

    @JsonProperty("database_id")
    public void setDatabaseId(Integer databaseId) {
        this.databaseId = databaseId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                .append("datasetCode", datasetCode)
                .append("databaseCode", databaseCode)
                .append("name", name)
                .append("description", description)
                .append("refreshedAt", refreshedAt)
                .append("newestAvailableDate", newestAvailableDate)
                .append("oldestAvailableDate", oldestAvailableDate)
                .append("columnNames", columnNames)
                .append("frequency", frequency).append("type", type)
                .append("premium", premium).append("limit", limit)
                .append("transform", transform)
                .append("columnIndex", columnIndex)
                .append("startDate", startDate).append("endDate", endDate)
                .append("data", data).append("collapse", collapse)
                .append("order", order).append("databaseId", databaseId)
                .append("additionalProperties", additionalProperties).toString();
    }

}

