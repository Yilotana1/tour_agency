package com.example.touragency.validation.tour;

import com.example.touragency.Tools;
import com.example.touragency.validation.InvalidDataException;
import com.example.touragency.validation.tour.exceptions.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TourValidator {


    public static TourValidator createValidator() {
        return new TourValidator();
    }


    public void checkNameIsValid(String name) throws InvalidTourNameException {
        if (name.matches("[\\p{IsCyrillic}A-Za-z\\d- ]+")) return;
        throw new InvalidTourNameException("Tour name should contain only latin or cyrillic characters or digits", name);
    }

    public void checkCountryIsValid(String country) throws InvalidCountryException {
        if (country.matches("[\\p{IsCyrillic}A-Za-z- ]+")) {
            return;
        }

        throw new InvalidCountryException("Tour country should contain only latin or cyrillic characters", country);
    }

    public void checkCityIsValid(String city) throws InvalidCountryException {
        if (city.matches("[\\p{IsCyrillic}A-Za-z- ]+")) {
            return;
        }

        throw new InvalidCountryException("Tour city should contain only latin or cyrillic characters", city);
    }

    public void checkPriceIsValid(String price) throws InvalidPriceException {
        if (price.matches("([0-9]*[.])?[0-9]+")) return;

        throw new InvalidPriceException("Price should contain only digits", price);
    }

    public void checkHotelIsValid(String hotel) throws InvalidHotelNameException {
        if (hotel.matches("[\\p{IsCyrillic}A-Za-z\\d- ]+")) {
            return;
        }
        throw new InvalidHotelNameException("Hotel name should contain only latin or cyrillic characters or digits", hotel);
    }

    public void checkTicketsAreValid(String maxTickets, String minTickets, String takenTickets) throws InvalidTicketsException {
        if (!maxTickets.matches("[\\d]+"))
            throw new InvalidTicketsException("Max tickets should be specified as a number", maxTickets);

        if (!minTickets.matches("[\\d]+"))
            throw new InvalidTicketsException("Min tickets should be specified as a number", minTickets);

        int iMaxTickets = Integer.parseInt(maxTickets);
        int iMinTickets = Integer.parseInt(minTickets);
        int iTakenTickets = Integer.parseInt(takenTickets);

        if (iMaxTickets < iMinTickets || iMaxTickets < iTakenTickets) {
            throw new InvalidTicketsException("Max tickets cannot be less than min tickets or taken tickets", maxTickets);
        }

        if (iMinTickets > iTakenTickets) {
            throw new InvalidTicketsException("Min tickets cannot be more than max tickets or less than taken tickets", minTickets);
        }

    }

    public void checkDatesAreValid(String startDateF, String endDateF) throws InvalidDateException{
        Calendar startDate = Tools.getCalendarFromString(startDateF);
        Calendar endDate = Tools.getCalendarFromString(endDateF);

        if (startDate.after(endDate)){
            throw new InvalidDateException("Start-date cannot be after end-date", new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
        }

        if (startDate.after(Calendar.getInstance())){
            throw new InvalidDateException("Start-date cannot be after current date", new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
        }


        if (endDate.before(Calendar.getInstance())){
            throw new InvalidDateException("End-date cannot be before current date", new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
        }
    }

    public void checkTourIsValid(String name, String country, String city, String price, String hotel,
                                 String minTickets, String maxTickets,
                                 String takenTickets, String startDate, String endDate) throws InvalidDataException {
        checkNameIsValid(name);
        checkCountryIsValid(country);
        checkCityIsValid(city);
        checkPriceIsValid(price);
        checkHotelIsValid(hotel);
        checkTicketsAreValid(maxTickets, minTickets, takenTickets);
        checkDatesAreValid(startDate, endDate);
    }


}
