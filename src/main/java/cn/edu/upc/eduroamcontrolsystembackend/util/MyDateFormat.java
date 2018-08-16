package cn.edu.upc.eduroamcontrolsystembackend.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jay on 2018/08/02
 */

@Service
public class MyDateFormat {
    private SimpleDateFormat simpleDateFormat;

    public MyDateFormat() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public String formattedDate() {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
