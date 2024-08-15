package com.example.userapi.util;

import com.example.userapi.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelFileHandler {

    private static final String FILE_PATH = "userdata.xlsx";

    public void writeToExcel(List<User> users) throws IOException {
        Workbook workbook;
        Sheet sheet;


        java.io.File file = new java.io.File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH)) {
                workbook = new XSSFWorkbook(fileInputStream);
                sheet = workbook.getSheetAt(0);
            }
        } else {

            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Users");
            createHeader(sheet);
        }


        int rowCount = sheet.getLastRowNum();
        for (int i = rowCount; i > 0; i--) {
            sheet.removeRow(sheet.getRow(i));
        }


        int rowIndex = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(user.getName());
            row.createCell(1).setCellValue(user.getEmail());
            row.createCell(2).setCellValue(user.getPhone());
            row.createCell(3).setCellValue(user.getRole());


            if (user.getAddress() != null) {
                row.createCell(4).setCellValue(user.getAddress().getHouseNumber());
                row.createCell(5).setCellValue(user.getAddress().getCity());
                row.createCell(6).setCellValue(user.getAddress().getState());
                row.createCell(7).setCellValue(user.getAddress().getCountry());
            } else {
                row.createCell(4).setCellValue("N/A");
                row.createCell(5).setCellValue("N/A");
                row.createCell(6).setCellValue("N/A");
                row.createCell(7).setCellValue("N/A");
            }
        }


        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    private void createHeader(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.createCell(1).setCellValue("Email");
        header.createCell(2).setCellValue("Phone/Mobile");
        header.createCell(3).setCellValue("Role");
        header.createCell(4).setCellValue("House Number");
        header.createCell(5).setCellValue("City");
        header.createCell(6).setCellValue("State");
        header.createCell(7).setCellValue("Country");
    }
}
