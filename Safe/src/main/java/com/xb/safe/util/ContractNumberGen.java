package com.xb.safe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContractNumberGen {
    public static String generateContractNumber(Date date) {
        String number = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("hmmss/MMyy");
        number = dateFormat.format(date);
        return number;
    }
}
