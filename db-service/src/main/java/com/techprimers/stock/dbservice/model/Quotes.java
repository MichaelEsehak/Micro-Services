/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techprimers.stock.dbservice.model;

import java.util.List;

/**
 *
 * @author Michael
 */
public class Quotes {

    private String userName;
    private List<String> quotes;

    public Quotes(String userName, List<String> quotes) {
        this.userName = userName;
        this.quotes = quotes;
    }

    public Quotes() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }

}
