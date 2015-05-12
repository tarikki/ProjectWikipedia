package util;

import main.GraphGenerator;
import modules.Graph;
import modules.Node;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by Pepe on 8.5.2015.
 */
public class JavaToExcel {

    // TODO decide what to use (HSSF / XSSF) Need to do testing with bigger datasets.

    public void writeExcel(String graphLocation) throws IOException {

        ArrayList<Node> nodesToProcess;

        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph(graphLocation);
        nodesToProcess = graph.getNodes();


        /// Timestamp for filename
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String timestamp = dateFormat.format(date);

        org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");

        String fileName = FilePaths.EXCEL_FILES + graph.getStartingArticle() + " " + timestamp + ".xlsx" + FilePaths.osPathCorrection();    //name and location of Excel file


        /// Stuff for the excel file
        String sheetName = "Sheet 1";    //name of Sheet of excel file
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);

        DateTime start = graph.getJodaStartDate();
        DateTime end = graph.getJodaEndDate();


        System.out.println(nodesToProcess.size() + " nodes to process.");
        System.out.println("Starting to write the Excel file..");


        int daysBetween = Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays();

        // Y-axis. Populates fields with dates in range.
        Row titleRow = sheet.createRow(0);

        for (int i = 1; i <= daysBetween + 1; i++) {
            titleRow.createCell(i).setCellValue(start.toString(fmt));
            start = start.plusDays(1);
            sheet.autoSizeColumn(i);
        }


        // X-axis. Populates field with article names.
        for (int i = 1; i < nodesToProcess.size(); i++) {
            Row r = sheet.createRow(i);
            r.createCell(0).setCellValue(nodesToProcess.get(i).getArticleName());
        }


        // Loop that populates fields with view count for each article
        int counter = 1;

        for (DateTime currentDate = graph.getJodaStartDate(); !currentDate.isAfter(end); currentDate = currentDate.plusDays(1)) {
            System.out.println("Going through date: " + currentDate);


            for (int i = 1; i < nodesToProcess.size(); i++) {
                Node node = nodesToProcess.get(i - 1);
                Row row = sheet.getRow(i);

                if (row == null) {
                    row = sheet.createRow(i + 1);
                }
                row.createCell(counter).setCellValue(node.getViewCountForDay(currentDate));
            }
            counter++;


        }


        sheet.autoSizeColumn(0); // This seems to take a LONG time. Might be easier to just do it manually.

        //Write the workbook to an output stream.
        FileOutputStream finalExcel = new FileOutputStream(fileName);
        wb.write(finalExcel);
        System.out.println("Successfully saved the Excel to: " + fileName);
        finalExcel.flush();
        finalExcel.close();

    }

}


