package vn.gs.notify.event.consumer;

import java.util.List;
import java.util.Map;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

public abstract class ConsumerService<T> {

  public abstract void listen(@Payload List<T> data, @Headers Map<String, Object> headers);
}

