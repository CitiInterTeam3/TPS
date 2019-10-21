package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.Product;
import com.CitiTeam3.TPS.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    void addProduct(){

    }

    public Product getProductId(String id){
        String sql="select * from product where cusipId=?";
        Object[] args=new Object[1];
        args[0]=id;
        List<Product> query = jdbcTemplate.query(sql, args, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product=new Product();
                product.setCusipId(rs.getString("cusipId"));
                product.setTotal(rs.getString("total"));
                product.setCoupon(rs.getString("coupon"));
                product.setIssueDate(rs.getString("IssueDate"));
                product.setMaterialDate(rs.getString("materialdate"));
                return product;
            }
        });
        return  query.size()==0?null:query.get(0);
    }

}
