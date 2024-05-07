package com.maua.yegestaodesaude.shared.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_REVERT = new SimpleDateFormat("dd/MM/yyyy");

    public Date convertStringToDate(String dateString) throws ParseException {
        return DATE_FORMAT.parse(dateString);
    }

    public String RevertDate(Date date) {
        return DATE_FORMAT_REVERT.format(date);
    }

    public String RevertDateString(String date) {
        String regexDate = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";

        if(date.matches(regexDate)){
            return date.split("-")[2] + "/" + date.split("-")[1] + "/" + date.split("-")[0];   
        }
        return "Data inválida";
    }
}
