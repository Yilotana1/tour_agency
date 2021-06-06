package com.example.touragency.model.dao.mapper;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.entity.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelMapper implements EntityMapper<Hotel> {
    @Override
    public Hotel extractFromResultSet(ResultSet rs) throws SQLException {



        return Hotel.createHotel(rs.getInt(Fields.ID), rs.getString(Fields.HOTEL_NAME),
                rs.getString(Fields.HOTEL_CITY), rs.getString(Fields.HOTEL_ADDRESS), rs.getInt(Fields.HOTEL_TYPE),
                rs.getString(Fields.HOTEL_ADMIN_PHONE));
    }
}
