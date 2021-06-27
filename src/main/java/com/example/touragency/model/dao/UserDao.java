package com.example.touragency.model.dao;

import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User>{

    @Override
    void update(User entity) throws SQLException;

    void update(String currentLogin, String firstName, String lastName, String phone, String email,
           UserStatus status, String login, String password, Role role) throws SQLException;

    Optional<User> findUserByLogin(String login) throws SQLException;

    Optional<User> findUserByEmail(String email) throws SQLException;

    Optional<User> findUserByPhone(String phone) throws SQLException;

    List<User> findByLimitManagersFirst(int start, int count) throws SQLException;

    List<User> findByLimitClientsFirst(int start, int count) throws SQLException;

    List<User> findByLimitNonBlockedFirst(int start, int count) throws SQLException;

    List<User> findByLimitBlockedFirst(int start, int count) throws SQLException;
}
