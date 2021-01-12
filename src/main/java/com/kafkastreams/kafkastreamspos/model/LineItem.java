package com.kafkastreams.kafkastreamspos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ItemCode",
        "ItemDescription",
        "ItemPrice",
        "ItemQty",
        "TotalValue"
})
@Data
public class LineItem {

    @JsonProperty("ItemCode")
    private String itemCode;
    @JsonProperty("ItemDescription")
    private String itemDescription;
    @JsonProperty("ItemPrice")
    private Double itemPrice;
    @JsonProperty("ItemQty")
    private Integer itemQty;
    @JsonProperty("TotalValue")
    private Double totalValue;

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("itemCode", itemCode).
                append("itemDescription", itemDescription).
                append("itemPrice", itemPrice).
                append("itemQty", itemQty).
                append("totalValue", totalValue).
                toString();
    }
}
