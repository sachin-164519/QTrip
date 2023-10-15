package Qtrip_QA.utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public static String fileName = null;
    private static XSSFSheet Sheet;
    private static XSSFWorkbook WBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    public static Object[][] getTableArray(String filepath, String sheetName){
        Object[][] data = null;
        try{
            FileInputStream ExcelFile = new FileInputStream(filepath);
            WBook = new XSSFWorkbook(ExcelFile);
            Sheet = WBook.getSheet(sheetName);
            Row = Sheet.getRow(0);
            int totalRow = Sheet.getPhysicalNumberOfRows();
            int totalCol = Row.getLastCellNum();
            data = new Object[totalRow-1][totalCol-1];
            DataFormatter df = new DataFormatter();
            for(int i=1;i<totalRow;i++){
                for(int j=1;j<totalCol;j++){
                    // String value = df.formatCellValue(Sheet.getRow(i).getCell(j));
                    Row = Sheet.getRow(i);
                    Cell = Row.getCell(j);
                    data[i-1][j-1] = df.formatCellValue(Cell);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return data;
    }    
}
