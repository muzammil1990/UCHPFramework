package com.volvo.in.commonlibrary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class DateUtility {

    private static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
    private static final String MM_DD_YYYY_HH_MM = "MM/dd/yyyy HH:mm";
    private static final String DATE_FORMAT = "dd/MM/yy";
    public static final String DATE_ONLY_FORMAT = "dd/MM/yyyy";
    private static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    /**
     * @param convertFormat
     *            date format
     * @return specified date format
     */
    public static Logger APP_LOGS = Logger.getLogger(DateUtility.class);
    private DateUtility() {
        super();
    }
    public static String getCurrentDate(String convertFormat) {
        Date date = new Date();
        if(convertFormat==null || convertFormat.isEmpty()){
            convertFormat=DATE_TIME_FORMAT;
        }
        DateFormat format1 = new SimpleDateFormat(convertFormat);
        return format1.format(date);
    }

    public static String getCurrentDate() {
        Date date = new Date();

        DateFormat format1 = new SimpleDateFormat(DATE_TIME_FORMAT);
        return format1.format(date);
    }


    /*********************************************
     * #Description : Checks whether two dates are equal or not
     *******************************************/
    public static boolean compareforEquality(Date firstDate, Date secondDate) {
        if (firstDate.compareTo(secondDate) == 0)
            return true;
        return false;
    }

    /*********************************************
     * #Description : Parse given date(String formate) to Date formate
     *********************************************/
    public static Date parseToDateFormat(String strDate) {
        Date toDate = null;
        SimpleDateFormat simpleDateformate = new SimpleDateFormat(DATE_ONLY_FORMAT);
        try {
            toDate = simpleDateformate.parse(strDate);
        } catch (ParseException e) {
            APP_LOGS.error(e.getMessage());
        }
        return toDate;
    }

    /*********************************************
     * #Description : Method to convert date from "dd/mm/yy" formate to "dd/MM/yyyy" format
     *******************************************/
    public static String convertDateBetweenFormat(String sourceDateFormat) {
        String destinationDateFormat = new String();
        try {
            DateFormat srcDf = new SimpleDateFormat(DATE_FORMAT);
            Date date = srcDf.parse(sourceDateFormat);
            DateFormat destDf = new SimpleDateFormat(DATE_ONLY_FORMAT);
            destinationDateFormat = destDf.format(date);
            return destinationDateFormat;
        }

        catch (ParseException e) {

            APP_LOGS.error(e.getMessage());
        }
        return destinationDateFormat;
    }

    /*********************************************
     * #Description : Returns dd/MM/yyyy 'n' previous date from current date.
     *******************************************/

    public static String getPreviousDateFromCurrentDate(int numberOfDays, String dateFromat) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -numberOfDays);
        SimpleDateFormat s = new SimpleDateFormat(dateFromat);
        return s.format(new Date(cal.getTimeInMillis()));

    }
    public static String getCETTimeFromCurrentIST(String dateToConvert){
        try{
        DateFormat formatter = new SimpleDateFormat(DD_MM_YYYY_HH_MM);
        Date dateFormat = formatter.parse(dateToConvert);
        TimeZone obj = TimeZone.getTimeZone("CET");
        formatter.setTimeZone(obj);
        return formatter.format(dateFormat.getTime());
        }
        catch(ParseException e){
            APP_LOGS.error(e.getMessage());
        }
        return null;

    }
    public static String getCurrentDateAndTime(){
        try{
        DateFormat formatter = new SimpleDateFormat(DD_MM_YYYY_HH_MM);
        Calendar currentdate = Calendar.getInstance();
        return formatter.format(currentdate.getTime());
        }
        catch(Exception e){
            APP_LOGS.error(e.getMessage());
        }
        return null;
    }
    /*********************************************
     * #Description : Returns current CET time either hour or minute or second
     *******************************************/
    public static String getCETTimeConversion(String value){
        DateFormat formatter = new SimpleDateFormat(value);
        Calendar currentdate = Calendar.getInstance();
        TimeZone obj = TimeZone.getTimeZone("CET");
        formatter.setTimeZone(obj);
        return formatter.format(currentdate.getTime());
    }


}
