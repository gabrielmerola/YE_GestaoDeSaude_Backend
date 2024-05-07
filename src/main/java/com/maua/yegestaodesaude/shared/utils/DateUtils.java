package com.maua.yegestaodesaude.shared.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_REVERT = new SimpleDateFormat("dd/MM/yyyy");

    public static Date convertStringToDate(String dateString) throws ParseException {
        return DATE_FORMAT.parse(dateString);
    }

    public String RevertDate(Date date) {
        return DATE_FORMAT_REVERT.format(date);
    }
}
