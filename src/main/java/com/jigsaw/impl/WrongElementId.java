package com.jigsaw.impl;

public class WrongElementId extends IllegalAccessException{
    public WrongElementId() {

    }

    public WrongElementId(String s) {
        super(s);
    }
}
