package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.dao.mapper.HotelMapper;
import com.example.touragency.model.entity.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touragency.constants.SqlConstants.*;

public class JDBCHotelDao implements HotelDao {


    private final Connection connection;

    public JDBCHotelDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Hotel hotel) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_HOTEL);
        ) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getCity());
            statement.setString(3, hotel.getAddress());
            statement.setInt(4, hotel.getType());
            statement.setString(5, hotel.getAdminPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hotel findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_HOTEL_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new HotelMapper().extractFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> findAll() {
        List<Hotel> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_HOTELS);
            while (rs.next()) {
                list.add(new HotelMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Hotel hotel) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_HOTEL)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getCity());
            statement.setString(3, hotel.getAddress());
            statement.setInt(4, hotel.getType());
            statement.setString(5, hotel.getAdminPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_HOTEL);
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
