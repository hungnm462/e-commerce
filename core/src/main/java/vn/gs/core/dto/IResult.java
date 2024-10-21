package vn.gs.core.dto;

import java.util.Objects;
import java.util.function.Supplier;
import lombok.val;

/**
 * @author hungnm
 * @created 19/10/2024 - 19:50
 * @description ...
 */

public interface IResult<T> {

  T data();

  boolean succeed();

  boolean error();

  Throwable exception();

  static <K> IResult<K> of(final Supplier<K> supplier) {
    if (Objects.isNull(supplier)) {
      return ResultImpl.error(new NullPointerException("Supplier is null"));
    }

    try {
      val data = supplier.get();
      return ResultImpl.success(data);
    } catch (Exception ex) {
      return ResultImpl.error(ex);
    }
  }
}
