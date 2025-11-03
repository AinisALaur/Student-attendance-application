package com.example.studentattendance.dataHandlers;

import com.example.studentattendance.Classes.AttendanceRecord;
import com.example.studentattendance.Classes.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DataImporterExcel implements DataImporter {
    @Override
    public ArrayList<Student> getStudents(String filePath) {
        try (FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            ArrayList<Student> students = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ArrayList<String> values = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            values.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            values.add(Double.toString(cell.getNumericCellValue()));
                            break;
                        default:
                            break;
                    }
                }

                String attendance = values.get(4);
                Student student = new Student(values.get(0), values.get(1), values.get(2), values.get(3));
                ArrayList<String> attendanceList = new ArrayList<>(Arrays.asList(attendance.split(" ")));

                for(int i = 0; i < attendanceList.toArray().length; i++){
                    AttendanceRecord attendanceRecord = new AttendanceRecord();
                    attendanceRecord.setAttendance(LocalDate.parse(attendanceList.get(i)));
                    if(attendanceRecord.getAttendance() != null){
                        student.addAttendance(attendanceRecord);
                    }else{
                        break;
                    }
                }

                students.add(student);
            }
            return students;


        } catch (IOException e) {
            return null;
        }
    }
}
