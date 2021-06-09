package com.example.touragency.model.dao.impl;

import com.example.touragency.model.dao.UserDao;
import com.example.touragency.model.dao.mapper.entity.UserMapper;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touragency.constants.SqlConstants.*;

public class JDBCUserDao implements UserDao {


    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public void create(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getLastname());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getRole().getId());
            statement.setInt(8, user.getStatus().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot create this user");
        }
    }

    @Override
    public User findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new UserMapper().extractFromResultSet(rs);
            }

        } catch (SQLException e) {
            throw new DaoException("Cannot find user by this id");
        }
        return null;
    }


    @Override
    public List<User> findAll() throws DaoException {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                list.add(new UserMapper().extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            throw new DaoException("Cannot find all users");
        }
        return list;
    }

    @Override
    public void update(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getLastname());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getRole().getId());
            statement.setInt(8, user.getStatus().getId());
            statement.setInt(9, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Cannot update user by this id");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Cannot delete user by this id");
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

}
