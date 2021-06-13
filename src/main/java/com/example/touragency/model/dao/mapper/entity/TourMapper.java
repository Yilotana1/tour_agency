package com.example.touragency.model.dao.mapper.entity;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.Tools;
import com.example.touragency.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMapper implements EntityMapper<Tour> {


    @Override
    public Tour extractFromResultSet(ResultSet rs) throws SQLException {

        return Tour.createTour(rs.getInt(Fields.ID), rs.getString(Fields.TOUR_NAME),
                rs.getString(Fields.TOUR_COUNTRY), rs.getBigDecimal(Fields.TOUR_PRICE),
                rs.getInt(Fields.TOUR_MAX_TICKETS), rs.getInt(Fields.TOUR_MIN_TICKETS),
                rs.getInt(Fields.TOUR_TAKEN_TICKETS), Tools.getCalendarFromDate(rs.getDate(Fields.TOUR_START_DATE)),
                Tools.getCalendarFromDate(rs.getDate(Fields.TOUR_END_DATE)),
                new TourCategoryMapper().extractFromResultSet(rs), new TourStatusMapper().extractFromResultSet(rs),
                new HotelMapper().extractFromResultSet(rs), rs.getString(Fields.TOUR_CITY));

    }
}