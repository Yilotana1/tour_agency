package com.example.touragency.model.dao.beans;

import com.example.touragency.model.entity.*;

public class TourHotelBean extends Entity {

    private Tour tour;
    private Hotel hotel;


    private TourHotelBean() {
    }

    public static TourHotelBean createTourHotelBean(Tour tour, Hotel hotel) {

        TourHotelBean tourHotelBean = new TourHotelBean();
        tourHotelBean.setTour(tour);
        tourHotelBean.setHotel(hotel);
        return tourHotelBean;
    }


    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Tour getTour() {
        return tour;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public String toString() {
        return "TourHotelBean{" +
                "tour=" + tour +
                ", hotel=" + hotel +
                '}';
    }
}
