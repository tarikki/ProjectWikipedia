package util;

import modules.Graph;
import modules.Node;
import modules.StatisticsNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pepe on 23.5.2015.
 */
public class CorrelationExcel {
    private GraphIO graphIO;
    private XSSFWorkbook wb;
    private ArrayList<XSSFSheet> sheets;
    private ArrayList<StatisticsNode> statisticsNodes;
    private Graph graph;
    private DateFormat dateFormat;
    private String timestamp;

    public void createStatisticsNodes(String graphLocation) {
        ArrayList<Node> nodesToProcess;

        statisticsNodes = new ArrayList<>();

        graphIO = new GraphIO();
        graph = graphIO.loadGraph(graphLocation);
        nodesToProcess = graph.getNodes();

        for (Node node : nodesToProcess) {
            StatisticsNode statisticsNode = new StatisticsNode(node.getArticleId(), node.getArticleName(), node.getDistanceFromStart(), node.getMeanViewCountsForPeriod());
            statisticsNodes.add(statisticsNode);
        }
    }

    public void createWorkBook() {

        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        timestamp = dateFormat.format(date);


        wb = new XSSFWorkbook();
        sheets = new ArrayList<>();

        /// Stuff for the excel file
        String sheetName = "";    //name of Sheet of excel file


        /// Create sheets.
        for (int i = 0; i <= 1; i++) {
            sheetName = "Articles " + i + " hop(s) from start";
            XSSFSheet sheet = wb.createSheet(sheetName);
            sheets.add(sheet);
        }
    }


    public void writeExcel() throws IOException {
        createWorkBook();


        String fileName = FilePaths.EXCEL_FILES_CORRELATION + graph.getStartingArticle() +" " + timestamp +  ".xlsx" + FilePaths.osPathCorrection();    //name and location of Excel file
        System.out.println(fileName);

        for (XSSFSheet sheet : sheets) {
            Row titleRow = sheet.createRow(0);
            titleRow.createCell(1).setCellValue("Correlation");
            titleRow.createCell(2).setCellValue("Standard deviation");
            titleRow.createCell(3).setCellValue("Mean");
            titleRow.createCell(4).setCellValue("Covariance");


        }


        int c0 = 1;
        int c1 = 1;


        for (int i = 0; i < statisticsNodes.size(); i++) {
            StatisticsNode statisticsNode = statisticsNodes.get(i);


            if (statisticsNode.getDistanceFromStart() == 0) {
                Row r = sheets.get(0).createRow(c0);

                Cell cell = r.createCell(0);
                cell.setCellValue(statisticsNodes.get(i).getArticleName()); // Article name

                Cell cell1 = r.createCell(1); /// Correlation
                cell1.setCellValue(statisticsNodes.get(i).getCorrelation());

                Cell cell2 = r.createCell(2); /// Standard deviation
                cell2.setCellValue(statisticsNodes.get(i).getSTD());

                Cell cell3 = r.createCell(3);  // Mean
                cell3.setCellValue(statisticsNodes.get(i).getMeanViewCountsForPeriod());

                Cell cell4 = r.createCell(4); // Covariance
                cell4.setCellValue(statisticsNodes.get(i).getCovariance());

                c0++;
                

            }

            if (statisticsNode.getDistanceFromStart() == 1) {
                Row r1 = sheets.get(1).createRow(c1);
                Cell cellr0 = r1.createCell(0); //

                cellr0.setCellValue(statisticsNodes.get(i).getArticleName()); // Article name

                Cell cellc1 = r1.createCell(1); // Correlation
                cellc1.setCellValue(statisticsNodes.get(i).getCorrelation());

                Cell cellc2 = r1.createCell(2); /// Standard deviation
                cellc2.setCellValue(statisticsNodes.get(i).getSTD());

                Cell cellc3 = r1.createCell(3);  // Mean
                cellc3.setCellValue(statisticsNodes.get(i).getMeanViewCountsForPeriod());

                Cell cellc4 = r1.createCell(4); // Covariance
                cellc4.setCellValue(statisticsNodes.get(i).getCovariance());


                c1++;
                System.out.println(c1);
            }
        }

        autoSizeColumns();
        FileOutputStream finalExcel = new FileOutputStream(fileName);
        wb.write(finalExcel);
        finalExcel.flush();
        finalExcel.close();


    }

    public void autoSizeColumns() {
        for (XSSFSheet sheet : sheets) {

            for (int i = 0; i <= 4; i++) {
                sheet.autoSizeColumn(i);

            }
        }
    }
}



