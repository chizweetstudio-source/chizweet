package com.chizweet.statapp.dto;

import java.util.List;

public class StatRequest {
    private List<Double> data;

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
