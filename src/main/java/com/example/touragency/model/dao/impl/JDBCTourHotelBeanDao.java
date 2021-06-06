package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.TourHotelBeanDao;
import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.dao.mapper.bean.tour.hotel.bean.BeanHotelMapper;
import com.example.touragency.model.dao.mapper.entity.TourMapper;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touragency.constants.SqlConstants.*;

public class JDBCTourHotelBeanDao implements TourHotelBeanDao {

    private final Connection connection;

    public JDBCTourHotelBeanDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public TourHotelBean findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_HOTEL_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Tour tour = new TourMapper().extractFromResultSet(rs);
                Hotel hotel = new BeanHotelMapper().extractFromResultSet(rs);
                return TourHotelBean.createTourHotelBean(tour, hotel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find tour by id");
        }
        return null;
    }

    @Override
    public List<TourHotelBean> findByCategory(TourCategory category) throws DaoException {
        List<TourHotelBean> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_HOTEL_BY_CATEGORY)) {
            statement.setInt(1, category.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(TourHotelBean.createTourHotelBean(
                        new TourMapper().extractFromResultSet(rs),
                        new BeanHotelMapper().extractFromResultSet(rs)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public TourHotelBean findByName(String name) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_HOTEL_BY_NAME);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Tour tour = new TourMapper().extractFromResultSet(rs);
                Hotel hotel = new BeanHotelMapper().extractFromResultSet(rs);
                return TourHotelBean.createTourHotelBean(tour, hotel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find tour by id");
        }
        return null;
    }


    @Override
    public List<TourHotelBean> findAll() throws DaoException {
        List<TourHotelBean> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_TOUR_HOTEL);
            while (rs.next()) {
                list.add(TourHotelBean.createTourHotelBean(
                        new TourMapper().extractFromResultSet(rs),
                        new BeanHotelMapper().extractFromResultSet(rs)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public void create(TourHotelBean entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(TourHotelBean entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }




    @Override
    public void close() throws SQLException {
        connection.close();
    }


}
