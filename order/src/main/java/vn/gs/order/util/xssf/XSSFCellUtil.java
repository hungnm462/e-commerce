package vn.gs.order.util.xssf;

import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import lombok.NonNull;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;

public class XSSFCellUtil {

  private Row r;
  private Cell c;
  private CellStyle dcs;

  public XSSFCellUtil(@NonNull Row row, CellStyle style) {
    this.r = row;
    this.dcs = style;
  }

  /////////////////////////////////
  ////      SET FUNCTION       ////
  /////////////////////////////////

  public XSSFCellUtil index(@NonNull @Min(0) Integer index) {
    this.c = this.init(index);
    return this;
  }

  public XSSFCellUtil value(double value) {
    this.preValidate();
    this.defaultStyle();

    this.c.setCellValue(value);
    return this;
  }

  public XSSFCellUtil value(Date value) {
    this.preValidate();
    this.defaultStyle();

    this.c.setCellValue(value);
    return this;
  }

  public XSSFCellUtil value(LocalDateTime value) {
    this.preValidate();
    this.defaultStyle();

    this.c.setCellValue(value);
    return this;
  }

  public XSSFCellUtil value(LocalDate value) {
    this.preValidate();
    this.defaultStyle();

    this.c.setCellValue(value);
    return this;
  }

  public XSSFCellUtil value(Calendar value) {
    this.c.setCellValue(value);

    return this;
  }

  public XSSFCellUtil value(RichTextString value) {
    this.preValidate();
    this.defaultStyle();

    this.c.setCellValue(value);
    return this;
  }

  public XSSFCellUtil value(String value) {
    this.preValidate();
    this.defaultStyle();

    this.c.setCellValue(value);
    return this;
  }

  public XSSFCellUtil style(@NonNull CellStyle style) {
    this.preValidate();

    this.c.setCellStyle(style);
    return this;
  }

  /////////////////////////////////
  ////      GET FUNCTION       ////
  /////////////////////////////////

  public Cell out() {
    this.preValidate();
    return this.c;
  }

  public CellStyle style() {
    return this.out().getCellStyle();
  }

  /////////////////////////////////
  ////    PRIVATE FUNCTION     ////
  /////////////////////////////////

  private Cell init(int index) {
    Cell cell = this.r.getCell(index);

    if (Objects.isNull(cell)) {
      cell = this.r.createCell(index);
    }

    return cell;
  }

  private void preValidate() {
    if (Objects.isNull(this.c)) {
      throw new RuntimeException("cell is null");
    }
  }

  private void defaultStyle() {
    if (Objects.nonNull(this.dcs)) {
      this.c.setCellStyle(this.dcs);
    }
  }
}
