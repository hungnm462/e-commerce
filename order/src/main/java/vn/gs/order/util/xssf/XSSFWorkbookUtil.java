package vn.gs.order.util.xssf;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class XSSFWorkbookUtil {

  private final XSSFWorkbook w;
  private XSSFSheetUtil xssfs;
  private CellStyle dcs;

  public XSSFWorkbookUtil(@NonNull XSSFWorkbook workbook) {
    this.w = workbook;
  }

  public XSSFWorkbookUtil(String path) {
    try (InputStream in = new FileInputStream(path)) {
      this.w = new XSSFWorkbook(in);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public XSSFWorkbookUtil(@NonNull InputStream in) {
    try {
      this.w = new XSSFWorkbook(in);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /////////////////////////////////
  ////      SET FUNCTION       ////
  /////////////////////////////////

  public XSSFWorkbookUtil defaultStyle(@NonNull CellStyle style) {
    this.dcs = style;
    return this;
  }

  /////////////////////////////////
  ////      GET FUNCTION       ////
  /////////////////////////////////

  public XSSFWorkbook out() {
    return this.w;
  }

  public CellStyle defaultStyle() {
    return this.dcs;
  }

  public XSSFSheetUtil sheet() {
    if (Objects.isNull(this.xssfs)) {
      this.xssfs = new XSSFSheetUtil(this.w, dcs);
    }
    return this.xssfs;
  }

  public byte[] toBytes() {
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      this.w.write(out);
      return out.toByteArray();
    } catch (IOException e) {
      log.error("XSSFWorkbookUtil: {}", e.getMessage(), e);
      return new byte[0];
    }
  }
}
