package com.example.touragency.model.dao.mapper.bean.tourhotel;

import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.dao.mapper.entity.TourMapper;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourHotelBeanMapper implements EntityMapper<TourHotelBean> {


    @Override
    public TourHotelBean extractFromResultSet(ResultSet rs) throws SQLException {
        Tour tour = new TourMapper().extractFromResultSet(rs);
        Hotel hotel = new HotelBeanMapper().extractFromResultSet(rs);
        return TourHotelBean.createTourHotelBean(tour, hotel);
    }
}
