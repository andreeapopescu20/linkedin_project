package com.endava.pages;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by andpopescu on 1/9/2017.
 */
public class WriteData {
/*
    public void writeExcel(String fileName, String sheetName, String[] dataToWrite) throws IOException {

        //ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(fileName);

        FileInputStream inputStream = new FileInputStream(file);
        POIFSFileSystem myFileSystem = new POIFSFileSystem(inputStream);
        Workbook wb = new HSSFWorkbook(myFileSystem);

        HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount+1);
        for(int j = 0; j < row.getLastCellNum(); j++){

            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        inputStream.close();

        FileOutputStream outputStream = new FileOutputStream(file);
        wb.write(outputStream);
        outputStream.close();
    }


    public static void main(String arg[]) throws IOException{

        String[] valueToWrite = {"Mr.E","Noida"};

        WriteData objExcelFile = new WriteData();
        objExcelFile.writeExcel("export.xls","Candidates",valueToWrite);

    }
}
*/
/*
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Java Books");

        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };

        int rowCount = 0;

        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }

        }
        try (FileOutputStream outputStream = new FileOutputStream("export.xls")) {
            workbook.write(outputStream);
        }
    }

}
*/
}