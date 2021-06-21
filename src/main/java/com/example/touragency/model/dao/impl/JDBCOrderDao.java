package com.example.touragency.model.dao.impl;

import com.example.touragency.model.Tools;
import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.dao.mapper.entity.OrderMapper;
import com.example.touragency.model.entity.Order;

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
    public int getCount() {
        return 0;
    }

    @Override
    public int create(Order order) {
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
        }
        return -1;
    }


    @Override
    public Order findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new OrderMapper().extractFromResultSet(rs);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_ORDERS);
            while (rs.next()) {
                list.add(new OrderMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order> findByLimit(int start, int count) {
        return null;
    }

    @Override
    public void update(Order order) {
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
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

}
