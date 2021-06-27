package com.example.touragency.model.dao.impl;

import com.example.touragency.Tools;
import com.example.touragency.model.dao.UserDao;
import com.example.touragency.model.dao.mapper.UserMapper;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.touragency.constants.db.sql.User.*;

public class JDBCUserDao implements UserDao {


    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public int getCount() throws SQLException {
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_USERS_NUMBER_AS_COUNT);
            rs.next();
            return rs.getInt("count");

        }
    }

    @Override
    public int create(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
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
            return Tools.getGeneratedId(statement);
        }
    }

    @Override
    public Optional<User> findById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new UserMapper().extractFromResultSet(rs));
            }

        }
        return Optional.empty();
    }


    @Override
    public List<User> findAll() throws SQLException{
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                list.add(new UserMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public List<User> findByLimit(int start, int count) throws SQLException{
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_LIMIT);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new UserMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public void update(User user) throws SQLException {
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
        }
    }

    public void update(String currentLogin, String firstName, String lastName, String phone, String email,
                       UserStatus status, String login, String password, Role role) throws SQLException{

        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_BY_LOGIN);
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, email);
            statement.setString(6, phone);
            statement.setInt(7, role.getId());
            statement.setInt(8, status.getId());
            statement.setString(9, currentLogin);
            statement.executeUpdate();
        }

    }

    @Override
    public void delete(int id) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
        ) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new UserMapper().extractFromResultSet(rs));
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
        ) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new UserMapper().extractFromResultSet(rs));
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByPhone(String phone) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_PHONE);
        ) {
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new UserMapper().extractFromResultSet(rs));
            }

        }
        return Optional.empty();
    }

    @Override
    public List<User> findByLimitClientsFirst(int start, int count) throws SQLException{
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_LIMIT_CLIENTS_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new UserMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public List<User> findByLimitManagersFirst(int start, int count) throws SQLException{
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_LIMIT_MANAGERS_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new UserMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public List<User> findByLimitNonBlockedFirst(int start, int count) throws SQLException{
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_LIMIT_NON_BLOCKED_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new UserMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }

    @Override
    public List<User> findByLimitBlockedFirst(int start, int count) throws SQLException{
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_LIMIT_BLOCKED_FIRST);
        ) {

            statement.setInt(1, start - 1);
            statement.setInt(2, count);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new UserMapper().extractFromResultSet(rs));
            }

        }
        return list;
    }
}

