package com.kafkastreams.kafkastreamspos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "InvoiceNumber",
        "CreatedTime",
        "StoreId",
        "PosID",
        "CashierId",
        "CustomerType",
        "CustomerCardNo",
        "TotalAmount",
        "NumberOfItems",
        "PaymentMethod",
        "TaxableAmount",
        "CGST",
        "SGST",
        "CESS",
        "DeliveryType",
        "DeliveryAddress",
        "InvoiceLineItems"
})
@Data
public class PosInvoice {
    @JsonProperty("InvoiceNumber")
    private String invoiceNumber;
    @JsonProperty("CreatedTime")
    private Long createdTime;
    @JsonProperty("StoreID")
    private String storeID;
    @JsonProperty("PosID")
    private String posID;
    @JsonProperty("CashierID")
    private String cashierID;
    @JsonProperty("CustomerType")
    private String customerType;
    @JsonProperty("CustomerCardNo")
    private String customerCardNo;
    @JsonProperty("TotalAmount")
    private Double totalAmount;
    @JsonProperty("NumberOfItems")
    private Integer numberOfItems;
    @JsonProperty("PaymentMethod")
    private String paymentMethod;
    @JsonProperty("TaxableAmount")
    private Double taxableAmount;
    @JsonProperty("CGST")
    private Double cGST;
    @JsonProperty("SGST")
    private Double sGST;
    @JsonProperty("CESS")
    private Double cESS;
    @JsonProperty("DeliveryType")
    private String deliveryType;
    @JsonProperty("DeliveryAddress")
    private DeliveryAddress deliveryAddress;
    @JsonProperty("InvoiceLineItems")
    private List<LineItem> invoiceLineItems = new ArrayList<LineItem>();

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("invoiceNumber", invoiceNumber).
                append("createdTime", createdTime).
                append("storeID", storeID).
                append("posID", posID).
                append("cashierID", cashierID).
                append("customerType", customerType).
                append("customerCardNo", customerCardNo).
                append("totalAmount", totalAmount).
                append("numberOfItems", numberOfItems).
                append("paymentMethod", paymentMethod).
                append("taxableAmount", taxableAmount).
                append("cGST", cGST).
                append("sGST", sGST).
                append("cESS", cESS).
                append("deliveryType", deliveryType).
                append("deliveryAddress", deliveryAddress).
                append("invoiceLineItems", invoiceLineItems).toString();
    }

}
