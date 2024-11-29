package com.realtimeStockPortfolio.portfolioService.service;

import com.realtimeStockPortfolio.portfolioService.model.Portfolio;
import com.realtimeStockPortfolio.portfolioService.model.Stock;
import com.realtimeStockPortfolio.portfolioService.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${stock.service.url}")
    String stockServiceUrl;

    public List<Portfolio> getPortfoliosByUserId(Long userId) {
        return portfolioRepository.findByUserId(userId);
    }

    public double getPortfolioValue(Long userId) {
        List<Portfolio> portfolioList = getPortfoliosByUserId(userId);
        double totalPortfolioValue = 0.0;

        for (Portfolio portfolio : portfolioList) {
            double stockQuantity = portfolio.getQuantity();
            String stockSymbol = portfolio.getStockSymbol();

            String stockUrl = stockServiceUrl + "/api/stocks/" + stockSymbol;
            Stock stock = restTemplate.getForObject(stockUrl, Stock.class);

            if (stock != null) {
                double stockCurrentPricePrice = stock.getCurrentPrice();
                totalPortfolioValue += stockCurrentPricePrice * stockQuantity;
            }
        }

        return totalPortfolioValue;
    }

    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }
}
