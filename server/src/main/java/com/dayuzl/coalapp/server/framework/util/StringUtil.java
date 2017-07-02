package com.dayuzl.coalapp.server.framework.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtil {

    //获得随机字符串
    public  static String generateRandomString(Date date) {

        String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer(gererateDateString(date,""));
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }

        return sb.toString();
    }

    private static String gererateDateString(Date date, String pattern){

        if(StringUtils.isEmpty(pattern)){
            pattern = "yyyyMMddHHmmmss";
        }
        if(date == null){
            date = new Date();
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(date);

        return dateString;
    }
}
