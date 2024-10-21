package vn.gs.notify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.gs.notify.entity.NotificationEntity;

/**
 * @author hungnm
 * @created 20/10/2024 - 22:29
 * @description ...
 */

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, String> {

}
