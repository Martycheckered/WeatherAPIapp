package com.myprojects;

import java.util.Arrays;
import java.util.List;

public class LongNameSplitter {
    private static LongNameSplitter instance;

    private LongNameSplitter() {
        //System.out.println("Singleton created!");
    }

    public static synchronized LongNameSplitter getInstance() {
        if (instance == null) {
            instance = new LongNameSplitter();
        }
        return instance;
    }

     StringBuilder divideBySingleWord (String cityname) {
        List<String> longCityNameList = Arrays.asList(cityname.split("\\s"));
        StringBuilder tempCityName =null;
        for (int i = 0; i <longCityNameList.size() ; i++) {
            if(tempCityName == null)
                tempCityName = new StringBuilder(longCityNameList.get(i) + "+");
            else
            { if (i==longCityNameList.size() -1) tempCityName.append(longCityNameList.get(i));
            else tempCityName.append(longCityNameList.get(i)).append("+");
            }
        }
        return tempCityName;
    }
}
