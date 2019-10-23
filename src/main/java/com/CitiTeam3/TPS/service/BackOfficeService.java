package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.domain.Sales;
import com.CitiTeam3.TPS.domain.SalesRequest;
import com.CitiTeam3.TPS.domain.Trader;
import com.CitiTeam3.TPS.domain.TraderRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BackOfficeService {
    HashMap<Integer,String>hs=new HashMap<>();
    public BackOfficeService(){
        hs.put(1,"Price too high!");
        hs.put(3,"Excessive Quantity!");
    }
    public String handleTwoLeg(TraderRequest trader, SalesRequest sales){
        if (isAmountHigh(trader.getAmount())){
            return "Excessive Quantity!";
        }
        if (isPriceHigh(trader.getPrice())){
            return "Price too high!";
        }
        return "Accept !";
    }
    public Boolean isPriceHigh(double i){
        if (i>110){
            return true;
        }
        return false;
    }
    public Boolean isAmountHigh(int i){
        if (i>1000){
            return true;
        }
        return false;
    }
}
