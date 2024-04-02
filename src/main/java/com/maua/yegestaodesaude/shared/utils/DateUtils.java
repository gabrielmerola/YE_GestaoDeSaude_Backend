package com.maua.yegestaodesaude.shared.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date convertStringToDate(String dateString) throws ParseException {
        return DATE_FORMAT.parse(dateString);
    }
}
