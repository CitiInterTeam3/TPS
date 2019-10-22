package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.SalesRepo;
import com.CitiTeam3.TPS.domain.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {
    @Autowired
    SalesRepo salesRepo;

    public Sales save(Sales sales){
        return salesRepo.save(sales);
    }
}
