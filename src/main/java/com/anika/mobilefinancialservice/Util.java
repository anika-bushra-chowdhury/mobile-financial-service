package com.anika.mobilefinancialservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Util {
    public static Base64 base64 = new Base64();


    public static String convertDateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date convertStringToDate(String dateString, String pattern) {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(dateString);
            log.info("Date is:", date);
        } catch (Exception e) {
            log.error("Error while parsing Date :", e);
        }
        return date;
    }

    public static Integer convertDateToDateInt(String date, String pattern) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return Integer.parseInt(df.format(date));
    }

    public static String encode(String data) {
        String encodedString = new String(base64.encode(data.getBytes()));
        return encodedString;
    }

    public static String decode(String data) {
        String decodedString = new String(base64.decode(data.getBytes()));
        return decodedString;
    }
}
