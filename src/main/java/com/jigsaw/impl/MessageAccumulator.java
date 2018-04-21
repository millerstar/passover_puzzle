package com.jigsaw.impl;

import java.util.ArrayList;
import java.util.List;

public class MessageAccumulator {

    //private class members
    public static List<String> massages = new ArrayList<>();

    //Public methods:
    public static void addMassage (String strToAdd){
        massages.add(strToAdd);
    }

    public static boolean areThereAMassagesToRead(){
        return massages.size() == 0;
    }

    public static List<String> getMassages(){
        return massages;
    }
}
