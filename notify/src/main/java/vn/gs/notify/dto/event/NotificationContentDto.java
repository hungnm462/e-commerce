package vn.gs.notify.dto.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import vn.gs.notify.enums.NotificationChannelEnum;

/**
 * @author hungnm
 * @created 19/10/2024 - 19:12
 * @description ...
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NotificationContentDto {

  NotificationChannelEnum channel;

  String content;
}
