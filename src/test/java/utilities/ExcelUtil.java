package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

    public String xlFilePath;
    public ExcelUtil(String filePath){
        xlFilePath = filePath;
    }

    public  FileInputStream fileRead;
    public  FileOutputStream fileOut;
    public  XSSFWorkbook workbook;
    public  XSSFSheet sheet ;
    public  XSSFRow rows;
    public  XSSFCell cell;
    public  XSSFCellStyle cellStyle;

    public  Integer getTotalRows(String xlSheetName) throws IOException {
        fileRead = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fileRead);
        sheet = workbook.getSheet(xlSheetName);
        Integer rows = sheet.getLastRowNum();

        workbook.close();
        fileRead.close();

        return rows;
    }

    public  Integer getTotalColumns(String xlSheetName, int rowNum) throws IOException {
        fileRead = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fileRead);
        sheet = workbook.getSheet(xlSheetName);
        Integer cols = (int) sheet.getRow(rowNum).getLastCellNum();

        workbook.close();
        fileRead.close();

        return cols;
    }

    public  String getCellData(String xlSheetName,int rowNum, int colNum) throws IOException {

        fileRead=new FileInputStream(xlFilePath);
        workbook=new XSSFWorkbook(fileRead);
        sheet=workbook.getSheet(xlSheetName);
        rows=sheet.getRow(rowNum);
        cell=rows.getCell(colNum);

        String data;
        try{
                //data=cell.toString();
                DataFormatter formatter = new DataFormatter();
                data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String rega
            }
        catch (Exception e){
            data="";
        }

        workbook.close();
        fileRead.close();
        return data;
    }

    public  void setCellData(String xlSheetName, int rownum, int colnum, String data) throws IOException{

        fileRead=new FileInputStream(xlFilePath);
        workbook=new XSSFWorkbook(fileRead);
        sheet=workbook.getSheet(xlSheetName);
        rows=sheet.getRow(rownum);
        cell=rows.createCell(colnum);
        cell.setCellValue(data);
        fileOut=new FileOutputStream(xlFilePath);

        workbook.write(fileOut);

        workbook.close();
        fileRead.close();
        fileOut.close();

    }

    public  void fillGreenColor(String xlSheetName, int rownum, int colnum) throws IOException {

        fileRead = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fileRead);
        sheet = workbook.getSheet(xlSheetName);
        rows = sheet.getRow(rownum);
        cell = rows.getCell(colnum);

        cellStyle = workbook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(cellStyle);
        fileOut = new FileOutputStream(xlFilePath);
        workbook.write(fileOut);
        workbook.close();
        fileRead.close();
        fileOut.close();
    }
    public  void fillRedColor(String xlSheetName,int rownum, int colnum) throws IOException{

        fileRead=new FileInputStream(xlFilePath);
        workbook=new XSSFWorkbook(fileRead);
        sheet=workbook.getSheet(xlSheetName);
        rows=sheet.getRow(rownum);
        cell=rows.getCell(colnum);

        cellStyle=workbook.createCellStyle();
    }
}
