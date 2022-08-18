package core;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtils {
    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;
    public Object[][] getTableArray(String filePath, String sheetName, int startColumn, int totalColumn) {
        String[][] tableArray = null;

        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            xssfWorkbook = new XSSFWorkbook(excelFile);
            xssfSheet = xssfWorkbook.getSheet(sheetName);
            int startRow = 1;
            int ci, cj;
            int totalRows = xssfSheet.getLastRowNum();
            ci=0;
            tableArray=new String[totalRows][totalColumn];
            for (int i=startRow;i<= totalRows;i++,ci++){
                cj=0;
                for (int j=startColumn;j<=totalColumn;j++,cj++){
                    XSSFCell cell=xssfSheet.getRow(i).getCell(j);
                    tableArray[ci][cj]=cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tableArray;
    }

    public  String getCellData(int rowNum, int colum) {
        XSSFCell cell = xssfSheet.getRow(rowNum).getCell(colum);
        System.out.println(cell.getStringCellValue());
        return cell.getStringCellValue();
    }
}
