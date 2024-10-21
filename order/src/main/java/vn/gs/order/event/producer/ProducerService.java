package vn.gs.order.event.producer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@RequiredArgsConstructor
public abstract class ProducerService<T> {

  private final KafkaTemplate<String, T> kafkaTemplate;

  public CompletableFuture<SendResult<String, T>> send(final T mailDto) {
    return send(mailDto, null);
  }

  public CompletableFuture<SendResult<String, T>> send(
      final T mailDto,
      Map<String, Object> headers
  ) {
    MessageBuilder<T> builder = MessageBuilder.withPayload(mailDto);
    builder.setHeader(KafkaHeaders.TOPIC, this.getTopic());
    if (Objects.nonNull(headers)) {
      headers.forEach(builder::setHeader);
    }
    CompletableFuture<SendResult<String, T>> future = kafkaTemplate.send(builder.build());
    future.whenComplete((sr, ex) -> {
      if (Objects.nonNull(ex)) {
        log.error(
            "Send fail topic: {}, message: {}, message: {}",
            this.getTopic(),
            builder,
            ex.getMessage(),
            ex
        );
        future.completeExceptionally(ex);
        handleError(mailDto, ex);
      } else {
        log.info(
            "Send success topic: {}, message: {}, sendResult: {}",
            this.getTopic(),
            builder,
            sr
        );
        future.complete(sr);
      }
    });
    return future;
  }

  public abstract String getTopic();

  public abstract void handleError(T data, Throwable ex);
}
