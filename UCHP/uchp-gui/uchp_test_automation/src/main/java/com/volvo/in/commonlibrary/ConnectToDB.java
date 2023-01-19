package com.volvo.in.commonlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectToDB {
    AppBasedUtils appBasedUtils  = new AppBasedUtils();
    UIUtils keywords = new UIUtils();
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.ibm.db2.jcc.DB2Driver";
    static final String DB_URL = "jdbc:db2://sd01.it.volvo.com:446/SEVOL0D2D20H00";

    // Database credentials
    public static String DB_USER_ID = null;
    public static String DB_PASSWORD = null;
    public static String DB_Name = null;
    Connection connection = null;
    Statement stmt = null;
    String sql = null;
    Properties prop = null;

    private static final String VOLVO_TRUCK_CORPORATION = "Volvo Truck Corporation";
    private static final String VOLVO_BUS_CORPORATION = "Volvo Bus Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction Equipment";
    private static final String TSM_AMERICAS = "TSM Americas";
    private static final String UD_TRUCKS_CORPORATION = "UD Truck Corporation";

    public void setConnectionToDB()  throws ClassNotFoundException, SQLException {
            prop =   appBasedUtils.getConfigProperties("config");
        if (BaseClass.getEnvironmentName().equalsIgnoreCase("test1")) {
            prop.getProperty("test1_UserID");
            DB_USER_ID = prop.getProperty("test1_UserID");
            DB_PASSWORD = prop.getProperty("test1_Pwd");
            DB_Name = prop.getProperty("test1_DB_Name");
        } else if (BaseClass.getEnvironmentName().equalsIgnoreCase("test2")) {
            prop.getProperty("test1_UserID");
            DB_USER_ID = prop.getProperty("test2_UserID");
            DB_PASSWORD = prop.getProperty("test2_Pwd");
            DB_Name = prop.getProperty("test2_DB_Name");
        } 
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, DB_USER_ID, DB_PASSWORD);
        stmt = connection.createStatement();
      
    }

    public void updateQueryBuilderToSetPreferredCompany(String companyName, String userId) {
        try {
            String companyNo = covertCompanyNameToNo(companyName);
            setConnectionToDB();
            String query = "UPDATE " + DB_Name + ".USERPREFERREDCOMPANY" + " set COMPANYNO = " + companyNo + " where USERID='" + userId + "'";
            stmt.executeUpdate(query);
            
            sql = "Select * from " + DB_Name + ".USERPREFERREDCOMPANY where" + " USERID = '" + userId + "'";
            ResultSet rset = stmt.executeQuery(sql);
          
            while (rset.next()) {
                // Retrieve by column name
                int companyNoFromDB = rset.getInt("COMPANYNO");
                String userIdFromDB = rset.getString("USERID");
                // Display values
                keywords.APP_LOGS.info("Prefered company is set for CompanyNo : " + companyNoFromDB + ", userId: " + userIdFromDB );
            }
            rset.close();
            stmt.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public String covertCompanyNameToNo(String companyName) {
        if (companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION)) {
            return "1";
        }
        if (companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION)) {
            return "2";
        }
        if (companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
            return "3";
        }
        if (companyName.equalsIgnoreCase(TSM_AMERICAS)) {
            return "4";
        }
        if (companyName.equalsIgnoreCase(UD_TRUCKS_CORPORATION)) {
            return "5";
        } else {
            return "6";
        }
    }
}
