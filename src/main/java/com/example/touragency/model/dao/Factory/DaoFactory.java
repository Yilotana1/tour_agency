package com.example.touragency.model.dao.Factory;

import com.example.touragency.model.dao.*;

public abstract class DaoFactory {
        private static DaoFactory daoFactory;

        public abstract UserDao createUserDao();
        public abstract TourDao createTourDao();
        public abstract OrderDao createOrderDao();
        public abstract HotelDao createHotelDao();
        public abstract DiscountDao createDiscountDao();
        public abstract OrderClientBeanDao createOrderClientBeanDao();
        public abstract TourHotelBeanDao createTourHotelBeanDao();
        public abstract OrderTourBeanDao createOrderTourBeanDao();


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
