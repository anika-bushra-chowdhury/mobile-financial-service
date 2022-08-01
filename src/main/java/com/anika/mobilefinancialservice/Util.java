package com.anika.mobilefinancialservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Util {
    public static String convertDateToString(Date date) {
        String pattern = "dd/mm/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date convertStringToDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (Exception e) {
            log.error("Error while parsing Date of Birth :", e);
        }
        return null;
    }

    public static String encode(String data) {
        Base64 base64 = new Base64();
        String encodedString = new String(base64.encode(data.getBytes()));
        return encodedString;
    }

    public static String decode(String data) {
        Base64 base64 = new Base64();
        String decodedString = new String(base64.decode(data.getBytes()));
        return decodedString;
    }
}
