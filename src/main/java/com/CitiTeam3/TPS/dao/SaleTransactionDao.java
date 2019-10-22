package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.SaleTransaction;
import com.CitiTeam3.TPS.domain.TraderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SaleTransactionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int addSaleTransaction(SaleTransaction saleTransaction){
        return 1;
    }
}
