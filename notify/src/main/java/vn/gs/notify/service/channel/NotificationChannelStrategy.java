package vn.gs.notify.service.channel;

import lombok.NonNull;
import vn.gs.notify.dto.event.NotificationMultiContentDto;
import vn.gs.notify.enums.NotificationChannelEnum;

/**
 * @author hungnm
 * @created 19/10/2024 - 18:33
 * @description ...
 */

public interface NotificationChannelStrategy {

  NotificationChannelEnum getChannel();

  void send(@NonNull NotificationMultiContentDto dto);
}
