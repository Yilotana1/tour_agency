package com.example.touragency.model.dao.Factory;

import com.example.touragency.model.dao.Connection.ConnectionPoolHolder;
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
    public JDBCTourDao createTourDao() {
        return new JDBCTourDao(getConnection());
    }

    @Override
    public JDBCOrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public JDBCHotelDao createHotelDao() { return new JDBCHotelDao(getConnection()); }

    @Override
    public JDBCDiscountDao createDiscountDao() { return new JDBCDiscountDao(getConnection()); }

}
