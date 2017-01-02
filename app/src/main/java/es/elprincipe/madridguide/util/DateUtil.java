package es.elprincipe.madridguide.util;


import java.util.Date;

public class DateUtil {

    public static long getDateDifToNow(Date date) {
        return ((new Date()).getTime() - date.getTime())/(1000 * 60 * 60 * 24);
    }

}
