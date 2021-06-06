package com.example.touragency.model.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tour extends Entity {
    private String name;
    private String country;
    private BigDecimal price;
    private int maxPlaces;
    private int minPlaces;
    private int takenPlaces;
    private Calendar startDate;
    private Calendar endDate;
    private TourCategory category;
    private TourStatus status;
    private int hotelId;
    private String city;


    private Tour() {
    }

    public static Tour createTour(int id, String name, String country, BigDecimal price,
                                  int maxPlaces, int minPlaces, int takenPlaces,
                                  Calendar startDate, Calendar endDate, TourCategory category, TourStatus status,
                                  int hotelId, String city) {
        Tour tour = new Tour();
        tour.setId(id);
        tour.setName(name);
        tour.setCountry(country);
        tour.setPrice(price);
        tour.setMaxPlaces(maxPlaces);
        tour.setMinPlaces(minPlaces);
        tour.setTakenPlaces(takenPlaces);
        tour.setStartDate(startDate);
        tour.setEndDate(endDate);
        tour.setCategory(category);
        tour.setStatus(status);
        tour.setHotelId(hotelId);
        tour.setCity(city);
        return tour;
    }


    @Override
    public String toString() {
        return "Tour{" +
                ", \nname='" + name + '\'' +
                ", \ncountry=" + country +
                ", \nprice=" + price +
                ", \nmaxPlaces=" + maxPlaces +
                ", \nminPlaces=" + minPlaces +
                ", \ntakenPlaces=" + takenPlaces +
                ", \nstartDate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate.getTime()) +
                ", \nendDate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate.getTime()) +
                ", \ncategory=" + category +
                ", \nstatus=" + status +
                ", \nhotel=" + hotelId +
                ", \ncity=" + city +
                '}';
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

    public void setMinPlaces(int minPlaces) {
        this.minPlaces = minPlaces;
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

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getMinPlaces() {
        return minPlaces;
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

    public int getHotelId() {
        return hotelId;
    }

    public String getCity() {
        return city;
    }

}
