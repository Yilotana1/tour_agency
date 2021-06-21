package com.example.touragency.model.dao.impl;

import com.example.touragency.model.Tools;
import com.example.touragency.model.dao.TourDao;
import com.example.touragency.model.dao.mapper.entity.TourMapper;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.enums.TourCategory;

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

    @Override
    public int getCount() {
        return 0;
    }

    public JDBCTourDao(Connection connection) {
        this.connection = connection;
    }



    @Override
    public int create(Tour tour) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TOUR, Statement.RETURN_GENERATED_KEYS);
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
            return Tools.getGeneratedId(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    @Override
    public void addTourToOrder(int tourId, int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_TOUR_TO_ORDER);
        ) {
            statement.setInt(1, orderId);
            statement.setInt(2, tourId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tour findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new TourMapper().extractFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tour findByName(String name){
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOUR_BY_NAME);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new TourMapper().extractFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> findByOrderId(int id){
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
        }
        return list;
    }


    @Override
    public List<Tour> findByCategory(TourCategory category) {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_CATEGORY)) {
            statement.setInt(1, category.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    @Override
    public List<Tour> findByPrice(BigDecimal price)  {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_PRICE)) {
            statement.setBigDecimal(1 ,price);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Tour> findByPeople(int peopleNumber)  {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_PEOPLE)) {
            statement.setInt(1, peopleNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Tour> findByHotelId(int hotelId) {
        List<Tour> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TOURS_BY_HOTEL_ID)) {
            statement.setInt(1, hotelId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Tour> findAll()  {
        List<Tour> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_TOURS);
            while (rs.next()) {
                list.add(new TourMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Tour> findByLimit(int start, int count) {
        return null;
    }

    @Override
    public void update(Tour tour)  {
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
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TOUR);
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
