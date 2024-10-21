package vn.gs.core.dto;

/**
 * @author hungnm
 * @created 19/10/2024 - 19:50
 * @description ...
 */

record ResultImpl<T>(
    T data,
    boolean succeed,
    boolean error,
    Throwable exception
) implements IResult<T> {

  public static <D> IResult<D> success(D data) {
    return new ResultImpl<>(data, true, false, null);
  }

  public static <D> IResult<D> error(Throwable throwable) {
    return new ResultImpl<>(null, false, true, throwable);
  }
}
