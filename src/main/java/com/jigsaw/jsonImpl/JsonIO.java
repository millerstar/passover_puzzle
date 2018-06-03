package com.jigsaw.jsonImpl;

import com.google.gson.Gson;
import com.jigsaw.impl.PuzzleBox;

public class JsonIO {


    public PuzzleBox readJson(String jsonFromClient){

        Gson gson = new Gson();
        PuzzleBox puzzleFromJson = gson.fromJson(jsonFromClient, PuzzleBox.class);
        System.out.println(puzzleFromJson);
        return puzzleFromJson;

    }
}
