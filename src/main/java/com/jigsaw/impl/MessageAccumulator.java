package com.jigsaw.impl;

import java.util.ArrayList;
import java.util.List;

public class MessageAccumulator {

    //private class members
    private String massage;
    List<String> massages = new ArrayList<>();

    //Public methods:
    public void addMassage (String strToAdd){
        massages.add(strToAdd);
    }

    public boolean areThereAMassagesToRead(){
        return massage.length()==0;
    }

    public List<String> getMassages(){
        return massages;
    }
}
