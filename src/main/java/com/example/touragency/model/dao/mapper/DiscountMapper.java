package com.example.touragency.model.dao.mapper;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.entity.Discount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountMapper implements EntityMapper<Discount> {

    @Override
    public Discount extractFromResultSet(ResultSet rs) throws SQLException {
        return Discount.createDiscount(rs.getInt(Fields.ID), rs.getInt(Fields.DISCOUNT_PERCENT),
                rs.getInt(Fields.DISCOUNT_MAX_PERCENT));
    }
}
