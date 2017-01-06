package com.endava.pages;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.io.Resources.getResource;


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

        List<Employees> employees = new ArrayList<>();
        List<KeyWords> keyWordsList = new ArrayList<>();

        for (int i = 1; i < rowCount + 1; i++) {
            Row row = sheet.getRow(i);

            Employees employeeData = new Employees();
            KeyWords keyWord = new KeyWords();

            if (sheetName.equals("Sheet1")) {
                for (int j = 0; j < (row.getLastCellNum() - 1); j++) {
                    employeeData.setFirstName(row.getCell(j).getStringCellValue());
                    employeeData.setLastName(row.getCell(j + 1).getStringCellValue());
                    employees.add(employeeData);
                }
            } else if (sheetName.equals("Admin")) {
                for (int j = 0; j < (row.getLastCellNum() - 1); j++) {
                    admin.setEmail(row.getCell(j).getStringCellValue());
                    admin.setPassword(row.getCell(j+1).getStringCellValue());
                }
            } else if(sheet.equals("Sheet2")){
                for (int j = 0; j < (row.getLastCellNum() - 1); j++) {
                    keyWord.setDiscipline(row.getCell(j).getStringCellValue());
                    keyWord.setSkill(row.getCell(j+1).getStringCellValue());
                    keyWordsList.add(keyWord);
                }
            }

        }
        if (sheetName.equals("Sheet1")) {
            return employees;
        } else if (sheetName.equals("Sheet2")) {
            return keyWordsList;
        } else if(sheetName.equals("Admin")){
            return admin;
        }

        return null;
    }
/*
    public static void main(String arg[]) throws IOException {
        ReadData objExcelFile = new ReadData();

        List<Employees> contacts = (List<Employees>) objExcelFile.readExcel("test.xls", "Sheet1");
        for (Employees contact : contacts) {
            System.out.println(contact.getFirstName() + " " + contact.getLastName());
        }


        List<KeyWords> skills = (List<KeyWords>) objExcelFile.readExcel("C:\\Users\\andpopescu\\IdeaProjects\\HRProject\\src\\main\\resources", "test.xls", "Sheet2");
        for (KeyWords skill : skills) {
            System.out.println(skill.getDiscipline() + " " +skill.getSkill());
        }
*/
}






