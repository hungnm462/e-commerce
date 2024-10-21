package vn.gs.notify.config.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import vn.gs.notify.dto.event.NotificationMultiContentDto;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

  @Value(value = "${spring.kafka.bootstrap-servers:localhost:9093}")
  private String host;
  private final ObjectMapper objectMapper;

  @Bean
  public ProducerFactory<String, NotificationMultiContentDto> producerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put("retries", 0);
    props.put("acks", "all");
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
    return new DefaultKafkaProducerFactory<>(
        props,
        new StringSerializer(),
        (key, data) -> {
          try {
            return objectMapper.writeValueAsBytes(data);
          } catch (JsonProcessingException ex) {
            log.error(
                "Failed when convert MailDto to byte[]: data: {}",
                data
            );
            throw new RuntimeException(ex);
          }
        }
    );
  }

  @Bean
  public KafkaTemplate<String, NotificationMultiContentDto> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ConsumerFactory<String, NotificationMultiContentDto> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(" auto-offset-reset", "earliest");
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
    return new DefaultKafkaConsumerFactory<>(
        props,
        new StringDeserializer(),
        (key, data) -> {
          try {
            return objectMapper.readValue(data, NotificationMultiContentDto.class);
          } catch (IOException ex) {
            log.error(
                "Failed when convert byte[] to MailDto: data: {}",
                data
            );
            throw new RuntimeException(ex);
          }
        }
    );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, NotificationMultiContentDto> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, NotificationMultiContentDto>
        factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
