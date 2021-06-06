package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.OrderClientBeanDao;
import com.example.touragency.model.dao.beans.OrderClientBean;
import com.example.touragency.model.dao.mapper.bean.order.client.bean.BeanUserMapper;
import com.example.touragency.model.dao.mapper.entity.OrderMapper;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touragency.constants.SqlConstants.*;

public class JDBCOrderClientBeanDao implements OrderClientBeanDao {

    private final Connection connection;

    public JDBCOrderClientBeanDao(Connection connection) {
        this.connection = connection;
    }



    @Override
    public OrderClientBean findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_CLIENT_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Order order = new OrderMapper().extractFromResultSet(rs);
                User client = new BeanUserMapper().extractFromResultSet(rs);
                return OrderClientBean.createOrderClientBean(order, client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find tour by id");
        }
        return null;
    }

    @Override
    public List<OrderClientBean> findAll() throws DaoException {
        List<OrderClientBean> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_ORDER_CLIENT);
            while (rs.next()) {
                list.add(OrderClientBean.createOrderClientBean(
                        new OrderMapper().extractFromResultSet(rs),
                        new BeanUserMapper().extractFromResultSet(rs)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public void update(OrderClientBean entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(OrderClientBean entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
