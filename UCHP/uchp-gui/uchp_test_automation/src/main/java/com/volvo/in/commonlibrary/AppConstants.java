package com.volvo.in.commonlibrary;

public class AppConstants {
    public static final String USER_DIR = "user.dir";
    public static final String FILE_SEPARATOR = "file.separator";
    static String driverPath = ".\\resources\\Drivers\\";
    public static final String filePath = System.getProperty(USER_DIR) + System.getProperty(FILE_SEPARATOR) + "test-output" + System.getProperty(FILE_SEPARATOR)
            + "EntendReport" + System.getProperty(FILE_SEPARATOR);
    public static final String RESOURCES_TEST_DATA = ".\\resources\\TestData\\";
    public static final String RESOURCES_DOWNLOAD_LOCATION = "\\resources\\DownloadLocation";
    public static final String gstrDownloadLocation = System.getProperty(USER_DIR).replace("\\", "\\\\") + AppConstants.RESOURCES_DOWNLOAD_LOCATION;
}
