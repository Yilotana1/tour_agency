package com.example.touragency.model.dao.impl;

import com.example.touragency.Tools;
import com.example.touragency.model.dao.DiscountDao;
import com.example.touragency.model.dao.mapper.DiscountMapper;
import com.example.touragency.model.entity.Discount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.touragency.constants.db.sql.Discount.*;

public class JDBCDiscountDao implements DiscountDao {


    private final Connection connection;

    public JDBCDiscountDao(Connection connection) {
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
    public int create(Discount discount){
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DISCOUNT, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, discount.getPercent());
            statement.setInt(2, discount.getMaxPercent());
            statement.executeUpdate();
            return Tools.getGeneratedId(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Optional<Discount> findById(int id) {
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
    public List<Discount> findAll() {
        List<Discount> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_DISCOUNTS);
            while (rs.next()) {
                list.add(new DiscountMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public List<Discount> findByLimit(int start, int count) {
        return null;
    }

    @Override
    public void update(Discount discount) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DISCOUNT);
        ) {
            statement.setInt(1, discount.getPercent());
            statement.setInt(2, discount.getMaxPercent());
            statement.setInt(3, discount.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DISCOUNT);
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
