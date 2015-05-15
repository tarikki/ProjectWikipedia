package util;

import main.GraphGenerator;
import modules.Graph;
import modules.Node;
import org.apache.poi.hslf.model.Sheet;
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

        int maxDistance = 2;
        ArrayList<Node> nodesToProcess;

        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph(graphLocation);
        nodesToProcess = graph.getNodes();

        int highest = 0;


        for (Node node : nodesToProcess) {

            if (node.getDistanceFromStart() > highest) {
                highest = node.getDistanceFromStart();
            }
        }


        /// Timestamp for filename
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String timestamp = dateFormat.format(date);

        org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");

        String fileName = FilePaths.EXCEL_FILES + graph.getStartingArticle() + " " + timestamp + ".xlsx" + FilePaths.osPathCorrection();    //name and location of Excel file


        /// Stuff for the excel file
        String sheetName = "";    //name of Sheet of excel file

        ArrayList<XSSFSheet> sheets = new ArrayList<>();

        XSSFWorkbook wb = new XSSFWorkbook();

        /// Create sheets.
        for (int i = 1; i < maxDistance + 1; i++) {
            sheetName = "Sheet with hop count " + i;
            XSSFSheet sheet = wb.createSheet(sheetName);
            sheets.add(sheet);

        }

        System.out.println(sheets.size());
        DateTime start = graph.getJodaStartDate();
        DateTime end = graph.getJodaEndDate();


        System.out.println(nodesToProcess.size() + " nodes to process.");
        System.out.println("Starting to write the Excel file..");


        int daysBetween = Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays();

        // Y-axis. Populates fields with dates in range.


        for (XSSFSheet sheet : sheets) {
            Row titleRow = sheet.createRow(0);

            for (int i = 1; i <= daysBetween + 1; i++) {
                titleRow.createCell(i).setCellValue(start.toString(fmt));
                start = start.plusDays(1);
                sheet.autoSizeColumn(i);

            }
            start = graph.getJodaStartDate();
        }

int c1 = 1;
        int c2 = 1;
        // X-axis. Populates field with article names.
        for (int i = 1; i < nodesToProcess.size(); i++) {
            Node node = nodesToProcess.get(i - 1);


            if (node.getDistanceFromStart()==1) {
                Row r = sheets.get(0).createRow(c1);
                Cell cell = r.createCell(0);
                cell.setCellValue(nodesToProcess.get(i).getArticleName());
            c1++;
            }

              else{
                    Row r1 = sheets.get(1).createRow(c2);
                    Cell cell1 = r1.createCell(0);
                    cell1.setCellValue(nodesToProcess.get(i).getArticleName());
                c2++;


            }


        }


        // Loop that populates fields with view count for each article
        int counter = 1;

        for (DateTime currentDate = graph.getJodaStartDate(); !currentDate.isAfter(end); currentDate = currentDate.plusDays(1)) {
            System.out.println("Going through date: " + currentDate);

            int x1 = 1;
            int x2 = 1;
            for (int i = 1; i < nodesToProcess.size(); i++) {
                Node node = nodesToProcess.get(i - 1);

                switch (node.getDistanceFromStart()) {

                    case 1:
                        Row row = sheets.get(0).getRow(x1);


                        if (row == null) {
                            row = sheets.get(0).createRow(x1 + 1);
                        }
                        row.createCell(counter).setCellValue(node.getViewCountForDay(currentDate));
                        x1++;
                        break;
                    case 2:
                        Row row1 = sheets.get(1).getRow(x2);
                        if (row1 == null) {
                            row1 = sheets.get(1).createRow(x2 + 1);
                        }
                        row1.createCell(counter).setCellValue(node.getViewCountForDay(currentDate));
                        x2++;
                        break;
                    default:
                        break;
                }


            }
            counter++;
        }


        for (XSSFSheet sheet : sheets)
        {
            sheet.autoSizeColumn(0);
        }

            //Write the workbook to an output stream.
            FileOutputStream finalExcel = new FileOutputStream(fileName);
            wb.write(finalExcel);
            System.out.println("Successfully saved the Excel to: " + fileName);
            finalExcel.flush();
            finalExcel.close();




    }


}


