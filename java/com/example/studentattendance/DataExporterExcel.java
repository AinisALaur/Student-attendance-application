package com.example.studentattendance;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DataExporterExcel implements DataExporter {
    @Override
    public void exportStudents(ArrayList<Student> students, String filePath){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student Details");

        Map<String, Object[]> data = new TreeMap<>();
        int rowNumber = 1;
        for(Student student : students){
            String attendance = "";
            for(AttendanceRecord attended : student.getAttendance()){
                attendance += attended.getAttendance() + " ";
            }
            System.out.println(student.getInfo());
            data.put(Integer.toString(rowNumber), new Object[]{student.getName(), student.getLastName(), student.getId(), student.getGroup(), attendance});
            ++rowNumber;
        }
        int rowNum = 0;

        for (String key : data.keySet()) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }

        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
            System.out.println("StudentData.xlsx written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
