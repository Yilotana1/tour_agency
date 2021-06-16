package com.example.touragency.model.dao.impl;

import com.example.touragency.model.Tools;
import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.dao.mapper.entity.OrderMapper;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touragency.constants.SqlConstants.*;

public class JDBCOrderDao implements OrderDao {

    private final Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public int create(Order order) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, new Date(order.getDate().getTimeInMillis()));
            statement.setInt(2, order.getStatus().getId());
            statement.setInt(3, order.getClient().getId());
            statement.setBigDecimal(4, order.getPrice());
            statement.setInt(5, order.getTourNumber());
            statement.executeUpdate();
            return Tools.getGeneratedId(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot create this order");
        }

    }


    @Override
    public Order findById(int id) throws DaoException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new OrderMapper().extractFromResultSet(rs);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find order by this id");
        }
        return null;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_ORDERS);
            while (rs.next()) {
                list.add(new OrderMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all orders");
        }
        return list;
    }

    @Override
    public void update(Order order) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)
        ) {
            statement.setDate(1, new Date(order.getDate().getTimeInMillis()));
            statement.setInt(2, order.getStatus().getId());
            statement.setBigDecimal(3, order.getPrice());
            statement.setInt(4, order.getTourNumber());
            statement.setInt(5, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot update this order");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot delete order by this id");
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

}
