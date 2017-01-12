package com.endava.pages;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andpopescu on 1/5/2017.
 */
public class ReadData {
    private Admins admin = new Admins();

    public Object readExcel(String fileName, String sheetName) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        FileInputStream inputStream = new FileInputStream(file);
        POIFSFileSystem myFileSystem = new POIFSFileSystem(inputStream);
        Workbook wb = new HSSFWorkbook(myFileSystem);

        HSSFSheet sheet = (HSSFSheet) wb.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        List<String> employees = new ArrayList<>();
        List<String> keywords = new ArrayList<>();

        for (int i = 1; i < rowCount + 1; i++) {
            Row row = sheet.getRow(i);

            if (sheetName.equals("Admin")) {
                for (int j = 0; j < (row.getLastCellNum() - 1); j++) {
                    admin.setEmail(row.getCell(j).getStringCellValue());
                    admin.setPassword(row.getCell(j + 1).getStringCellValue());
                }
            } else if (sheetName.equals("Sheet1")) {
                for (int j = 0; j < (row.getLastCellNum() - 1); j++) {
                    employees.add(row.getCell(j).getStringCellValue() + " " + row.getCell(j + 1).getStringCellValue());
                }
            } else if (sheetName.equals("Sheet2")) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    keywords.add(row.getCell(j).getStringCellValue());
                }
            }
        }
        if (sheetName.equals("Sheet1")) {
            return employees;
        } else if (sheetName.equals("Sheet2")) {
            return keywords;
        } else if (sheetName.equals("Admin")) {
            return admin;
        }
        return null;
    }
}









