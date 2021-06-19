package com.example.touragency.model.service;

import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.entity.Discount;
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
        return tour.getMaxPlaces() - tour.getTakenPlaces() > 0;
    }

    public static boolean isOutDated(Tour tour, Calendar currentDate) {
        return tour.getStartDate().before(currentDate);
    }

    public static long countClientOrders(OrderDao orderDao, User client) {
            return orderDao.findAll().stream()
                    .filter(order -> order.getClient().getId() == client.getId()).count();
    }


    public static BigDecimal getPriceWithDiscount(Discount discount, List<Tour> tours) {
        BigDecimal priceSum = tours.stream()
                .map(Tour::getPrice)
                .reduce(BigDecimal::add).get();

        int orderTourNumber = tours.size();
        if (orderTourNumber != 0) {
            float discountPercent = (float) (discount.getPercent() * orderTourNumber);
            if (discountPercent < discount.getMaxPercent()) {
                priceSum = priceSum
                        .subtract(priceSum.multiply(BigDecimal.valueOf(discountPercent / 100))
                                .multiply(BigDecimal.valueOf(orderTourNumber)));
            }
        }
        return priceSum;
    }

}
