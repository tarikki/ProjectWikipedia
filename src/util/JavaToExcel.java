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


//TODO make sure there is no error when counting loops from 0 / 1 (because we need to skip 1st cell)
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

        String fileName = FilePaths.EXCEL_FILES + timestamp + ".xlsx" + FilePaths.osPathCorrection();    //name and location of Excel file


        /// Stuff for the excel file
        String sheetName = "Sheet 1";    //name of Sheet of excel file
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);

        DateTime start = graph.getJodaStartDate();
        DateTime end = graph.getJodaEndDate();

        int range = Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays();

        //Y axis (dates)
        XSSFRow yaxis = sheet.createRow(0);


        /// ERROR MIGHT BE HERE with the counter.
        int counter = 0;
        for (DateTime currentDate = start; currentDate.isBefore(end); currentDate = currentDate.plusDays(1))
        {

            counter++;
            yaxis.createCell(counter).setCellValue(currentDate.toString(fmt));
            sheet.autoSizeColumn(counter);


        /// X axis (article names)
        XSSFRow first = sheet.createRow(1); /// so we can skip 1st cell. OR ERROR MIGHT BE HERE
        first.createCell(0).setCellValue(nodesToProcess.get(0).getArticleName());


        for (int k = 1; k < nodesToProcess.size(); k++) {
            Node node = nodesToProcess.get(k);

            XSSFRow row = sheet.createRow(k);
            row.createCell(0).setCellValue(node.getArticleName());


            for (int j = 1; j <= range; j++) {
                row.createCell(j).setCellValue(node.getViewCountForDay(currentDate));
            }
        }
        }


        //Write the workbook to an output stream.
        sheet.autoSizeColumn(0); // This seems to take a LONG time. Might be easier to just do it manually.

        System.out.println("Starting to write the Excel file..");
        FileOutputStream finalExcel = new FileOutputStream(fileName);
        wb.write(finalExcel);
        System.out.println("Successfully saved the Excel to: " + fileName);
        finalExcel.flush();
        finalExcel.close();

    }

}


