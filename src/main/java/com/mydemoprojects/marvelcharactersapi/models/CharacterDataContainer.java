package com.mydemoprojects.marvelcharactersapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDataContainer {

    private int offset;

    private int limit;

    private int total;

    private int count;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private ArrayList<Character> results;

    public ArrayList<Character> getResults() {
        return results;
    }

    public void setResults(ArrayList<Character> results) {
        this.results = results;
    }

}
