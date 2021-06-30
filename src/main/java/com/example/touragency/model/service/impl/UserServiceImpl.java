package com.example.touragency.model.service.impl;

import com.example.touragency.constants.Messages;
import com.example.touragency.controller.Servlet;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.UserDao;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.exceptions.*;
import com.example.touragency.model.service.UserService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


/**
 * User Service implementation. Presents method to realize business process
 */
public class UserServiceImpl implements UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public final static Logger log = Logger.getLogger(Servlet.class);


    /**
     * Return User entity if user login and password matches, or throw ServiceException with respective message.
     * @param login
     * @param password
     * @return User
     * @throws ServiceException
     */
    @Override
    public Optional<User> signIn(String login, String password) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            Optional<User> user = userDao.findUserByLogin(login);

            user.filter(
                    u -> u.getPassword().equals(password)
            )
                    .orElseThrow(() -> new ServiceException(Messages.LOGIN_OR_PASSWORD_NOT_FOUND));


            return user;

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    public void update(int id, String firstName, String lastName, String phone, String email, UserStatus status,
                       String login, String password, Role role) throws ServiceException {

        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = User.createUser(id, firstName, lastName, phone, email, status, login, password, role);
            userDao.update(user);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public void update(String currentLogin, String firstName, String lastName,
                       String phone, String email, UserStatus status,
                       String login, String password, Role role) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            userDao.getConnection().setAutoCommit(false);
            userDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            throwExceptionIfDataExist(currentLogin, login, email, phone, userDao);

            userDao.update(currentLogin, firstName, lastName, phone, email, status, login, password, role);
            userDao.getConnection().commit();

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    /**
     * Check if user data is already exists in the system. If it is, throw respective exception.
     * Uses while login and registration process to prevent duplicates entries or error in db.
     *
     * @param currentLogin
     * @param login
     * @param email
     * @param userDao
     * @return User
     * @throws ServiceException
     */
    private void throwExceptionIfDataExist(String currentLogin, String login, String email, String phone, UserDao userDao) throws ServiceException, SQLException {
        Optional<User> userWithTheSameLogin = userDao.findUserByLogin(login);
        Optional<User> userWithTheSameEmail = userDao.findUserByEmail(email);
        Optional<User> userWithTheSamePhone = userDao.findUserByPhone(phone);
        Optional<User> userToUpdate = userDao.findUserByLogin(currentLogin);

        if (userWithTheSameLogin.isPresent() && !userToUpdate.get().getLogin().equals(login))
            throw new ServiceException(Messages.LOGIN_ALREADY_EXISTS);

        if (userWithTheSameEmail.isPresent() && !userToUpdate.get().getEmail().equals(email))
            throw new ServiceException(Messages.EMAIL_ALREADY_EXISTS);

        if (userWithTheSamePhone.isPresent() && !userToUpdate.get().getPhone().equals(phone))
            throw new ServiceException(Messages.PHONE_ALREADY_EXISTS);
    }


    /**
     * Create user in the system if it's not presented yet
     * @param user
     * @throws ServiceException
     */
    @Override
    public void signUp(User user) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.getConnection().setAutoCommit(false);
            userDao.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            throwExceptionIfDataExists(user, userDao);
            userDao.create(user);

            userDao.getConnection().commit();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    private void throwExceptionIfDataExists(User user, UserDao userDao) throws ServiceException, SQLException {
        Optional<User> userWithTheSameLogin = userDao.findUserByLogin(user.getLogin());
        Optional<User> userWithTheSameEmail = userDao.findUserByEmail(user.getEmail());
        Optional<User> userWithTheSamePhone = userDao.findUserByPhone(user.getPhone());

        if (userWithTheSameLogin.isPresent()) throw new ServiceException(Messages.LOGIN_ALREADY_EXISTS);
        if (userWithTheSameEmail.isPresent()) throw new ServiceException(Messages.EMAIL_ALREADY_EXISTS);
        if (userWithTheSamePhone.isPresent()) throw new ServiceException(Messages.PHONE_ALREADY_EXISTS);
    }


    @Override
    public int getCount() throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getCount();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public List<User> getAll() throws ServiceException{
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    /**
     * Retrieve user list for pagination in order as it's saved in db. pageId is page number, pageSize is max items on page.
     * @param pageId
     * @param pageSize
     * @return User list
     * @throws ServiceException
     */
    @Override
    public List<User> getPage(int pageId, int pageSize) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByLimit(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    /**
     * Retrieve user list for pagination with clients at the top of the list. pageId is page number, pageSize is max items on page.
     * @param pageId
     * @param pageSize
     * @return
     * @throws ServiceException
     */
    @Override
    public List<User> getPageClientsFirst(int pageId, int pageSize) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitClientsFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    /**
     * Retrieve user list for pagination with managers at the top of the list. pageId is page number, pageSize is max items on page.
     * @param pageId
     * @param pageSize
     * @return
     * @throws ServiceException
     */
    @Override
    public List<User> getPageManagersFirst(int pageId, int pageSize) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitManagersFirst(pageId * pageSize - pageSize + 1, pageSize);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    /**
     * Retrieve user list for pagination with not blocked users first at the top of the list. pageId is page number, pageSize is max items on page.
     * @param pageId
     * @param pageSize
     * @return
     * @throws ServiceException
     */
    @Override
    public List<User> getPageNonBlockedFirst(int pageId, int pageSize) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitNonBlockedFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public List<User> getPageBlockedFirst(int pageId, int pageSize) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            return userDao.findByLimitBlockedFirst(pageId * pageSize - pageSize + 1, pageSize);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public void update(User user) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public Optional<User> getById(int id) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }


    @Override
    public Optional<User> getByLogin(String login) throws ServiceException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findUserByLogin(login);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public int add(User user) throws ServiceException {
        try {
            return daoFactory.createUserDao().create(user);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

}
