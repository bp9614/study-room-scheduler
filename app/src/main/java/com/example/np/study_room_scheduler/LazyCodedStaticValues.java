package com.example.np.study_room_scheduler;

import java.util.HashMap;

/**
 * Created by phamb1 on 11/30/2017.
 */

public class LazyCodedStaticValues {

    public static final HashMap<String, String> monthToInt = new HashMap<>();

    public static void populateValues() {
        monthToInt.put("Jan", "01");
        monthToInt.put("Feb", "02");
        monthToInt.put("Mar", "03");
        monthToInt.put("Apr", "04");
        monthToInt.put("May", "05");
        monthToInt.put("Jun", "06");
        monthToInt.put("Jul", "07");
        monthToInt.put("Aug", "08");
        monthToInt.put("Sep", "09");
        monthToInt.put("Oct", "10");
        monthToInt.put("Nov", "11");
        monthToInt.put("Dec", "12");
    }

}
