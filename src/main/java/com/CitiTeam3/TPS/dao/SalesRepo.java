package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SalesRepo extends JpaRepository<Sales,String> {
    Sales getBySalesName(String name);
}
