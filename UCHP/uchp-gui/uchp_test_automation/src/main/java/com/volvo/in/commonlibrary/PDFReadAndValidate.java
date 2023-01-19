package com.volvo.in.commonlibrary;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Point;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class PDFReadAndValidate {
    public static String gstrPDFFileData;
    WebDriver driver;
    public File gDownloadedFile;
    UIUtils keywords = new UIUtils(driver);
    public Logger APP_LOGS = Logger.getLogger(PDFReadAndValidate.class);

    AppBasedUtils appBasedUtils = new AppBasedUtils();

    /*******************************************
     * #Description : This is a common function to extract PDF data in Text format and stores in string object
     ********************************************/
    public String readPDFFile(File strFileName, HashMap<String, Object> data) throws IOException {
        gstrPDFFileData = null;
        APP_LOGS.info("Extracting text data from the file:: " + strFileName);
        try {
            if(isFileDownloaded_Ext(strFileName,"pdf",data)){

            APP_LOGS.info(strFileName + " File exist");
            PDFParser parser;
            COSDocument cosDoc;
            PDFTextStripper pdfStripper;
            PDDocument pdDoc;
            RandomAccessFile randomAccessFile = new RandomAccessFile(strFileName, "r");
            parser = new PDFParser(randomAccessFile);
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdDoc.getNumberOfPages();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(pdDoc.getNumberOfPages());
            gstrPDFFileData = pdfStripper.getText(pdDoc);
            if (gstrPDFFileData == null) {
                APP_LOGS.info("Not able to extract data from PDF file, Make sure file downloaded successfully");
            }
            pdDoc.close();
            APP_LOGS.info("***************************************************************************");
            APP_LOGS.info("Extracted text data from PDF file is:\n" + gstrPDFFileData);
            APP_LOGS.info("***************************************************************************");
            randomAccessFile.close();
            }
            else{
                keywords.APP_LOGS.info(strFileName + " File doesn't exist");
                APP_LOGS.error("File doesn't exist");
                Assert.fail();
            }
        }
         catch (Exception e) {
            keywords.APP_LOGS.info(strFileName + " File doesn't exist");
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
        return gstrPDFFileData;
    }

    /********************************************
     * #Description : This fuction takes filePath as input and return FileName #Input Parameters: File path
     ********************************************/
    public String getFileName(File fileabsolutepath) {
        String filename = fileabsolutepath.getName();
        int pos = filename.lastIndexOf(".");
        if (pos > 0) {
            filename = filename.substring(0, pos);
        }
        return filename;
    }

    /********************************************
     * #Description : This fuction takes filePath as input and return FileName #Input Parameters: File path
     ********************************************/
    public String getFolderNameOfFile(File fileabsolutepath) {
        String fileName = fileabsolutepath.getAbsolutePath();
        return fileName.substring(0, fileName.lastIndexOf('\\'));
    }

    /********************************************
     * #Description : This is common function to download file on Chrome, Fireforx and InternetExplorer browser
     *
     * #Input Parameters: strBrowserType: It is browsertype Ex: Firefox,Chrome,IE strXpathlocation: WebElement of document to be downloaded
     ********************************************/

    public void downloadDocuments(String strBrowserType, WebElement strXpathlocation, WebDriver driver, File pathToSave) {
        String sPathSep = System.getProperty("file.separator");
        String AutoITscriptLocation = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "AutoITScripts";
        if (strBrowserType.equalsIgnoreCase("Chrome")) {
            try {
                strXpathlocation.click();
                keywords.APP_LOGS.info("PDF Document is downloaded successfully");
            } catch (Exception e) {
                APP_LOGS.info("Error occured while downloading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        }

        else if (strBrowserType.equalsIgnoreCase("IE") || strBrowserType.equalsIgnoreCase("Internet Explorer")) {
            try {
                
                Robot robot = new Robot();
                Point coordinates = strXpathlocation.getLocation();
                strXpathlocation.click();
                strXpathlocation.sendKeys("");
                //simulate pressing enter            
                         robot.keyPress(KeyEvent.VK_ENTER);
                         robot.keyRelease(KeyEvent.VK_ENTER);
                //wait for the modal dialog to open            
                         Thread.sleep(2000);
                //press s key to save            
                         robot.keyPress(KeyEvent.VK_ALT);
                         robot.keyPress(KeyEvent.VK_N);

                         robot.keyRelease(KeyEvent.VK_N);
                         robot.keyRelease(KeyEvent.VK_ALT);
                         Thread.sleep(3000);
               //press enter to save the file with default name and in default location
                         robot.keyPress(KeyEvent.VK_TAB);
                         robot.keyRelease(KeyEvent.VK_TAB);

                         Thread.sleep(3000);

                         robot.keyPress(KeyEvent.VK_DOWN);
                         robot.keyRelease(KeyEvent.VK_DOWN);

                         Thread.sleep(3000);

                         robot.keyPress(KeyEvent.VK_DOWN);
                         robot.keyRelease(KeyEvent.VK_DOWN);

                         Thread.sleep(3000);
                          Runtime rt = Runtime.getRuntime();
                          rt.exec(AutoITscriptLocation + sPathSep + "IEFileSaveAs.exe");

            } catch (Exception e) {
                APP_LOGS.info("Error occured while downloading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        } else if (strBrowserType.equalsIgnoreCase("Firefox") || strBrowserType.equalsIgnoreCase("Mozilla")) {
            try {

                strXpathlocation.click();
               // Runtime rt = Runtime.getRuntime();
               // rt.exec(AutoITscriptLocation + sPathSep + "FirefoxSaveAs.exe");
                keywords.waitTime1(5);
                APP_LOGS.info("Document is downloaded successfully ");
            } catch (Exception e) {
                APP_LOGS.info("Error occured while downloading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        }

    }
    /********************************************
     * #Description : This is common function to download file on Chrome, Fireforx and InternetExplorer browser
     *
     * #Input Parameters: strBrowserType: It is browsertype Ex: Firefox,Chrome,IE File path to be saved
     ********************************************/

    public void downloadDocuments(String strBrowserType, WebDriver driver, File pathToSave) {
        String sPathSep = System.getProperty("file.separator");
        String AutoITscriptLocation = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "AutoITScripts";
        if (strBrowserType.equalsIgnoreCase("Chrome")) {
            try {
                keywords.APP_LOGS.info("PDF Document is downloaded successfully");
            } catch (Exception e) {
                APP_LOGS.info("Error occured while downloading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        }

        else if (strBrowserType.equalsIgnoreCase("IE") || strBrowserType.equalsIgnoreCase("Internet Explorer")) {
            try {
                Runtime rt = Runtime.getRuntime();
                // rt.exec(".\\resources\\AutoITScripts\\FileSaveAs.exe");
                rt.exec(AutoITscriptLocation + sPathSep + "IEFileSaveAs.exe " + pathToSave.getAbsolutePath());
                keywords.waitTime1(1);
                APP_LOGS.info("Document is downloaded successfully");

            } catch (Exception e) {
                APP_LOGS.info("Error occured while downloading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        } else if (strBrowserType.equalsIgnoreCase("Firefox") || strBrowserType.equalsIgnoreCase("Mozilla")) {
            try {
            /*    Runtime rt = Runtime.getRuntime();
                rt.exec(AutoITscriptLocation + sPathSep + "FirefoxSaveAs.exe");
                keywords.waitTime1(5);*/
                APP_LOGS.info("Document is downloaded successfully ");
            } catch (Exception e) {
                APP_LOGS.info("Error occured while downloading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        }

    }

    /********************************************
     * #Description : attaches individual documents to claim job #Input Parameters: strBrowserType: It is browsertype Ex: Firefox,Chrome,IE strXpathlocation:
     * WebElement of document to be downloaded
     ********************************************/

    public void uploadDocuments(String strBrowserType, File documentLocation) {
        String sPathSep = System.getProperty("file.separator");
        String AutoITscriptLocation = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "AutoITScripts";
        if (strBrowserType.equalsIgnoreCase("Firefox") || strBrowserType.equalsIgnoreCase("Mozilla")) {
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec(AutoITscriptLocation + sPathSep + "FireFoxFileUpload.exe " + documentLocation.getAbsolutePath());
                keywords.waitTime1(5);
                APP_LOGS.info("attachment is uploaded successfully ");
            } catch (Exception e) {
                APP_LOGS.info("Error occured while uploading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        }

        else if (strBrowserType.equalsIgnoreCase("Chrome")) {
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec(AutoITscriptLocation + sPathSep + "ChromeFileUpload.exe " + documentLocation.getAbsolutePath());
                keywords.waitTime1(5);
                APP_LOGS.info("attachment is uploaded successfully");
            } catch (Exception e) {
                APP_LOGS.info("Error occured while Uploading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        }

        else if (strBrowserType.equalsIgnoreCase("IE") || strBrowserType.equalsIgnoreCase("Internet Explorer")) {
            try {

                Runtime rt = Runtime.getRuntime();
                rt.exec(AutoITscriptLocation + sPathSep + "IEFileUpload.exe " + documentLocation.getAbsolutePath());
                keywords.waitTime1(5);
                APP_LOGS.info("attachment is uploaded successfully");

            } catch (Exception e) {
                APP_LOGS.info("Error occured while downloading the attachement");
                APP_LOGS.error(e.getMessage());
            }
        }

    }

    /********************************************
     * #Description : This is common function to clean all files and directories #Input Parameters: folderToclean: folder/pathName in which all files and
     * subdirectories will be cleaned
     ********************************************/

    public void cleanFolder(File folderToclean) {
        if (folderToclean.exists()) {
            try {
                FileUtils.cleanDirectory(folderToclean);
            } catch (IOException e) {
                APP_LOGS.error(e.getMessage());
                keywords.APP_LOGS.info("Not able to clean files,Please make sure file is being opened or not");
            }
        }
    }


    /********************************************
     * get the file size(No of files exist in folder)
     *
     * #Description : This is common function to get count of no of file
     *
     * #Input Parameters: folderToclean: folder/pathName in which all files and subdirectories
     * ********************************************/

    public long downloadLocationFolderFilesSize(){
        String sPathSep = System.getProperty("file.separator");
        File downloadLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation");
        return  downloadLocation.listFiles().length;
    }

    /********************************************
     * defcleanFolder(File folderToclean)
     *
     * #Description : This is common function to clean all files and directories
     *
     * #Input Parameters: folderToclean: folder/pathName in which all files and subdirectories will be cleaned
     ********************************************/

    public void deleteFileFromFolder(File fileToBeDeleted) {
        try {
            if (!(fileToBeDeleted.isDirectory())) {
                keywords.waitTime(3000);
                fileToBeDeleted.delete();
                keywords.APP_LOGS.info("\"" + getFileName(fileToBeDeleted) + "\" has been deleted from location:" + getFolderNameOfFile(fileToBeDeleted));
            }
        } catch (Exception e) {
            Assert.fail("File can not be deleted");
            APP_LOGS.error(e.getMessage());
        }
    }

    /*******************************************
     * defListFolderFiles(File strNewDownloadLocation)
     *
     * #Description : This is common function to list all file in the location specified
     *
     * #Input Parameters: folderToclean: folder/pathName in which all files will be listed
     ********************************************/
    public void listFolderFiles(File strNewDownloadLocation) {
        try {
            // File folder = new File(strNewDownloadLocation);
            File[] listOfFiles = strNewDownloadLocation.listFiles();
            keywords.APP_LOGS.info("Listing all files in the directory" + strNewDownloadLocation);
            keywords.APP_LOGS.info("Number of files present in download directory are:: " + listOfFiles.length);
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    gDownloadedFile = file;
                    keywords.APP_LOGS.info("Downloaded files are: " + file.getName());
                }

            }
        } catch (Exception e) {
            keywords.APP_LOGS.info("File not present in the directory");
            APP_LOGS.error(e.getMessage());
        }

    }

    /*********************************************
     * #Description : This is common function to validate company name, dealer , importers etc in the documents #Input Parameters: expectedPDFData: Expected
     * Data to be compared against PDF data
     ********************************************/
    public void validatePDFdata(HashMap<String, Object> expectedPDFData, File DownloadedFile, String docuememntType) {

        String strPDFDocFilename = null;
        try {
            if (DownloadedFile == null) {
                keywords.APP_LOGS.info("Invalid file name, makesure file downloaded successfull");
            }

            strPDFDocFilename = getFileName(DownloadedFile).toString();
            strPDFDocFilename = strPDFDocFilename + ".pdf";
            if (strPDFDocFilename.equals(appBasedUtils.getStringData(expectedPDFData, "DownloadedFileName"))) {
                keywords.APP_LOGS.info("Downloaded document is verified, Document name is::  " + strPDFDocFilename);
                keywords.APP_LOGS.info("\"" + appBasedUtils.getStringData(expectedPDFData, "DownloadedFileName")
                        + "\" is expected pdf document file name from  excel sheet");
            } else
                keywords.APP_LOGS.info("Invalid PDF Document!!!!!");

            /*
             * if(gstrPDFFileData.contains((String)expectedPDFData.get("Company"))) { keywords.APP_LOGS.info("Company name \""+expectedPDFData.get("Company")+
             * "\" is found in PDF document downloaded"); } else keywords.APP_LOGS.info("Company name \""+expectedPDFData.get("Company")+
             * "\" is not found in PDF document downloaded");
             */
            /*
             * if(gstrPDFFileData.contains((String)expectedPDFData.get("DocumentType"))) { keywords.APP_LOGS.info("Document type \""
             * +expectedPDFData.get("DocumentType")+"\"  is found in PDF document downloaded"); } else keywords.APP_LOGS.info("Document type \""
             * +expectedPDFData.get("DocumentType")+ "\" is not found in PDF document downloaded");
             */

            if (docuememntType.equalsIgnoreCase("Outstanding Claim Summary-Company")) {
                keywords.APP_LOGS.info("Verifying \"Company name\", \"Dealer name\" and \"Document type\" in the document.");
                keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "Company"), gstrPDFFileData);
                keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "DocumentType"), gstrPDFFileData);
            }

            if (docuememntType.equalsIgnoreCase("Outstanding Claim Summary-Dealer")) {
                keywords.APP_LOGS.info("Verifying \"Company name\", \"Dealer name\" and \"Document type\" in the document.");
                keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "Company"), gstrPDFFileData);

                keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "ExpectedDealerValue"), gstrPDFFileData);
                keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "DocumentType"), gstrPDFFileData);
            }

            if (docuememntType.equalsIgnoreCase("Warranty Claim Acceptance")) {
                keywords.APP_LOGS.info("Verifying \"Document type\", and \"Document number\".");
                keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "DocumentType"), gstrPDFFileData);
                keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "CreditDocumentNo"), gstrPDFFileData);
            }

        } catch (Exception e) {
            keywords.APP_LOGS.info("Exception caught while validating the PDF document");
            APP_LOGS.error(e.getMessage());
        }
    }

    /*********************************************
     * #Description : This is common function to validate company name, dealer , importers etc in the documents #Input Parameters: folderToclean:
     * folder/pathName in which all files will be listed expectedPDFData: Expected Data to be compared against PDF data
     ********************************************/
    public void validateMaterialInstructionPDFdata(HashMap<String, Object> expectedPDFData, File DownloadedFile, String docuememntType,
            SoftAssert testCaseAssertion) {

        String strPDFDocFilename = null;
        try {
            if (DownloadedFile == null) {
                keywords.APP_LOGS.info("Invalid file name, makesure file downloaded successfull");
            }

            strPDFDocFilename = getFileName(DownloadedFile).toString();
            strPDFDocFilename = strPDFDocFilename + ".pdf";
            if (strPDFDocFilename.equals(appBasedUtils.getStringData(expectedPDFData, "DownloadedFileName"))) {
                keywords.APP_LOGS.info("Downloaded document is verified, Document name is::  " + strPDFDocFilename);
                keywords.APP_LOGS.info("\"" + appBasedUtils.getStringData(expectedPDFData, "DownloadedFileName")
                        + "\" is expected pdf document file name from  excel sheet");
            } else
                keywords.APP_LOGS.info("Invalid PDF Document!!!!!");
            keywords.APP_LOGS.info("Verifying \"MI Type\", \"Reference Number\" and \"Dealer\" in the document.");
            testCaseAssertion.assertTrue(keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "ReferenceNumber"), gstrPDFFileData));
            testCaseAssertion.assertTrue(keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "DealerNumber"), gstrPDFFileData));
            if (appBasedUtils.getStringData(expectedPDFData, "DownloadedFileName").equals("shippingCarrierTracking.pdf")) {
                if (!appBasedUtils.getStringData(expectedPDFData, "CarrierType").equals("NULL")) {
                    testCaseAssertion.assertTrue(keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "CarrierType"), gstrPDFFileData));
                }
            }

        } catch (Exception e) {
            keywords.APP_LOGS.info("Exception caught while validating the PDF document");
            APP_LOGS.error(e.getMessage());
        }
    }

    private boolean isFileDownloaded_Ext(File strFileName, String ext,HashMap<String, Object> data){
        String sPathSep = System.getProperty("file.separator");
        boolean flag=false;
        int count=1;
        File dir = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation");
        File[] files = dir.listFiles();
        APP_LOGS.info(files.length+ " is the total no. of files");
        if (files == null || files.length == 0) {
            flag = false;
        }
       // APP_LOGS.info(data.get("SizeOfFolderWithFilesCount")+ " Values");
      //  APP_LOGS.info(appBasedUtils.getStringData(data, "SizeOfFolderWithFilesCount") + " Size of files after download");
        long noOffiles = files.length;

                //(long) data.get("SizeOfFolderWithFilesCount");
        APP_LOGS.info(noOffiles + " in specified folder before download");
        do {
            for (int i = 0; i < files.length; i++) {
                keywords.waitTime1(10);
                APP_LOGS.info(files[i].getName());
                if (files[i].getName().contains(ext)) {
                    if ((files[i].getName()).equalsIgnoreCase(appBasedUtils.getStringData(data, "DownloadedFileName"))) {
                        APP_LOGS.info(files[i].getName() + "file exist in folder");
                        flag = true;
                        count++;
                            if(count==2){
                                break;
                            }
                        }
                    }
                }

        } while (noOffiles == noOffiles+1);
        return flag;
    }

}
