package com.kafkastreams.kafkastreamspos.service;

import com.kafkastreams.kafkastreamspos.AppConfigs;
import com.kafkastreams.kafkastreamspos.model.HadoopRecord;
import com.kafkastreams.kafkastreamspos.model.LineItem;
import com.kafkastreams.kafkastreamspos.model.Notification;
import com.kafkastreams.kafkastreamspos.model.PosInvoice;

import java.util.ArrayList;
import java.util.List;

public class RecordBuilder {
    public static Notification getNotification(PosInvoice posInvoice){
        return new Notification()
                .withInvoiceNumber(posInvoice.getInvoiceNumber())
                .withCustomerCardNo(posInvoice.getCustomerCardNo())
                .withTotalAmount(posInvoice.getTotalAmount())
                .withEarnedLoyaltyPoints(posInvoice.getTotalAmount() * AppConfigs.LOYALTY_FACTOR);
    }


    public static PosInvoice getMaskedInvoices(PosInvoice posInvoice) {
        posInvoice.setCustomerCardNo("XX");
        if(posInvoice.getDeliveryType().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY)){
            posInvoice.getDeliveryAddress().setAddressLine("XX");
            posInvoice.getDeliveryAddress().setContactNumber("XX");
        }
        return posInvoice;
    }

    public static List<HadoopRecord> getHadoopRecords(PosInvoice maskedInvoice) {
        List<HadoopRecord> records = new ArrayList<>();
        for(LineItem lineItem : maskedInvoice.getInvoiceLineItems()){
            HadoopRecord record = new HadoopRecord()
                    .withInvoiceNumber(maskedInvoice.getInvoiceNumber())
                    .withCreatedTime(maskedInvoice.getCreatedTime())
                    .withStoreID(maskedInvoice.getStoreID())
                    .withPosID(maskedInvoice.getPosID())
                    .withCustomerType(maskedInvoice.getCustomerType())
                    .withPaymentMethod( maskedInvoice.getPaymentMethod())
                    .withDeliveryType(maskedInvoice.getDeliveryType())
                    .withItemCode(lineItem.getItemCode())
                    .withItemDescription(lineItem.getItemDescription())
                    .withItemPrice(lineItem.getItemPrice())
                    .withItemQty(lineItem.getItemQty())
                    .withTotalValue(lineItem.getTotalValue());
            if (maskedInvoice.getDeliveryType().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY)) {
                record.setCity(maskedInvoice.getDeliveryAddress().getCity());
                record.setState(maskedInvoice.getDeliveryAddress().getState());
                record.setPinCode(maskedInvoice.getDeliveryAddress().getPinCode());
            }
            records.add(record);
        }
        return records;
    }
}
