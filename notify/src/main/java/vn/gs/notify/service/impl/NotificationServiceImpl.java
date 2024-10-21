package vn.gs.notify.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.gs.notify.dto.event.NotificationMultiContentDto;
import vn.gs.notify.entity.NotificationEntity;
import vn.gs.notify.repository.NotificationRepository;
import vn.gs.notify.service.NotificationService;

/**
 * @author hungnm
 * @created 20/10/2024 - 23:28
 * @description ...
 */

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;

  @Override
  public NotificationEntity createAndReturnEntity(@NonNull NotificationMultiContentDto dto) {

    return notificationRepository.save(dto);
  }
}
