package com.realtimeStockPortfolio.portfolioService.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Stock {

    // Fields from the /stock/profile2 endpoint
    private String companyName;

    private double marketCap;

    private String exchangeName;

    private String ticker;

    // Fields from the /quote endpoint
    private double currentPrice;

    private double openPrice;

    private double highPrice;

    private double lowPrice;
}
