package com.example.touragency.model.dao.Factory;

import com.example.touragency.ConnectionPoolHolder;
import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.impl.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JDBCUserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public UserDao createUserDao(Connection connection) {
         return new JDBCUserDao(connection);
    }

    @Override
    public JDBCTourDao createTourDao() {
        return new JDBCTourDao(getConnection());
    }

    @Override
    public TourDao createTourDao(Connection connection) {
        return new JDBCTourDao(connection);
    }

    @Override
    public JDBCOrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao(Connection connection) {
        return new JDBCOrderDao(connection);
    }

    @Override
    public JDBCHotelDao createHotelDao() {
        return new JDBCHotelDao(getConnection());
    }

    @Override
    public HotelDao createHotelDao(Connection connection) {
        return new JDBCHotelDao(connection);
    }

    @Override
    public JDBCDiscountDao createDiscountDao() {
        return new JDBCDiscountDao(getConnection());
    }

    @Override
    public DiscountDao createDiscountDao(Connection connection) {
        return new JDBCDiscountDao(connection);
    }
}
