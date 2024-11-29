package com.realtimeStockPortfolio.portfolioService.controller;

import com.realtimeStockPortfolio.portfolioService.model.Portfolio;
import com.realtimeStockPortfolio.portfolioService.service.PortfolioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public List<Portfolio> getPortfoliosByUserId(@PathVariable Long userId) {
        return portfolioService.getPortfoliosByUserId(userId);
    }

    @GetMapping("/{userId}/value")
    public ResponseEntity<Double> getPortfolioValue(@PathVariable Long userId) {
        double portfolioValue = portfolioService.getPortfolioValue(userId);

        return ResponseEntity.ok(portfolioValue);
    }

    @PostMapping
    ResponseEntity<Portfolio> createPortfolio(@RequestBody @Valid Portfolio portfolio) {
        Portfolio savedPortfolio = portfolioService.createPortfolio(portfolio);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPortfolio);
    }

}
