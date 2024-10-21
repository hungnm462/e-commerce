package vn.gs.order.util.xssf;

import jakarta.validation.constraints.Min;
import java.util.Objects;
import lombok.NonNull;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class XSSFRowUtil {

  private final Sheet s;
  private Row r;
  private XSSFCellUtil xssfc;
  private CellStyle dcs;

  public XSSFRowUtil(@NonNull Sheet sheet, CellStyle style) {
    this.s = sheet;
    this.dcs = style;
  }

  /////////////////////////////////
  ////      SET FUNCTION       ////
  /////////////////////////////////

  public XSSFRowUtil index(@NonNull @Min(0) Integer index) {
    this.r = this.init(index);
    return this;
  }

  public XSSFRowUtil defaultStyle(@NonNull CellStyle style) {
    this.dcs = style;
    return this;
  }

  /////////////////////////////////
  ////      GET FUNCTION       ////
  /////////////////////////////////

  public Row out() {
    this.preValidate();
    return this.r;
  }

  public CellStyle defaultStyle() {
    return this.dcs;
  }

  public XSSFCellUtil cell() {
    this.preValidate();

    if (Objects.isNull(this.xssfc)) {
      this.xssfc = new XSSFCellUtil(this.r, dcs);
    }
    return this.xssfc;
  }

  /////////////////////////////////
  ////    PRIVATE FUNCTION     ////
  /////////////////////////////////

  private Row init(int index) {
    Row row = this.s.getRow(index);

    if (Objects.isNull(row)) {
      row = this.s.createRow(index);
    }

    return row;
  }

  private void preValidate() {
    if (Objects.isNull(this.r)) {
      throw new RuntimeException("row is null");
    }
  }
}
