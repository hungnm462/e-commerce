package vn.gs.notify.entity;

import jakarta.persistence.Convert;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.gs.core.entity.base.GsBaseStringEntity;
import vn.gs.notify.enums.NotificationChannelEnum;
import vn.gs.notify.enums.NotificationStatusEnum;
import vn.gs.notify.enums.converter.jarkarta.NotificationChannelEnumConverter;
import vn.gs.notify.enums.converter.jarkarta.NotificationStatusEnumConverter;
import vn.gs.notify.enums.converter.jarkarta.StringListConverter;

/**
 * @author hungnm
 * @created 19/10/2024 - 19:50
 * @description ...
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("notification")
public class NotificationEntity extends GsBaseStringEntity {

  String userSend;//????

  String userReceive;//???

  String subject;

  String content;

  Map<String, Object> params;

  //////////////////////////////
  //         CONVERTER        //
  //////////////////////////////

  @Convert(converter = StringListConverter.class)
  List<String> tos;

  @Convert(converter = NotificationStatusEnumConverter.class)
  NotificationStatusEnum status;

  @Convert(converter = NotificationChannelEnumConverter.class)
  NotificationChannelEnum channel;
}
