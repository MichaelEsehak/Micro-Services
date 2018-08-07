/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techprimers.stock.dbservice.repository;

import com.techprimers.stock.dbservice.model.Quote;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Michael
 */
public interface QuotesRepository extends JpaRepository<Quote, Integer> {

    public List<Quote> findByUserName(String username);

    @Transactional
    public void deleteByUserName(String username);

}
