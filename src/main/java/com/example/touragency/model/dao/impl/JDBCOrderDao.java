package com.example.touragency.model.dao.impl;

import com.example.touragency.Tools;
import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.dao.mapper.OrderMapper;
import com.example.touragency.model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.touragency.constants.db.sql.Order.*;

public class JDBCOrderDao implements OrderDao {

    private final Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public int getCount() throws SQLException{
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ORDERS_NUMBER_AS_COUNT);
            rs.next();
            return rs.getInt("count");

        }
    }

    @Override
    public int create(Order order) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, new Date(order.getDate().getTimeInMillis()));
            statement.setInt(2, order.getStatus().getId());
            statement.setInt(3, order.getClient().getId());
            statement.setBigDecimal(4, order.getPrice());
            statement.setInt(5, order.getTourId());

            statement.executeUpdate();
            return Tools.getGeneratedId(statement);
        }
    }


    @Override
    public Optional<Order> findById(int id) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(new OrderMapper().extractFromResultSet(rs));
            }


        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws SQLException{
        List<Order> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_ORDERS);
            while (rs.next()) {
                list.add(new OrderMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public List<Order> findByLimit(int start, int count) throws SQLException{
        List<Order> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_LIMIT);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new OrderMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public void update(Order order) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)
        ) {
            statement.setDate(1, new Date(order.getDate().getTimeInMillis()));
            statement.setInt(2, order.getStatus().getId());
            statement.setBigDecimal(3, order.getPrice());
            statement.setInt(4, order.getTourId());
            statement.setInt(5, order.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }


    @Override
    public List<Order> findOrdersByLogin(String login) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_LOGIN)
        ) {
            List<Order> orders = new ArrayList<>();
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orders.add(new OrderMapper().extractFromResultSet(rs));
            }

            return orders;
        }
    }

    @Override
    public List<Order> findOrdersByLimitOpenedFirst(int start, int count) throws SQLException{
        List<Order> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_LIMIT_OPENED_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new OrderMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public List<Order> findOrdersByLimitPaidFirst(int start, int count) throws SQLException{
        List<Order> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_LIMIT_PAID_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new OrderMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }
}
