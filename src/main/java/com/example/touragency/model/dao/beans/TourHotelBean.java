package com.example.touragency.model.dao.beans;

import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Tour;

public class TourHotelBean {

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

    public Hotel getHotel() {
        return hotel;
    }


    public Tour getTour() {
        return tour;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
