package vn.gs.order.util.xssf;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.NonNull;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.validation.annotation.Validated;

@Validated
public class XSSFSheetUtil {

  private final Workbook w;
  private Sheet s;
  private XSSFRowUtil xssfr;
  private CellStyle dcs;

  public XSSFSheetUtil(@NonNull Workbook workbook, CellStyle style) {
    this.w = workbook;
    this.dcs = style;
  }

  /////////////////////////////////
  ////      SET FUNCTION       ////
  /////////////////////////////////

  public XSSFSheetUtil index(@NonNull @Min(0) Integer index) {
    this.s = this.init(index);
    return this;
  }

  public XSSFSheetUtil name(@Valid @NotBlank String name) {
    this.s = this.init(name);
    return this;
  }

  public XSSFSheetUtil defaultStyle(@NonNull CellStyle style) {
    this.dcs = style;
    return this;
  }

  public XSSFSheetUtil merge(@Valid @NotBlank String region) {
    CellRangeAddress address = parseCellRange(region);

    // Khởi tạo vị trí merge (góc trên bên trái)
    this.row().index(address.getFirstRow()).cell().index(address.getFirstColumn());

    this.s.addMergedRegion(address);
    return this;
  }

  /////////////////////////////////
  ////      GET FUNCTION       ////
  /////////////////////////////////

  public Sheet out() {
    this.preValidate();
    return this.s;
  }

  public CellStyle defaultStyle() {
    return this.dcs;
  }

  public XSSFRowUtil row() {
    this.preValidate();

    if (Objects.isNull(this.xssfr)) {
      this.xssfr = new XSSFRowUtil(this.s, dcs);
    }
    return this.xssfr;
  }

  /////////////////////////////////
  ////     PRIVATE FUNCTION    ////
  /////////////////////////////////

  private Sheet init(int index) {
    Sheet sheet = this.w.getSheetAt(index);

    if (Objects.isNull(sheet)) {
      sheet = this.w.createSheet();
    }

    return sheet;
  }

  private Sheet init(String name) {
    Sheet sheet = this.w.getSheet(name);

    if (Objects.isNull(sheet)) {
      sheet = this.w.createSheet(name);
    }

    return sheet;
  }

  private void preValidate() {
    if (Objects.isNull(this.s)) {
      throw new RuntimeException("sheet is null");
    }
  }

  /////////////////////////////////
  ////     STATIC FUNCTION     ////
  /////////////////////////////////

  public static CellRangeAddress parseCellRange(String cellRange) {
    Pattern pattern = Pattern.compile(XSSFConstant.REGION_MERGE_REGEX);
    Matcher matcher = pattern.matcher(cellRange);

    if (matcher.matches()) {
      String startColumn = matcher.group(1);
      int startRowIndex = Integer.parseInt(matcher.group(2)) - 1;
      String endColumn = matcher.group(3);
      int endRowIndex = Integer.parseInt(matcher.group(4)) - 1;

      int startColumnIndex = columnNameToIndex(startColumn);
      int endColumnIndex = columnNameToIndex(endColumn);

      return new CellRangeAddress(startRowIndex, endRowIndex, startColumnIndex, endColumnIndex);
    } else {
      throw new IllegalArgumentException("Chuỗi không đúng định dạng: " + cellRange);
    }
  }

  private static int columnNameToIndex(String columnName) {
    int columnIndex = 0;
    for (int i = 0; i < columnName.length(); i++) {
      columnIndex = columnIndex * 26 + (columnName.charAt(i) - 'A' + 1);
    }
    return columnIndex - 1;
  }
}
