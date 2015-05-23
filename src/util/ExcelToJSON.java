package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Pepe on 23.5.2015.
 */
public class ExcelToJSON {
    private Gson gson;
    private FileWriter fileWriter;
    private ArrayList<String> articleNames;

    public void getArticleNamesFromExcel(String filepath) throws IOException {


        FileInputStream file = new FileInputStream(new File(filepath));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first sheet from the workbook
        XSSFSheet sheet1 = workbook.getSheetAt(0);

        articleNames = new ArrayList<>();

        articleNames.add(sheet1.getRow(1).getCell(0).getStringCellValue());

        XSSFSheet sheet2 = workbook.getSheetAt(1);

        Iterator rowIterator = sheet2.rowIterator();

        rowIterator.next(); // Skip 1st row (dates-label)

        while (rowIterator.hasNext())
        {
            Row row = (Row) rowIterator.next();
            articleNames.add(row.getCell(0).getStringCellValue());
        }


        gson = new GsonBuilder().setPrettyPrinting().create();

        String articlesJSON = gson.toJson(articleNames);

        String savePath = FilePaths.ARTICLE_JSONS + articleNames.get(0) +".json" + FilePaths.osPathCorrection();

        try {
            fileWriter = new FileWriter(savePath);
            fileWriter.write(articlesJSON);
            System.out.println("Successfully saved the file to: " + savePath);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }


