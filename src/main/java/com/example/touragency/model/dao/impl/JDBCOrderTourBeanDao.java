package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.OrderTourBeanDao;
import com.example.touragency.model.dao.beans.OrderTourBean;
import com.example.touragency.model.dao.mapper.bean.ordertour.enums.TourBeanMapper;
import com.example.touragency.model.dao.mapper.entity.OrderMapper;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touragency.constants.SqlConstants.*;

public class JDBCOrderTourBeanDao implements OrderTourBeanDao {

    private final Connection connection;

    public JDBCOrderTourBeanDao(Connection connection) {
        this.connection = connection;
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public OrderTourBean findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_TOUR_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Order order = new OrderMapper().extractFromResultSet(rs);
                Tour tour = new TourBeanMapper().extractFromResultSet(rs);
                return OrderTourBean.createOrderTourBean(order, tour);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find tour by id");
        }
        return null;
    }

    @Override
    public List<OrderTourBean> findAll() throws DaoException {
        List<OrderTourBean> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_ORDER_TOUR);
            while (rs.next()) {
                list.add(OrderTourBean.createOrderTourBean(
                        new OrderMapper().extractFromResultSet(rs),
                        new TourBeanMapper().extractFromResultSet(rs)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public void create(OrderTourBean entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(OrderTourBean entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws SQLException {

    }
}
