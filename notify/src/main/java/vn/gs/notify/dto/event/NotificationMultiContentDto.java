package vn.gs.notify.dto.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.util.CollectionUtils;
import vn.gs.notify.enums.NotificationChannelEnum;

/**
 * @author hungnm
 * @created 19/10/2024 - 18:46
 * @description ...
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NotificationMultiContentDto {

  List<String> tos;

  String subject;

  List<NotificationContentDto> contents;

  Map<String, Object> params = new HashMap<>();

  //////////////////////////////
  // FIELD NON GETTER, SETTER //
  //////////////////////////////

  Map<NotificationChannelEnum, String> contentMap;

  //////////////////////////////
  //           SETTER         //
  //////////////////////////////

  public void setContents(List<NotificationContentDto> contents) {
    if (!CollectionUtils.isEmpty(contents)) {
      this.contentMap = contents.stream()
          .collect(Collectors.toMap(
              NotificationContentDto::getChannel,
              NotificationContentDto::getContent
          ));
    }
    this.contents = contents;
  }

  //////////////////////////////
  //      PUBLIC METHOD       //
  //////////////////////////////

  public List<NotificationChannelEnum> getChannels() {
    if (CollectionUtils.isEmpty(contents)) {
      return Collections.emptyList();
    }

    return contents.stream().map(NotificationContentDto::getChannel).toList();
  }

  public String getContent(@NonNull NotificationChannelEnum channel) {
    return contentMap.get(channel);
  }
}
