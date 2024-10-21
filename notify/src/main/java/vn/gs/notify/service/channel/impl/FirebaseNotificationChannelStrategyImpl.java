package vn.gs.notify.service.channel.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.gs.notify.dto.event.NotificationMultiContentDto;
import vn.gs.notify.enums.NotificationChannelEnum;
import vn.gs.notify.service.channel.NotificationChannelStrategy;

/**
 * @author hungnm
 * @created 19/10/2024 - 18:55
 * @description ...
 */

@Service
@RequiredArgsConstructor
public class FirebaseNotificationChannelStrategyImpl implements NotificationChannelStrategy {

  @Override
  public NotificationChannelEnum getChannel() {
    return NotificationChannelEnum.FIREBASE;
  }

  @Override
  public void send(@NonNull NotificationMultiContentDto dto) {
    // TODO nếu lưu thành công sẽ lưu vào [db, log] với channel EMAIL, status ERROR
    // TODO nếu có lỗi thì sẽ lưu thông tin vào [db, log] với channel EMAIL, status ERROR
  }
}
