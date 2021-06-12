package com.example.touragency.model.dao.mapper.entity;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.entity.enums.TourCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourCategoryMapper implements EntityMapper<TourCategory> {


    @Override
    public TourCategory extractFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt(Fields.TOUR_CATEGORY_ID);

        if (id == TourCategory.EXCURSION.getId()) {
            return TourCategory.EXCURSION;
        }
        if (id == TourCategory.SHOPPING.getId()) {
            return TourCategory.SHOPPING;
        }
        if (id == TourCategory.REST.getId()) {
            return TourCategory.REST;
        }

        return null;
    }
}
