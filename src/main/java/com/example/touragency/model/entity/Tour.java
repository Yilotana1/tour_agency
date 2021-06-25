package com.example.touragency.model.entity;

import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.entity.enums.TourStatus;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tour extends Entity {
    private String name;
    private String country;
    private BigDecimal price;


    private Hotel hotel;
    private int maxPlaces;
    private int takenPlaces;
    private Calendar startDate;
    private Calendar endDate;
    private TourCategory category;
    private TourStatus status;
    private String city;


    private Tour() {
    }

    public static Tour createTour(int id, String name, String country, BigDecimal price,
                                  int maxPlaces, int takenPlaces,
                                  Calendar startDate, Calendar endDate, TourCategory category, TourStatus status,
                                  Hotel hotel, String city) {
        Tour tour = new Tour();
        tour.setId(id);
        tour.setName(name);
        tour.setCountry(country);
        tour.setPrice(price);
        tour.setMaxPlaces(maxPlaces);
        tour.setTakenPlaces(takenPlaces);
        tour.setStartDate(startDate);
        tour.setEndDate(endDate);
        tour.setCategory(category);
        tour.setStatus(status);
        tour.setHotel(hotel);
        tour.setCity(city);
        return tour;
    }

    public static Tour createTour(String name, String country, BigDecimal price,
                                  int maxPlaces, int takenPlaces,
                                  Calendar startDate, Calendar endDate, TourCategory category, TourStatus status,
                                  Hotel hotel, String city) {
        Tour tour = new Tour();
        tour.setName(name);
        tour.setCountry(country);
        tour.setPrice(price);
        tour.setMaxPlaces(maxPlaces);
        tour.setTakenPlaces(takenPlaces);
        tour.setStartDate(startDate);
        tour.setEndDate(endDate);
        tour.setCategory(category);
        tour.setStatus(status);
        tour.setHotel(hotel);
        tour.setCity(city);
        return tour;
    }


    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", hotel=" + hotel.toString() +
                ", maxPlaces=" + maxPlaces +
                ", takenPlaces=" + takenPlaces +
                ", startDate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(startDate.getTime()) +
                ", endDate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(endDate.getTime()) +
                ", category=" + category +
                ", status=" + status +
                ", city='" + city + '\'' +
                '}';
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMaxPlaces(int maxPlaces) {
        this.maxPlaces = maxPlaces;
    }


    public void setTakenPlaces(int takenPlaces) {
        this.takenPlaces = takenPlaces;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setCategory(TourCategory category) {
        this.category = category;
    }

    public void setStatus(TourStatus status) {
        this.status = status;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getStartDateFormat() {

        return new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime());
    }

    public String getEndDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd").format(endDate.getTime());
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getMaxPlaces() {
        return maxPlaces;
    }

    public int getTakenPlaces() {
        return takenPlaces;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public TourCategory getCategory() {
        return category;
    }

    public TourStatus getStatus() {
        return status;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getCity() {
        return city;
    }

}
