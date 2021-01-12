package com.kafkastreams.kafkastreamspos;

public class AppConfigs {
    public final static String applicationID = "PosStreams";
    public final static String bootstrapServers = "localhost:9092";
    public final static String sourceTopicName = "pos.simulation";
    public final static String shipmentTopicName = "pos.shipment";
    public final static String notificationTopic = "pos.loyalty";
    public final static String hadoopTopic = "pos.hadoopsink";
    public final static String DELIVERY_TYPE_HOME_DELIVERY = "HOME-DELIVERY";
    public final static String CUSTOMER_TYPE_PRIME = "PRIME";
    public final static Double LOYALTY_FACTOR = 0.02;
}
