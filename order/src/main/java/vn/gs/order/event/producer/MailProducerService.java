package vn.gs.order.event.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.gs.order.dto.event.MailDto;

@Slf4j
@Service
public class MailProducerService extends ProducerService<MailDto> {

  @Value(value = "${spring.kafka.topic.mail:kafka-mail}")
  private String topic;

  public MailProducerService(KafkaTemplate<String, MailDto> kafkaTemplate) {
    super(kafkaTemplate);
  }

  @Override
  public String getTopic() {
    return this.topic;
  }

  @Override
  public void handleError(MailDto data, Throwable ex) {
    // To Do: Xử lý khi gặp lỗi
  }
}
