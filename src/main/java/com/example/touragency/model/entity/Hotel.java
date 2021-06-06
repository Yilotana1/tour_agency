package com.example.touragency.model.entity;

public class Hotel extends Entity {

    private String name;
    private String city;
    private String address;
    private int type;
    private String adminPhone;

    private Hotel() {
    }

    public static Hotel createHotel(int id, String name, String city,
                                   String address, int type, String adminPhone) {
        Hotel hotel = new Hotel();
        hotel.setId(id);
        hotel.setName(name);
        hotel.setCity(city);
        hotel.setAddress(address);
        hotel.setType(type);
        hotel.setAdminPhone(adminPhone);
        return hotel;
    }


    @Override
    public String toString() {
        return "Hotel{" +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", adminPhone='" + adminPhone + '\'' +
                '}';
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getType() {
        return type;
    }

    public String getAdminPhone() {
        return adminPhone;
    }
}
