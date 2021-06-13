package com.example.touragency.model.dao.Factory;

import com.example.touragency.model.dao.*;

import java.sql.Connection;

public abstract class DaoFactory {
        private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract UserDao createUserDao(Connection connection);

    public abstract TourDao createTourDao();
    public abstract TourDao createTourDao(Connection connection);

    public abstract OrderDao createOrderDao();
    public abstract OrderDao createOrderDao(Connection connection);

    public abstract HotelDao createHotelDao();
    public abstract HotelDao createHotelDao(Connection connection);

    public abstract DiscountDao createDiscountDao();
    public abstract DiscountDao createDiscountDao(Connection connection);



        public static DaoFactory getInstance(){
            if( daoFactory == null ){
                synchronized (DaoFactory.class){
                    if(daoFactory==null){
                        DaoFactory temp = new JDBCDaoFactory();
                        daoFactory = temp;
                    }
                }
            }
            return daoFactory;
        }

}
