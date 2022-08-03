package com.anika.mobilefinancialservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
            log.debug("Date is: {}", date);
        } catch (Exception e) {
            log.error("Error while parsing Date :", e);
        }
        return date;
    }

    public static Integer convertDateToDateInt(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return Integer.parseInt(df.format(date));
    }

    public static String encode(String data) {
        return new String(base64.encode(data.getBytes()));
    }

    public static String decode(String data) {
        return new String(base64.decode(data.getBytes()));
    }

    public static String generateNrNUmber() {
        Random random = new Random();
        return "MFS" + (random.nextInt(9000000 - 1000000) + 1000000);
    }
}
