package com.example.touragency.model.dao.impl;

import com.example.touragency.Tools;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.dao.mapper.TourMapper;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.touragency.constants.db.sql.Tour.*;


/**
 * Tour dao implementation. Presents methods for access to Tour table in database
 * @author Anatoliy Zhilko
 */
public class JDBCTourDao implements TourDao {


    private final Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public int getCount() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SQL_FIND_TOUR_NUMBER_AS_COUNT);
        rs.next();
        return rs.getInt("count");
    }

    public JDBCTourDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int create(Tour tour) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TOUR, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, tour.getName());
        statement.setString(2, tour.getCountry());
        statement.setBigDecimal(3, tour.getPrice());
        statement.setInt(4, tour.getMaxTickets());
        statement.setInt(5, tour.getTakenTickets());
        statement.setDate(6, new Date(tour.getStartDate().getTimeInMillis()));
        statement.setDate(7, new Date(tour.getEndDate().getTimeInMillis()));
        statement.setInt(8, tour.getCategory().getId());
        statement.setInt(9, tour.getStatus().getId());
        statement.setInt(10, tour.getHotel().getId());
        statement.setString(11, tour.getCity());
        statement.executeUpdate();
        return Tools.getGeneratedId(statement);

    }


    @Override
    public Optional<Tour> findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_BY_ID);

        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return Optional.of(new TourMapper().extractFromResultSet(rs));
        }


        return Optional.empty();
    }

    @Override
    public Optional<Tour> findByName(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_BY_NAME);

        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return Optional.of(new TourMapper().extractFromResultSet(rs));
        }


        return Optional.empty();
    }

    /**
     * Method returns list of tours in certain range and with certain country field. Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     * @param country
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitCountry(int start, int count, String country) throws SQLException {
        List<Tour> list = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_COUNTRY);


        statement.setString(1, country);
        statement.setInt(2, start - 1);
        statement.setInt(3, count);

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            list.add(new TourMapper().extractFromResultSet(rs));
        }


        return list;
    }

    /**
     * Method returns list of tours in certain range and with certain name field. Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitName(int start, int count, String name) throws SQLException {
        List<Tour> list = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_NAME);

        statement.setString(1, name);
        statement.setInt(2, start - 1);
        statement.setInt(3, count);

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            list.add(new TourMapper().extractFromResultSet(rs));
        }


        return list;
    }


    @Override
    public List<Tour> findAll() throws SQLException{
        List<Tour> list = new ArrayList<>();Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_TOURS);
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        return list;
    }

    @Override
    public List<Tour> findByLimit(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of tours in certain range and only with status BURNING. Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitBurningFirst(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_BURNING_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of tours in certain range and only with status NON_BURNING. Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitNonBurningFirst(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_NON_BURNING_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of tours in certain range and order by hotel stars in descending order.
     * Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitHighHotelStarsFirst(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_HIGH_HOTEL_STARS_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of tours in certain range and order by hotel stars in ascending order.
     * Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitLowHotelStarsFirst(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_LOW_HOTEL_STARS_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of tours in certain range and order by price in descending order.
     * Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitHighPriceFirst(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_HIGH_PRICE_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }

        return list;
    }


    /**
     * Method returns list of tours in certain range and order by price in ascending order.
     * Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitLowPriceFirst(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_LOW_PRICE_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    /**
     * Method returns list of tours in certain range and only with category EXCURSION.
     * Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitExcursion(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_EXCURSION);
        ) {

            statement.setInt(1, TourCategory.EXCURSION.getId());
            statement.setInt(2, start - 1);
            statement.setInt(3, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of tours in certain range and only with category REST.
     * Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitRest(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_REST);
        ) {

            statement.setInt(1, TourCategory.REST.getId());
            statement.setInt(2, start - 1);
            statement.setInt(3, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }


    /**
     * Method returns list of tours in certain range and only with category SHOPPING.
     * Method's using sql script with "LIMIT ?,?"
     * @param start
     * @param count
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tour> findByLimitShopping(int start, int count) throws SQLException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_LIMIT_SHOPPING);
        ) {

            statement.setInt(1, TourCategory.SHOPPING.getId());
            statement.setInt(2, start - 1);
            statement.setInt(3, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public void update(Tour tour) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TOUR);
        ) {
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getCountry());
            statement.setBigDecimal(3, tour.getPrice());
            statement.setInt(4, tour.getMaxTickets());
            statement.setInt(5, tour.getTakenTickets());
            statement.setDate(6, new Date(tour.getStartDate().getTimeInMillis()));
            statement.setDate(7, new Date(tour.getEndDate().getTimeInMillis()));
            statement.setInt(8, tour.getCategory().getId());
            statement.setInt(9, tour.getStatus().getId());
            statement.setInt(10, tour.getHotel().getId());
            statement.setString(11, tour.getCity());
            statement.setInt(12, tour.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TOUR);
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
