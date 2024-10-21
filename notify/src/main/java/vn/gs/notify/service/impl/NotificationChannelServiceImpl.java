package vn.gs.notify.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.gs.notify.dto.event.NotificationMultiContentDto;
import vn.gs.notify.enums.NotificationChannelEnum;
import vn.gs.notify.service.NotificationChannelService;
import vn.gs.notify.service.channel.NotificationChannelStrategy;

/**
 * @author hungnm
 * @created 19/10/2024 - 18:56
 * @description ...
 */

@Slf4j
@Service
public class NotificationChannelServiceImpl implements NotificationChannelService {

  private final Map<NotificationChannelEnum, NotificationChannelStrategy> notificationChannels;

  public NotificationChannelServiceImpl(List<NotificationChannelStrategy> notificationChannels) {
    this.notificationChannels = notificationChannels.stream()
        .collect(Collectors.toMap(NotificationChannelStrategy::getChannel, Function.identity()));
  }

  @Override
  public void send(NotificationMultiContentDto dto) {
    for (NotificationChannelEnum channel : dto.getChannels()) {
      NotificationChannelStrategy strategy = notificationChannels.get(channel);
      if (Objects.nonNull(strategy)) {
        strategy.send(dto);
      } else {
        log.error("Notification channel {} not supported", channel);
      }
    }
  }
}
