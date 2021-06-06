package com.example.touragency.model.dao.mapper.bean.tour.hotel.bean;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.entity.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanHotelMapper implements EntityMapper<Hotel> {
    @Override
    public Hotel extractFromResultSet(ResultSet rs) throws SQLException {
        return Hotel.createHotel(rs.getInt(Fields.BEAN_HOTEL_ID), rs.getString(Fields.BEAN_HOTEL_NAME),
                rs.getString(Fields.BEAN_HOTEL_CITY), rs.getString(Fields.BEAN_HOTEL_ADDRESS),
                rs.getInt(Fields.BEAN_HOTEL_STARS), rs.getString(Fields.BEAN_HOTEL_ADMIN_PHONE));
    }
}
