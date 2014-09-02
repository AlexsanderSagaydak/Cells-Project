package com.xb.safe.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static String getFormattedString(Date date) {
        DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
            @Override
            public String[] getMonths() {
                return new String[]{"січня", "лютого", "березня", "квітня", "травня", "червня",
                    "липня", "серпня", "вересня", "жовтня", "листопада", "грудня"};
            }
        };
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", myDateFormatSymbols);
        return dateFormat.format(date);
    }
}
