package vn.gs.notify.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @author hungnm
 * @created 19/10/2024 - 18:36
 * @description ...
 */

@AllArgsConstructor
public enum NotificationChannelEnum {
  EMAIL("EMAIL"),
  SMS("SMS"),
  ZALO("ZALO"),
  TELEGRAM("TELEGRAM"),
  FIREBASE("FIREBASE"),
  WEBSITE("WEBSITE")
  ;
  private final String value;

  @JsonCreator(mode = Mode.DELEGATING)
  public static NotificationChannelEnum of(final String value) {
    for (final NotificationChannelEnum e : NotificationChannelEnum.values()) {
      if (e.value.equals(value)) {
        return e;
      }
    }

    throw new IllegalArgumentException(value);
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
