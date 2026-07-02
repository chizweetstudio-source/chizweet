package com.chizweet.statapp.controller;

import com.chizweet.statapp.dto.StatRequest;
import com.chizweet.statapp.dto.StatResponse;
import com.chizweet.statapp.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StatController {

    private final StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @PostMapping("/calculate")
    public StatResponse calculate(@RequestBody StatRequest request) {
        StatResponse response = new StatResponse();
        
        if (request == null || request.getData() == null || request.getData().isEmpty()) {
            return response; // Return empty response or could throw error
        }
        
        response.setMean(statService.calculateMean(request.getData()));
        response.setMedian(statService.calculateMedian(request.getData()));
        response.setMode(statService.calculateMode(request.getData()));
        response.setStdDeviation(statService.calculateStdDeviation(request.getData()));
        response.setMin(statService.getMin(request.getData()));
        response.setMax(statService.getMax(request.getData()));
        
        return response;
    }
}
