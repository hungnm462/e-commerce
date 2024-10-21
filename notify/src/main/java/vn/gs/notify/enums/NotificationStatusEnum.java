package vn.gs.notify.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * @author hungnm
 * @created 20/10/2024 - 22:33
 * @description ...
 */

@AllArgsConstructor
public enum NotificationStatusEnum {
  SENT("sent"),
  ERROR("error"),
  ;
  private final String value;

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static NotificationStatusEnum of(final String value) {
    for (final NotificationStatusEnum e : NotificationStatusEnum.values()) {
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
