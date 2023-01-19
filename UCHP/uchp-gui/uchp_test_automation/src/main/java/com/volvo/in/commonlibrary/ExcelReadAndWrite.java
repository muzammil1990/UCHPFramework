package com.volvo.in.commonlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

public class ExcelReadAndWrite {
    String fileName;
    Workbook wb;
    Sheet ws;
    String Textformat;
    SoftAssert softAssert = new SoftAssert();
    UIUtils keywords = new UIUtils();
    FileInputStream fileinputStream;
    public Logger APP_LOGS = Logger.getLogger(ExcelReadAndWrite.class);


    /**
     * @param fileName
     *            workbook name
     * @param sheetName
     *            workbook sheet name
     */
    public ExcelReadAndWrite(String fileName, String sheetName) {
        try {
            //
            // NOTE: Please note that the order in which the extensions are
            // checked
            // is not supposed to be changed.
            // ONLY if the extension is not of Excel-2010 format, we should
            // check for Excel-97 format
            // because the xls is part of xlsx as a string.
            //
            if (fileName.indexOf(".xlsx") > 0 || fileName.indexOf(".xls") > 0) {
                // The file probably has extension of Excel-2010 format (xlsx
                // extension)
                // Try opening it that way
                fileinputStream = new FileInputStream(fileName);
                wb = WorkbookFactory.create(fileinputStream);
                ws = wb.getSheet(sheetName);
                this.fileName = fileName;
            } else {
                // File could be of unknown extensions
                BaseClass.test.log(Status.ERROR, "provided file is not excel");
                Assert.fail("provided file is not excel");
            }
            /*
             * excelPath=fileName; sheetName=sheetName;
             */
        } catch (IOException io) {
            // Some other error encountered. May be the file does not exist.
            // Rather than deciphering the error, let's just pass it back to the
            // user
            BaseClass.test.log(Status.ERROR, "File not found");
            Assert.fail("File not found");
        } catch (InvalidFormatException io) {
            Reporter.log("Provided excel format is not valid");
            Assert.fail("Provided excel format is not valid");
        }

    }

    /**
     * Remove the contents in cell
     */
    public void removeContents() {
        try {
            for (int i = 1; i <= ws.getLastRowNum(); i++) {
                ws.removeRow(ws.getRow(i));
                ws.createRow(i);
            }
            FileOutputStream fos = new FileOutputStream(fileName);
            wb.write(fos);
        } catch (IOException io) {
            // Some other error encountered. May be the file does not exist.
            // Rather than deciphering the error, let's just pass it back to the
            // user
            Reporter.log("File not found");
            Assert.fail("File not found");
        }
    }

    /**
     * Write data into excel file
     * 
     * @param rowNum
     *            of excel sheet
     * @param colNum
     *            of excel sheet
     * @param data
     *            to write into the cell
     */
    public void setExcelRowData(int rowNum, int colNum, String data) {
        try {
            Row row = ws.getRow(rowNum);
            Cell cel = row.createCell(colNum);
            cel.setCellType(Cell.CELL_TYPE_STRING);
            cel.setCellValue(data);
            FileOutputStream fos = new FileOutputStream(fileName);
            wb.write(fos);
        } catch (IOException io) {
            // Some other error encountered. May be the file does not exist.
            // Rather than deciphering the error, let's just pass it back to the
            // user
            Reporter.log("File not found");
            Assert.fail("File not found");
        }
    }

    public void updateExcelRowData(int rowNum, int colNum, String newValue) {
        try {

            Row row = ws.getRow(rowNum);
            Cell cel = row.getCell(colNum);
            cel.setCellValue(newValue);
            FileOutputStream fos = new FileOutputStream(fileName);
            wb.write(fos);
            fos.close();
        } catch (IOException io) {
            APP_LOGS.info(io);
            Reporter.log("File not found");
            Assert.fail("File not found");
        }
    }

    /**
     * Get the used row count from sheet
     * 
     * @return row count
     */
    public int getRowCount() {

        int rowCount = ws.getPhysicalNumberOfRows();
        return rowCount;
    }

    /**
     * @param rowNum
     *            physically used row number
     * @return last cell column number
     */
    public int getColCount(int rowNum) {
        Row r = ws.getRow(rowNum);
        int colCount = r.getLastCellNum();
        return colCount;
    }

    /**
     * @param rowIndex
     *            value
     * @param columnIndex
     *            value
     * @return cell value in specified row and column
     */
    public String getCell(int rowIndex, int columnIndex) {
        String cellValue = null;

        cellValue = ws.getRow(rowIndex).getCell(columnIndex).toString();
        return cellValue;
    }

    /**
     * @return list of entire column cell value
     */
    @SuppressWarnings("rawtypes")
    public HashMap read() {
        LinkedHashMap<String, ArrayList<Object>> mapFile1 = new LinkedHashMap<String, ArrayList<Object>>();
        @SuppressWarnings("unused")
        int listSize = 0;
        int j = 0;
        int noOfColumns = ws.getRow(0).getLastCellNum();
        int rowCount = ws.getLastRowNum() - ws.getFirstRowNum();
        for (int i = 0; i < noOfColumns; i++) {
            Row firstrow = ws.getRow(0);
            ArrayList<Object> ele = new ArrayList<Object>();
            for (j = 1; j <= rowCount; j++) {
                Row row1 = ws.getRow(j);
                Cell cell = null;
                Object value = null;
                cell = row1.getCell(i);
                DataFormatter dt = new DataFormatter();
                value = dt.formatCellValue(cell);
                ele.add(value);
            }
            String mapValue = firstrow.getCell(i).getStringCellValue();
            mapFile1.put(mapValue, ele);// save each column data with first row
                                        // in column as key in hashmap
            listSize = ele.size();
        }
        return mapFile1;
    }

    /**
     * @return list of key names as column as header in worksheet
     * @throws IOException
     */
    public List<Object> readAll() throws IOException {

        @SuppressWarnings("unchecked")
        HashMap<String, ArrayList<Object>> mapFile2 = read();
        List<Object> listele = new ArrayList<Object>();
        int rownum = getRowCount();
        for (int i = 0; i < rownum - 1; i++) {
            HashMap<String, Object> tempMap = new HashMap<String, Object>();

            for (Object key : mapFile2.keySet()) {
                List<Object> list = mapFile2.get(key);
                tempMap.put((String) key, list.get(i));
            }
            listele.add(tempMap);
        }
        fileinputStream.close();
        return listele;
    }

    /******************************************
     * #Description : Method to extract data present in the ExcelSheet in text format and stores in the String variable
     */
    public void extractTextFromExcel() throws IOException {
        Textformat = " ";
        Iterator<Row> iterator = ws.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    Textformat = Textformat + cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    Textformat = Textformat + cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    Textformat = Textformat + cell.getNumericCellValue();
                    break;
                }
                Textformat = Textformat + "-";
            }
            Textformat = Textformat + "\n";
        }
        wb.close();
        keywords.APP_LOGS.info("=======================================================================");
        keywords.APP_LOGS.info("Exgtracted text from the ExcelSheet is::\n" + Textformat);
        keywords.APP_LOGS.info("=======================================================================");
        // return Textformat;
    }

    /**********************************************
     * #Description : Method to validate companyName, DocumentType and Importer present in the listcreditingdocument.xls file
     * 
     * #Input Parameters: 1. expectedPDFData: excel data 2. DownloadedFile: Downloaded file
     **********************************************/
    public void validateExceldata(HashMap<String, Object> expectedPDFData, File DownloadedFile) throws IOException {

        String strExcelDocFilename = null;
        try {
            if (DownloadedFile == null) {
                keywords.APP_LOGS.info("Invalid file name, makesure file downloaded successfull");
            }
            // String strgDownloadedFile=DownloadedFile.getAbsolutePath();
            PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
            strExcelDocFilename = pdfReadAndValidate.getFileName(DownloadedFile);
            strExcelDocFilename = (String) strExcelDocFilename;
            if (strExcelDocFilename.equals((String) expectedPDFData.get("ExcelFileName")))
                keywords.APP_LOGS.info("Downloaded document is verified, Document name is::  " + strExcelDocFilename);
            else
                keywords.APP_LOGS.info("Invalid Document!!!!!");
            softAssert.assertEquals(keywords.assertContains((String) expectedPDFData.get("Company"), Textformat), true);
            softAssert.assertEquals(keywords.assertContains((String) expectedPDFData.get("DocumentType"), Textformat), true);
            softAssert.assertEquals(keywords.assertContains((String) expectedPDFData.get("Importer"), Textformat), true);
            softAssert.assertAll();
        } catch (Exception e) {
            keywords.APP_LOGS.info("Exception caught while validating the document");
            APP_LOGS.error(e.getMessage());
        }
    }
 public String readExcelDataOnSpecifiedColumn(String excelFile, String sheetNameToRead,int cellRowNo, int cellColumnNo){
        try {
            FileInputStream fs = new FileInputStream(excelFile);
            jxl.Workbook wb = jxl.Workbook.getWorkbook(fs);

            // TO get the access to the sheet
            jxl.Sheet sh = wb.getSheet(sheetNameToRead);
            // To get the number of rows present in sheet
            return sh.getCell(cellColumnNo, cellRowNo).getContents() ;
            
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return null;
  }
}
