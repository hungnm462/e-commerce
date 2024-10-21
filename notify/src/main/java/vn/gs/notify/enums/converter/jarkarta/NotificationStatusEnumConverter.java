package vn.gs.notify.enums.converter.jarkarta;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;
import vn.gs.notify.enums.NotificationStatusEnum;

/**
 * @author hungnm
 * @created 20/10/2024 - 22:36
 * @description ...
 */

@Converter(autoApply = true)
public class NotificationStatusEnumConverter
    implements AttributeConverter<NotificationStatusEnum, String> {

  @Override
  public String convertToDatabaseColumn(NotificationStatusEnum attribute) {
    if (Objects.isNull(attribute)) {
      return null;
    }
    return attribute.toString();
  }

  @Override
  public NotificationStatusEnum convertToEntityAttribute(String dbData) {
    if (Objects.isNull(dbData)) {
      return null;
    }
    return NotificationStatusEnum.of(dbData);
  }
}
