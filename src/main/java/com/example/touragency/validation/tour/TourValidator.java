package com.example.touragency.validation.tour;

import com.example.touragency.Tools;
import com.example.touragency.constants.Messages;
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
        throw new InvalidTourNameException(Messages.TOUR_NAME_SHOULD_CONSIST, name);
    }

    public void checkCountryIsValid(String country) throws InvalidCountryException {
        if (country.matches("[\\p{IsCyrillic}A-Za-z- ]+")) {
            return;
        }

        throw new InvalidCountryException(Messages.TOUR_COUNTRY_SHOULD_CONSIST, country);
    }

    public void checkCityIsValid(String city) throws InvalidCountryException {
        if (city.matches("[\\p{IsCyrillic}A-Za-z- ]+")) {
            return;
        }

        throw new InvalidCountryException(Messages.TOUR_CITY_SHOULD_CONSIST, city);
    }

    public void checkPriceIsValid(String price) throws InvalidPriceException {
        if (price.matches("([0-9]*[.])?[0-9]+")) return;

        throw new InvalidPriceException(Messages.PRICE_SHOULD_CONSIST, price);
    }

    public void checkHotelIsValid(String hotel) throws InvalidHotelNameException {
        if (hotel.matches("[\\p{IsCyrillic}A-Za-z\\d- ]+")) {
            return;
        }
        throw new InvalidHotelNameException(Messages.HOTEL_NAME_SHOULD_CONSIST, hotel);
    }

    public void checkTicketsAreValid(String maxTickets, String takenTickets) throws InvalidTicketsException {
        if (!maxTickets.matches("[\\d]+"))
            throw new InvalidTicketsException(Messages.MAX_TICKETS_SHOULD_BE_NUMBER, maxTickets);

        int iMaxTickets = Integer.parseInt(maxTickets);
        int iTakenTickets = Integer.parseInt(takenTickets);

        if (iMaxTickets < iTakenTickets) {
            throw new InvalidTicketsException(Messages.MAX_TICKETS_NOT_LESS_THAN_TAKEN_TICKETS, maxTickets);
        }

    }

    public void checkDatesAreValid(String startDateF, String endDateF) throws InvalidDateException{
        Calendar startDate = Tools.getCalendarFromString(startDateF);
        Calendar endDate = Tools.getCalendarFromString(endDateF);

        if (startDate.after(endDate)){
            throw new InvalidDateException(Messages.START_CANNOT_BE_AFTER_END, new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
        }

        if (startDate.before(Calendar.getInstance())){
            throw new InvalidDateException(Messages.START_CANNOT_BE_BEFORE_CURRENT, new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
        }


        if (endDate.before(Calendar.getInstance())){
            throw new InvalidDateException(Messages.END_CANNOT_BE_BEFORE_CURRENT, new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
        }
    }

    public void checkTourIsValid(String name, String country, String city, String price, String hotel, String maxTickets,
                                 String takenTickets, String startDate, String endDate) throws InvalidDataException {
        checkNameIsValid(name);
        checkCountryIsValid(country);
        checkCityIsValid(city);
        checkPriceIsValid(price);
        checkHotelIsValid(hotel);
        checkTicketsAreValid(maxTickets, takenTickets);
        checkDatesAreValid(startDate, endDate);
    }


}
