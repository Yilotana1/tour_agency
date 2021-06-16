package com.example.touragency.model.dao;

import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.exceptions.DaoException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.touragency.constants.SqlConstants.SQL_INSERT_ORDER;

public interface OrderDao extends GenericDao<Order>{ }
