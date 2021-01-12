package com.kafkastreams.kafkastreamspos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "AddressLine",
        "City",
        "State",
        "PinCode",
        "ContactNumber"
})
@Data
public class DeliveryAddress {
    @JsonProperty("AddressLine")
    private String addressLine;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("PinCode")
    private String pinCode;
    @JsonProperty("ContactNumber")
    private String contactNumber;

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("AddressLine", addressLine).
                append("City", city).
                append("State", state).
                append("PinCode", pinCode).
                append("ContactNumber", contactNumber).toString();
    }
}
