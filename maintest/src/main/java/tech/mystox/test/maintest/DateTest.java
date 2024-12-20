package tech.mystox.test.maintest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mystox on 2023/5/15, 18:11.
 * company:
 * description:
 * update record:
 */
public class DateTest {



    public static void main(String[] args) {
    SimpleDateFormat sdfT = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdfZ = new SimpleDateFormat("HHmmss");
    String startTimeS = sdfT.format(new Date())+"t"+sdfZ.format(new Date());
        System.out.println(startTimeS);
    }
}
