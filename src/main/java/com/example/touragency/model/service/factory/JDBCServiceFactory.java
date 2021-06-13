package com.example.touragency.model.service.factory;

import com.example.touragency.ConnectionPoolHolder;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.impl.OrderServiceImpl;
import com.example.touragency.model.service.impl.TourServiceImpl;
import com.example.touragency.model.service.impl.UserServiceImpl;

import java.sql.SQLException;

public class JDBCServiceFactory extends ServiceFactory{

    @Override
    public OrderService createOrderService() throws SQLException {
        return new OrderServiceImpl();
    }


    @Override
    public TourService createTourService() {
        return new TourServiceImpl();
    }



    @Override
    public UserService createUserService() {
        return new UserServiceImpl();
    }

}
