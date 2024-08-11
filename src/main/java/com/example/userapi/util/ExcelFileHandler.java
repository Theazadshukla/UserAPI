package com.example.userapi.util;

import com.example.userapi.model.Address;
import com.example.userapi.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler {

    private static final String FILE_PATH = "src/main/resources/users.xlsx";

    public void writeUsersToFile(List<User> users) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Create header row
        String[] headers = {"Name", "Email", "Phone", "Role", "HouseNo", "City", "State", "Country"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Populate data rows
        int rowIndex = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(user.getName());
            row.createCell(1).setCellValue(user.getEmail());
            row.createCell(2).setCellValue(user.getPhone());
            row.createCell(3).setCellValue(user.getRole());
            row.createCell(4).setCellValue(user.getAddress().getHouseNo());
            row.createCell(5).setCellValue(user.getAddress().getCity());
            row.createCell(6).setCellValue(user.getAddress().getState());
            row.createCell(7).setCellValue(user.getAddress().getCountry());
        }

        // Write to file
        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    public List<User> readUsersFromFile() throws IOException {
        List<User> users = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                User user = new User();
                user.setName(row.getCell(0).getStringCellValue());
                user.setEmail(row.getCell(1).getStringCellValue());
                user.setPhone(row.getCell(2).getStringCellValue());
                user.setRole(row.getCell(3).getStringCellValue());

                Address address = new Address();
                address.setHouseNo(row.getCell(4).getStringCellValue());
                address.setCity(row.getCell(5).getStringCellValue());
                address.setState(row.getCell(6).getStringCellValue());
                address.setCountry(row.getCell(7).getStringCellValue());

                user.setAddress(address);
                users.add(user);
            }
        }

        return users;
    }
}
