package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.TraderTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SaleController {
    @Autowired
    TraderTransactionDao traderTransactionDao;

}
