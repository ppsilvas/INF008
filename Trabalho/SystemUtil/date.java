package Trabalho.SystemUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class date {
    public static String handleDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
