package com.jigsaw.impl;


/**
 *  Author:  Matvey
 *  Date:   29/04/2018
 */
public class Config {

    private String inputFilePosition;
    private String outputFilePosition;
    private boolean rotate;
    private int threadsNumber;

    private static boolean isConfigExist;
    // default config
    private static Config config = new Config();

    public static Config getInstance(){
        if(!isConfigExist){
            // creaete default config
            config.inputFilePosition = "src\\main\\resources\\puzzlePiecesFile.txt";
            config.outputFilePosition = "src\\main\\resources\\puzzleResultFile.txt";
            config.rotate = true;
            config.threadsNumber = 4;
            isConfigExist = true;
            return config;
        }
        return config;
    }

    public static void setConfig(String inputFile, String outputFile, boolean rotate, int threadsNumber) {
        config.inputFilePosition = inputFile;
        config.outputFilePosition = outputFile;
        config.rotate = rotate;
        config.threadsNumber = threadsNumber;
        isConfigExist = true;
    }

    public String getInputFilePosition() {
        return inputFilePosition;
    }

    public String getOutputFilePosition() {
        return outputFilePosition;
    }

    public boolean isRotate() {
        return rotate;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }
}
