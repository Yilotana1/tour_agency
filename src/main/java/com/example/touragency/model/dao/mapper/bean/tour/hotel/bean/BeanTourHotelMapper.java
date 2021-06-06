package com.example.touragency.model.dao.mapper.bean.tour.hotel.bean;

import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.dao.mapper.EntityMapper;
import com.example.touragency.model.dao.mapper.entity.TourMapper;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanTourHotelMapper implements EntityMapper<TourHotelBean> {


    @Override
    public TourHotelBean extractFromResultSet(ResultSet rs) throws SQLException {
        Tour tour = new TourMapper().extractFromResultSet(rs);
        Hotel hotel = new BeanHotelMapper().extractFromResultSet(rs);
        return TourHotelBean.createTourHotelBean(tour, hotel);
    }
}
