package com.jigsaw.impl;

import java.util.ArrayList;
import java.util.List;

public class MessageAccumulator {

    //private class members
    private String massage;
    List<String> massages = new ArrayList<>();

    //Public methods:
    public List<String> addMassage (String strToAdd){
        massages.add(strToAdd);
        return massages;
    }
}