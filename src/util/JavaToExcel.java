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
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
    private GraphIO graphIO;
    private String timestamp;
    private DateFormat dateFormat;
    private org.joda.time.format.DateTimeFormatter fmt;

    private DateTime start;
    private DateTime end;

    private XSSFWorkbook wb;
    private ArrayList<XSSFSheet> sheets;
    private static final int MAX_DISTANCE = 2;

    public JavaToExcel() {
        graphIO = new GraphIO();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        timestamp = dateFormat.format(date);
        fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
    }


    public void createWorkBook() {
        wb = new XSSFWorkbook();
        sheets = new ArrayList<>();

        /// Stuff for the excel file
        String sheetName = "";    //name of Sheet of excel file


        /// Create sheets.
        for (int i = 0; i <= MAX_DISTANCE; i++) {
            sheetName = "Articles " + i + " hop(s) from start";
            XSSFSheet sheet = wb.createSheet(sheetName);
            sheets.add(sheet);

        }
    }

    public void autoSizeArticleNameColumn() {
        for (XSSFSheet sheet : sheets) {
            sheet.autoSizeColumn(0);
        }
    }


    public void writeExcel(String graphLocation) throws IOException {


        createWorkBook();

        ArrayList<Node> nodesToProcess;

        graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph(graphLocation);
        nodesToProcess = graph.getNodes();


        String fileName = FilePaths.EXCEL_FILES + graph.getStartingArticle() + " " + timestamp + ".xlsx" + FilePaths.osPathCorrection();    //name and location of Excel file

        final long startTime = System.nanoTime();
        System.out.println(nodesToProcess.size() + " nodes to process.");
        System.out.println("Starting to write the Excel file..");

        start = graph.getJodaStartDate();
        end = graph.getJodaEndDate();

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

        // Sheet row counters.
        int c0 = 1;
        int c1 = 1;
        int c2 = 1;

        // X-axis. Populates field with article names.
        for (int i = 1; i < nodesToProcess.size(); i++) {
            Node node = nodesToProcess.get(i - 1);


            if (node.getDistanceFromStart() == 0) {
                Row r = sheets.get(0).createRow(c0);
                Cell cell = r.createCell(0);
                cell.setCellValue(nodesToProcess.get(i).getArticleName());
                c0++;

            }

            if (node.getDistanceFromStart() == 1) {
                Row r1 = sheets.get(1).createRow(c1);
                Cell cell1 = r1.createCell(0);
                cell1.setCellValue(nodesToProcess.get(i).getArticleName());
                c1++;
            } else {
                Row r2 = sheets.get(2).createRow(c2);
                Cell cell2 = r2.createCell(0);
                cell2.setCellValue(nodesToProcess.get(i).getArticleName());
                c2++;


            }


        }


        // Loop that populates fields with view count for each article

        int counter = 1; /// Counter for days.

        for (DateTime currentDate = graph.getJodaStartDate(); !currentDate.isAfter(end); currentDate = currentDate.plusDays(1)) {
            System.out.println("Going through date: " + currentDate);

            /// Sheet row counters
            int x0 = 1;
            int x1 = 1;
            int x2 = 1;

            for (int i = 1; i < nodesToProcess.size(); i++) {
                Node node = nodesToProcess.get(i - 1);


                if (node.getDistanceFromStart() == 0) {
                    Row row = sheets.get(0).getRow(x0);


                    if (row == null) {
                        row = sheets.get(0).createRow(x0 + 1);
                    }
                    row.createCell(counter).setCellValue(node.getViewCountForDay(currentDate));
                    x0++;
                }

                if (node.getDistanceFromStart() == 1) {
                    Row row1 = sheets.get(1).getRow(x1);


                    if (row1 == null) {
                        row1 = sheets.get(1).createRow(x1 + 1);
                    }
                    row1.createCell(counter).setCellValue(node.getViewCountForDay(currentDate));
                    x1++;
                }

                else {
                    Row row2 = sheets.get(2).getRow(x2);
                    if (row2 == null) {
                        row2 = sheets.get(2).createRow(x2 + 1);
                    }
                    row2.createCell(counter).setCellValue(node.getViewCountForDay(currentDate));
                    x2++;

                }


            }
            counter++;
        }


        autoSizeArticleNameColumn();

        //Write the workbook to an output stream.
        FileOutputStream finalExcel = new FileOutputStream(fileName);
        wb.write(finalExcel);
        System.out.println("Successfully saved the Excel to: " + fileName);
        final long duration = System.nanoTime() - startTime;
        double seconds = (double)duration / 1000000000.0;

        System.out.println("This process took about: " + seconds + "seconds" );

        finalExcel.flush();
        finalExcel.close();


    }


}


