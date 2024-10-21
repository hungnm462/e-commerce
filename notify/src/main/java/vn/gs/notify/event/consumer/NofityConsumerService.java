package vn.gs.notify.event.consumer;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import vn.gs.notify.dto.event.NotificationMultiContentDto;
import vn.gs.notify.service.NotificationChannelService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NofityConsumerService extends ConsumerService<NotificationMultiContentDto> {

  private final NotificationChannelService notificationChannelService;

  @KafkaListener(
      topics = "${spring.kafka.topic.mail:kafka-mail}",
      groupId = "${spring.kafka.group.mail:kafka-mail-group-id}",
      concurrency = "3",
      autoStartup = "none"
  )
  @Override
  public void listen(@Payload List<NotificationMultiContentDto> data, @Headers Map<String, Object> headers) {
    log.info("data: {}, headers: {}", data, headers);
    for (NotificationMultiContentDto notificationMultiContentDto : data) {
      notificationChannelService.send(notificationMultiContentDto);
    }
  }
}
