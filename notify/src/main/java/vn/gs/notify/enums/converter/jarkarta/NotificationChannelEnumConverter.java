package vn.gs.notify.enums.converter.jarkarta;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;
import vn.gs.notify.enums.NotificationChannelEnum;

/**
 * @author hungnm
 * @created 20/10/2024 - 22:22
 * @description ...
 */

@Converter(autoApply = true)
public class NotificationChannelEnumConverter
    implements AttributeConverter<NotificationChannelEnum, String> {

  @Override
  public String convertToDatabaseColumn(NotificationChannelEnum attribute) {
    if (Objects.isNull(attribute)) {
      return null;
    }
    return attribute.getValue();
  }

  @Override
  public NotificationChannelEnum convertToEntityAttribute(String dbData) {
    if (Objects.isNull(dbData)) {
      return null;
    }
    return NotificationChannelEnum.valueOf(dbData);
  }
}
