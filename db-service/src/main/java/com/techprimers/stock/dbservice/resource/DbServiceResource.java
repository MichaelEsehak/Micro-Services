/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techprimers.stock.dbservice.resource;

import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuotesRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Michael
 */
@RestController
@RequestMapping("/rest/db")

public class DbServiceResource {

    private QuotesRepository quotesRepository;

    @Autowired
    public DbServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQueotes(@PathVariable final String username) {

        return getQuotesByUserName(username);

    }

    @PostMapping("/add")
    public List<String> add(@RequestBody Quotes quotes) {
        quotes.getQuotes().stream()
                .map(x -> new Quote(quotes.getUserName(), x))
                .forEach(x -> quotesRepository.save(x));

        return getQuotesByUserName(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    private List<String> delete(@PathVariable String username) {
        quotesRepository.deleteByUserName(username);

        return new ArrayList<>();
    }

    private List<String> getQuotesByUserName(String username) {
        return quotesRepository.findByUserName(username).stream()
                .map(Quote::getQuote).collect(Collectors.toList());
    }
}
