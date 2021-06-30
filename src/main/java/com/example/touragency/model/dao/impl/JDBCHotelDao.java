package com.example.touragency.model.dao.impl;

import com.example.touragency.Tools;
import com.example.touragency.model.dao.HotelDao;
import com.example.touragency.model.dao.mapper.HotelMapper;
import com.example.touragency.model.dao.mapper.OrderMapper;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.touragency.constants.db.sql.Hotel.*;
import static com.example.touragency.constants.db.sql.Order.SQL_FIND_ORDERS_BY_LIMIT;
import static com.example.touragency.constants.db.sql.Tour.SQL_FIND_TOUR_NUMBER_AS_COUNT;


/**
 * Hotel dao implementation. Presents methods for access to Hotel table in database
 * @author Anatoliy Zhilko
 */
public class JDBCHotelDao implements HotelDao {


    private final Connection connection;

    public JDBCHotelDao(Connection connection) {
        this.connection = connection;
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public int getCount() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SQL_FIND_HOTEL_NUMBER_AS_COUNT);
        rs.next();
        return rs.getInt("count");
    }

    @Override
    public int create(Hotel hotel) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_HOTEL, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getCity());
            statement.setString(3, hotel.getAddress());
            statement.setInt(4, hotel.getStars());
            statement.setString(5, hotel.getAdminPhone());
            statement.executeUpdate();
            return Tools.getGeneratedId(statement);
        }
    }

    @Override
    public Optional<Hotel> findById(int id) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_HOTEL_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(new HotelMapper().extractFromResultSet(rs));
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<Hotel> findByName(String name) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_HOTEL_BY_NAME);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(new HotelMapper().extractFromResultSet(rs));
            }

        }
        return Optional.empty();
    }


    @Override
    public List<Hotel> findAll() throws SQLException{
        List<Hotel> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_HOTELS);
            while (rs.next()) {
                list.add(new HotelMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of hotel in certain range,specified in sql script by "LIMIT ?, ?".
     *
     * @param start
     * @param count
     * @return Hotel list
     * @throws SQLException
     */
    @Override
    public List<Hotel> findByLimit(int start, int count) throws SQLException{
        List<Hotel> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_HOTELS_BY_LIMIT);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new HotelMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public void update(Hotel hotel) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_HOTEL)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getCity());
            statement.setString(3, hotel.getAddress());
            statement.setInt(4, hotel.getStars());
            statement.setString(5, hotel.getAdminPhone());
            statement.setInt(6, hotel.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_HOTEL);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
