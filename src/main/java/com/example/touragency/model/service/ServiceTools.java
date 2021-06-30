package com.example.touragency.model.service;

import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.UserStatus;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class ServiceTools {


    public static boolean isUserBlocked(User user) {
        return user.getStatus().equals(UserStatus.BLOCKED);
    }

    public static boolean containsFreeTickets(Tour tour) {
        return tour.getMaxTickets() - tour.getTakenTickets() > 0;
    }

    public static boolean isOutDated(Tour tour) {
        return tour.getStartDate().before(Calendar.getInstance());
    }


    public static BigDecimal getPriceWithDiscount(Discount discount, List<Order> orders, BigDecimal tourPrice) {

        float percentSum = orders.size() * discount.getPercent();
        if (percentSum > discount.getMaxPercent()) {
            percentSum = (orders.size() - 1) * discount.getPercent();
        }

        return tourPrice
                .subtract(
                        tourPrice.multiply(BigDecimal.valueOf(percentSum / 100))
                );


    }

}
