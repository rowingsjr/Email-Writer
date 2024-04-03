package org.example;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FileReader
{
    public static List<String> headers = new ArrayList<>();
    public static void readExcelFile() throws IOException
    {
        String filePath = "C:\\Users\\richa\\Desktop\\Book1Test.xlsx";
        FileInputStream file = new FileInputStream(new File(filePath));
        Map<String, List<String>> data = new HashMap<>();
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);

        for (int cn = 0; cn < headerRow.getLastCellNum(); cn++) {
            Cell cell = headerRow.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            headers.add(cell.toString());
            data.put(cell.toString(), new ArrayList<>());
        }

        // Read the data and store it in the map
        for (int rn = 1; rn <= sheet.getLastRowNum(); rn++) {
            Row row = sheet.getRow(rn);
            if (row == null) continue; // This skips empty rows

            for (int cn = 0; cn < headers.size(); cn++) {
                Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String header = headers.get(cn);
                data.get(header).add(cell.toString());
            }
        }

        //TODO: add another iterator for the

    }
}
