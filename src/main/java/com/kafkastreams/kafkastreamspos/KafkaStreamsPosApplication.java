package com.kafkastreams.kafkastreamspos;

import com.kafkastreams.kafkastreamspos.model.PosInvoice;
import com.kafkastreams.kafkastreamspos.serde.AppSerdes;
import com.kafkastreams.kafkastreamspos.service.RecordBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@Slf4j
@SpringBootApplication
public class KafkaStreamsPosApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(KafkaStreamsPosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsPosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Properties streamProperties = new Properties();
		streamProperties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG,AppConfigs.applicationID);
		streamProperties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
		streamProperties.setProperty(StreamsConfig.NUM_STREAM_THREADS_CONFIG,"3");

		StreamsBuilder streamsBuilder = new StreamsBuilder();
		KStream<String, PosInvoice> kStream0 = streamsBuilder.stream(AppConfigs.sourceTopicName,
				Consumed.with(Serdes.String(), AppSerdes.PosInvoice()));

		kStream0.filter(((key, value) -> value.getDeliveryType().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY)))
		.to(AppConfigs.shipmentTopicName, Produced.with(AppSerdes.String(),AppSerdes.PosInvoice()));

		kStream0.filter(((key, value) -> value.getCustomerType().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME)))
				.mapValues((invoice -> RecordBuilder.getNotification(invoice)))
				.to(AppConfigs.notificationTopic,Produced.with(AppSerdes.String(),AppSerdes.Notification()));


		kStream0.mapValues((invoice) -> RecordBuilder.getMaskedInvoices(invoice))
				.flatMapValues((maskedInvoice) -> RecordBuilder.getHadoopRecords(maskedInvoice))
				.to(AppConfigs.hadoopTopic,Produced.with(AppSerdes.String(),AppSerdes.HadoopRecord()));

		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(),streamProperties);
		kafkaStreams.start();
		logger.info("Starting the KafkaStreams");

		Runtime.getRuntime().addShutdownHook(new Thread(()  -> {
			kafkaStreams.close();
			logger.info("Closing the KafkaStreams");
		}));







	}
}
