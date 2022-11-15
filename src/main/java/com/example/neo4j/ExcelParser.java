package com.example.neo4j;


import com.example.neo4j.entity.StudentEntity;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Component
public class ExcelParser {


    public ArrayList<StudentEntity> parseListOfUsers(InputStream inputStream) throws IOException {
        ArrayList<StudentEntity> students = new ArrayList<>();
        Workbook book =  WorkbookFactory.create(inputStream);
        Sheet sheet = book.getSheetAt(0);
        int rowIndex = 0;
        while (sheet.getRow(rowIndex) != null){
            StudentEntity student = new StudentEntity(sheet.getRow(rowIndex).getCell(0).getStringCellValue(),
                    (int) sheet.getRow(rowIndex).getCell(1).getNumericCellValue(), 0, true);
            students.add(student);
            rowIndex++;
        }
        System.out.println(students);
        return students;
    }


}
