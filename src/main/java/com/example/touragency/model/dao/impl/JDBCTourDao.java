package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.dao.mapper.entity.TourMapper;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.exceptions.DaoException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touragency.constants.SqlConstants.*;

public class JDBCTourDao implements TourDao {


    private final Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    public JDBCTourDao(Connection connection) {
        this.connection = connection;
    }



    @Override
    public void create(Tour tour) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TOUR);
        ) {
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getCountry());
            statement.setBigDecimal(3, tour.getPrice());
            statement.setInt(4, tour.getMaxPlaces());
            statement.setInt(5, tour.getMinPlaces());
            statement.setInt(6, tour.getTakenPlaces());
            statement.setDate(7, new Date(tour.getStartDate().getTimeInMillis()));
            statement.setDate(8, new Date(tour.getEndDate().getTimeInMillis()));
            statement.setInt(9, tour.getCategory().getId());
            statement.setInt(10, tour.getStatus().getId());
            statement.setInt(11, tour.getHotel().getId());
            statement.setString(12, tour.getCity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot create this tour");
        }
    }


    @Override
    public void addTourToOrder(int tourId, int orderId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_TOUR_TO_ORDER);
        ) {
            statement.setInt(1, orderId);
            statement.setInt(2, tourId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot add tour to order_has_tour table");
        }
    }

    @Override
    public Tour findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new TourMapper().extractFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find tour by id");
        }
        return null;
    }

    @Override
    public Tour findByName(String name) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_BY_NAME);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new TourMapper().extractFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find tour by id");
        }
        return null;
    }

    @Override
    public List<Tour> findByOrderId(int id) throws DaoException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_ORDER_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }


    @Override
    public List<Tour> findByCategory(TourCategory category) throws DaoException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_CATEGORY)) {
            statement.setInt(1, category.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public List<Tour> findByPrice(BigDecimal price) throws DaoException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_PRICE)) {
            statement.setBigDecimal(1 ,price);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public List<Tour> findByPeople(int peopleNumber) throws DaoException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_PEOPLE)) {
            statement.setInt(1, peopleNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public List<Tour> findByHotelId(int hotelId) throws DaoException {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_HOTEL_ID)) {
            statement.setInt(1, hotelId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }


    @Override
    public List<Tour> findAll() throws DaoException {
        List<Tour> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_TOURS);
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot find all tours");
        }
        return list;
    }

    @Override
    public void update(Tour tour) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TOUR);
        ) {
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getCountry());
            statement.setBigDecimal(3, tour.getPrice());
            statement.setInt(4, tour.getMaxPlaces());
            statement.setInt(5, tour.getMinPlaces());
            statement.setInt(6, tour.getTakenPlaces());
            statement.setDate(7, new Date(tour.getStartDate().getTimeInMillis()));
            statement.setDate(8, new Date(tour.getEndDate().getTimeInMillis()));
            statement.setInt(9, tour.getCategory().getId());
            statement.setInt(10, tour.getStatus().getId());
            statement.setInt(11, tour.getHotel().getId());
            statement.setString(12, tour.getCity());
            statement.setInt(13, tour.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Cannot update tour by this id");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TOUR);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Cannot delete tour by this id");
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }


}
