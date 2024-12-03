package com.solanaexchange.project.controller;

import com.solanaexchange.project.service.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = {"https://solanaexchange.netlify.app/","https://localhost:5173/"})
public class StockData {
@Autowired
    StockDataService stockDataService;
    @GetMapping("/stock")
    public Map<String,Object> getStock(){

        return stockDataService.getStockData();
    }

}
