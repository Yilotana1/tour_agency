package com.example.touragency.model.service.factory;

import com.example.touragency.ConnectionPoolHolder;
import com.example.touragency.model.service.*;
import com.example.touragency.model.service.impl.*;

import java.sql.SQLException;

public class JDBCServiceFactory extends ServiceFactory {

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

    @Override
    public HotelService createHotelService() {
        return new HotelServiceImpl();
    }

    @Override
    public DiscountService createDiscountService() {
        return new DiscountServiceImpl();
    }

}
