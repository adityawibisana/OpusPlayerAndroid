package aw.com.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Adit on 4/8/2017.
 */

public class Converters {
    public static String convertNumberToTimeDisplay (int number) {
        int second = number%60;
        int minute = (int) TimeUnit.SECONDS.toMinutes((long) number);
        return String.format("%02d",minute) + ":" + String.format("%02d", second) ;
    }
}
