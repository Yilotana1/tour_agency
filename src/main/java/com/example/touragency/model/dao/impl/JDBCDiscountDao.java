package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.DiscountDao;
import com.example.touragency.model.dao.mapper.DiscountMapper;
import com.example.touragency.model.entity.Discount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.touragency.constants.db.sql.Discount.*;


/**
 * Discount dao implementation. Presents methods for access to Discount table in database
 * In this project we have functionality only with one discount for all tours.
 * So the table will always save only one discount item.
 * @author Anatoliy Zhilko
 */
public class JDBCDiscountDao implements DiscountDao {


    private final Connection connection;

    public JDBCDiscountDao(Connection connection) {
        this.connection = connection;
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public int getCount() throws SQLException{
        return 1;
    }


    @Override
    public Optional<Discount> findById(int id) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_DISCOUNT_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new DiscountMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    @Override
    public List<Discount> findAll() throws SQLException{
        List<Discount> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_DISCOUNTS);
            while (rs.next()) {
                list.add(new DiscountMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    /**
     * Method throw UnsupportedOperationException, because the entity table always save one record, so it doesn't make sense
     * to implement it
     * @param start
     * @param count
     * @return
     */
    @Override
    public List<Discount> findByLimit(int start, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Discount discount) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DISCOUNT);
        ) {
            statement.setInt(1, discount.getPercent());
            statement.setInt(2, discount.getMaxPercent());
            statement.setInt(3, discount.getId());
            statement.executeUpdate();
        }
    }


    /**
     * Method throw UnsupportedOperationException, because the entity table must at least save one record,
     * so it doesn't make sense
     * to implement it
     *
     */
    @Override
    public void delete(int id) throws SQLException{
        throw new UnsupportedOperationException();
    }


    /**
     * Method throw UnsupportedOperationException, because the entity table must at least save one record,
     * so it doesn't make sense
     * to implement it
     *
     */
    @Override
    public int create(Discount discount) throws SQLException{
        throw new UnsupportedOperationException();
    }


    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
