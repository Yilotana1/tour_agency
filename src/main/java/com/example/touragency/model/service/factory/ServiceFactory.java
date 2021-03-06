package com.example.touragency.model.service.factory;

import com.example.touragency.model.dao.*;
import com.example.touragency.model.service.*;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public abstract OrderService createOrderService();

    public abstract TourService createTourService();

    public abstract UserService createUserService();

    public abstract HotelService createHotelService();

    public abstract DiscountService createDiscountService();





    public static ServiceFactory getInstance(){
        if( serviceFactory == null ){
            synchronized (ServiceFactory.class){
                if(serviceFactory==null){
                    ServiceFactory temp = new JDBCServiceFactory();
                    serviceFactory = temp;
                }
            }
        }
        return serviceFactory;
    }

}
