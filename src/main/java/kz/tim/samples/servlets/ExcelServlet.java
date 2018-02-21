package kz.tim.samples.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kz.tim.samples.dto.Occurrence;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Documentation for {@code ExcelServlet}.
 *
 * @author Timur Tibeyev.
 */
@WebServlet("excel")
@MultipartConfig
public class ExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String word = request.getParameter("word");
        Part filePart = request.getPart("excelFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        if (filePart == null || fileContent.available() == 0) {
            request.setAttribute("error", "File is empty");
        } else if (word == null && word.isEmpty()) {
            request.setAttribute("error", "Word is empty");
        } else {
            List<Occurrence> occurrences = new ArrayList<>();
            word = word.toLowerCase();

            Workbook workbook = new XSSFWorkbook(fileContent);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                Iterator<Row> rowIterator = sheet.rowIterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String value = cell.getStringCellValue();
                        if (value != null && !value.isEmpty()) {
                            if (value.toLowerCase().contains(word)) {
                                String sheetName = sheet.getSheetName();
                                int rowNum = row.getRowNum();
                                int columnIndex = cell.getColumnIndex();
                                occurrences
                                        .add(new Occurrence(value, sheetName, rowNum, columnIndex));
                            }
                        }
                    }
                }
            }
            request.setAttribute("occurrences", occurrences);
            fileContent.close();
        }
        request.getRequestDispatcher("excel.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
