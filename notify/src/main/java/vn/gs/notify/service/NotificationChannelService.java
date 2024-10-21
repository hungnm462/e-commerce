package vn.gs.notify.service;

import vn.gs.notify.dto.event.NotificationMultiContentDto;

/**
 * @author hungnm
 * @created 19/10/2024 - 18:56
 * @description ...
 */

public interface NotificationChannelService {

  void send(NotificationMultiContentDto dto);
}
