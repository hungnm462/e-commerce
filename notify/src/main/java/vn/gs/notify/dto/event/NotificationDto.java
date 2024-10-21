package vn.gs.notify.dto.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import vn.gs.notify.enums.NotificationChannelEnum;

/**
 * @author hungnm
 * @created 20/10/2024 - 23:31
 * @description ...
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NotificationDto {

  List<String> tos;

  String subject;

  NotificationChannelEnum channel;

  String content;

  Map<String, Object> params = new HashMap<>();
}
