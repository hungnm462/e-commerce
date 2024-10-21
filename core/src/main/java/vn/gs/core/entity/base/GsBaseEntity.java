package vn.gs.core.entity.base;

import java.io.Serializable;

/**
 * @author hungnm
 * @created 19/10/2024 - 19:50
 * @description ...
 */

public interface GsBaseEntity<T> extends Serializable {

  T getId();
}
