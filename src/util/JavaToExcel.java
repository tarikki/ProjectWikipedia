package util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Pepe on 8.5.2015.
 */
public class JavaToExcel {

/// PARAMS WILL BE: Node node, Date/String startDate, Date/String endDate, String sheetName
// Also String sheetName if we want more than 1 sheet

//TODO implement a loop that fills in the view count at the correct cell.

    public void writeExcel() throws IOException {

        /// Just to test stuff out. Will be removed and replaced with proper article names.
        ArrayList<String> articles = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            articles.add("Article #: " + i);
        }


        /// Timestamp for filename
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String timestamp = dateFormat.format(date);

        String fileName = FilePaths.EXCEL_FILES + timestamp + ".xls" + FilePaths.osPathCorrection();    //name and location of Excel file


        /// Stuff for the excel file
        String sheetName = "Sheet 1";    //name of Sheet of excel file
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);


        //// Y-axis for excel-sheet, contains our dates.
        HSSFRow yaxis = sheet.createRow(0);
        for (int i = 1; i < articles.size(); i++) { // Starts at 1 (cell 0, 1) because we want the very first cell to be empty
            yaxis.createCell(i).setCellValue("Date: " + i); /// replace with proper dates. Now just a string placeholder.
        }


        /// X-axis for excel-sheet, contains the article name
        for (int j = 1; j < articles.size(); j++) {
            HSSFRow row = sheet.createRow(j);
            row.createCell(0).setCellValue(articles.get(j)); /// Placeholder. Replace with proper articlenames.
        }


        //Write the workbook to an output stream.

        FileOutputStream finalExcel = new FileOutputStream(fileName);
        wb.write(finalExcel);
        System.out.println("Successfully saved the Excel to: " + fileName);
        finalExcel.flush();
        finalExcel.close();

    }

}


