package vn.gs.notify.service;

import lombok.NonNull;
import vn.gs.notify.dto.event.NotificationMultiContentDto;
import vn.gs.notify.entity.NotificationEntity;

/**
 * @author hungnm
 * @created 20/10/2024 - 23:27
 * @description ...
 */

public interface NotificationService {

  NotificationEntity createAndReturnEntity(@NonNull NotificationMultiContentDto dto);
}
