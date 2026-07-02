package com.chizweet.statapp.dto;

import java.util.List;

public class StatResponse {
    private Double mean;
    private Double median;
    private List<Double> mode;
    private Double stdDeviation;
    private Double min;
    private Double max;

    public Double getMean() { return mean; }
    public void setMean(Double mean) { this.mean = mean; }

    public Double getMedian() { return median; }
    public void setMedian(Double median) { this.median = median; }

    public List<Double> getMode() { return mode; }
    public void setMode(List<Double> mode) { this.mode = mode; }

    public Double getStdDeviation() { return stdDeviation; }
    public void setStdDeviation(Double stdDeviation) { this.stdDeviation = stdDeviation; }

    public Double getMin() { return min; }
    public void setMin(Double min) { this.min = min; }

    public Double getMax() { return max; }
    public void setMax(Double max) { this.max = max; }
}
