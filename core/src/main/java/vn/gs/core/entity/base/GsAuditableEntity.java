package vn.gs.core.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.gs.core.constant.GsConst;

/**
 * @author hungnm
 * @created 19/10/2024 - 19:50
 * @description ...
 */

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class GsAuditableEntity implements Serializable {

  @CreatedDate
  @Column(name = "create_date", nullable = false)
  @JsonFormat(pattern = GsConst.Date.US_NORMAL_DATE_TIME, shape = JsonFormat.Shape.STRING)
  LocalDateTime createDate;

  @CreatedBy
  @Column(name = "create_by", nullable = false)
  String createBy;

  @LastModifiedDate
  @Column(name = "modify_date")
  @JsonFormat(pattern = GsConst.Date.US_NORMAL_DATE_TIME, shape = JsonFormat.Shape.STRING)
  LocalDateTime modifyDate;

  @LastModifiedBy
  @Column(name = "modify_by")
  String modifyBy;
}
