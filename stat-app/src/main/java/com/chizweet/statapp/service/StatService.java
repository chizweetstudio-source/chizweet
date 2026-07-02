package com.chizweet.statapp.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatService {

    public Double calculateMean(List<Double> data) {
        if (data == null || data.isEmpty()) return 0.0;
        double sum = data.stream().mapToDouble(Double::doubleValue).sum();
        return sum / data.size();
    }

    public Double calculateMedian(List<Double> data) {
        if (data == null || data.isEmpty()) return 0.0;
        List<Double> sorted = new ArrayList<>(data);
        Collections.sort(sorted);
        int size = sorted.size();
        if (size % 2 == 0) {
            return (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
        } else {
            return sorted.get(size / 2);
        }
    }

    public List<Double> calculateMode(List<Double> data) {
        if (data == null || data.isEmpty()) return Collections.emptyList();
        
        Map<Double, Integer> counts = new HashMap<>();
        for (Double num : data) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        
        int maxCount = Collections.max(counts.values());
        
        return counts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Double calculateStdDeviation(List<Double> data) {
        if (data == null || data.size() < 2) return 0.0;
        double mean = calculateMean(data);
        double variance = data.stream()
                .mapToDouble(num -> Math.pow(num - mean, 2))
                .sum() / (data.size() - 1); // Sample standard deviation
        return Math.sqrt(variance);
    }
    
    public Double getMin(List<Double> data) {
        if (data == null || data.isEmpty()) return 0.0;
        return Collections.min(data);
    }
    
    public Double getMax(List<Double> data) {
        if (data == null || data.isEmpty()) return 0.0;
        return Collections.max(data);
    }
}
