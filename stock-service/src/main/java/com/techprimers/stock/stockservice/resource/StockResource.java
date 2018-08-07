/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techprimers.stock.stockservice.resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.YahooFinance;

/**
 *
 * @author Michael
 */
@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{username}")
    public List<Quote> getStock(@PathVariable final String username) {
        ResponseEntity<List<String>> quotesResponse = restTemplate.exchange("http://db-service/rest/db/" + username, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        });

        return quotesResponse.getBody().stream()
                .map(quote -> {
                    try {
                        return new Quote(quote, YahooFinance.get(quote).getQuote().getPrice());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return new Quote(quote, BigDecimal.ZERO);
                    }
                })
                .collect(Collectors.toList());
    }

    private class Quote {

        private String quote;
        private BigDecimal price;

        public Quote(String quote, BigDecimal price) {
            this.quote = quote;
            this.price = price;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

    }

}
