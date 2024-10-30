package com.example.userapi.util;

import com.example.userapi.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Value;
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

        // Clear existing rows (if any)
        int rowCount = sheet.getLastRowNum();
        for (int i = rowCount; i > 0; i--) {
            sheet.removeRow(sheet.getRow(i));
        }

        // Start adding rows from row 1, as row 0 is reserved for headers
        int rowIndex = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowIndex++);
            writeUserData(row, user);
        }

        // Write data back to the file
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH)) {
            workbook.write(fileOutputStream);
        }

        workbook.close();
    }

    // Create Excel header with the correct format/order
    private void createHeader(Sheet sheet) {
        Row header = sheet.createRow(0);
        String[] columns = {
                "ID", "Name", "Role", "Phone", "Email",
                "House Number", "City", "State", "Country"
        };
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }
    }

    // Write user data to Excel in the specified order
    private void writeUserData(Row row, User user) {
        row.createCell(0).setCellValue(user.getId()); // ID
        row.createCell(1).setCellValue(user.getName()); // Name
        row.createCell(2).setCellValue(user.getRole()); // Role
        row.createCell(3).setCellValue(user.getPhone()); // Phone
        row.createCell(4).setCellValue(user.getEmail()); // Email

        // Address details
        row.createCell(5).setCellValue(user.getAddress().getHouseNumber()); // House Number
        row.createCell(6).setCellValue(user.getAddress().getCity());        // City
        row.createCell(7).setCellValue(user.getAddress().getState());       // State
        row.createCell(8).setCellValue(user.getAddress().getCountry());     // Country
    }
}