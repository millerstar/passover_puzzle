package com.jigsaw.jsonImpl;

import com.google.gson.Gson;

public class JsonIO {


    public Puzzle readJson(String jsonFromClient){

        Gson gson = new Gson();
        Puzzle puzzleFromJson = gson.fromJson(jsonFromClient, Puzzle.class);
        System.out.println(puzzleFromJson);
        return puzzleFromJson;

    }
}
