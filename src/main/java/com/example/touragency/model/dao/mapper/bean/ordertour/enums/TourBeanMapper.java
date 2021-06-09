package com.example.touragency.model.dao.mapper.bean.ordertour.enums;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.Tools;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.dao.mapper.enums.TourCategoryMapper;
import com.example.touragency.model.dao.mapper.enums.TourStatusMapper;
import com.example.touragency.model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourBeanMapper implements EntityMapper<Tour> {

    @Override
    public Tour extractFromResultSet(ResultSet rs) throws SQLException {
        return Tour.createTour(rs.getInt(Fields.BEAN_TOUR_ID), rs.getString(Fields.BEAN_TOUR_NAME),
                rs.getString(Fields.BEAN_TOUR_COUNTRY), rs.getBigDecimal(Fields.BEAN_TOUR_PRICE),
                rs.getInt(Fields.BEAN_TOUR_MAX_PLACES), rs.getInt(Fields.BEAN_TOUR_MIN_PLACES), rs.getInt(Fields.BEAN_TOUR_TAKEN_PLACES),
                Tools.getCalendarFromDate(rs.getDate(Fields.BEAN_TOUR_START_DATE)),
                Tools.getCalendarFromDate(rs.getDate(Fields.BEAN_TOUR_END_DATE)),
                new TourCategoryMapper().extractFromResultSet(rs), new TourStatusMapper().extractFromResultSet(rs),
                rs.getInt(Fields.BEAN_TOUR_HOTEL_ID), rs.getString(Fields.BEAN_TOUR_CITY));
    }
}
