package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String excelPath, String sheetName) throws IOException {

            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
    }

    public static void getRowCount() {

        int rows = sheet.getPhysicalNumberOfRows();
        System.out.println("No of rows : "+rows);
    }

    public static void getCellData(int rowNum, int colNum) {

        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        System.out.println("Fomatted value : " + value);
    }

}
