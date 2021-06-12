package com.example.touragency.model.service;

import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.entity.enums.UserStatus;
import com.example.touragency.model.exceptions.DaoException;

import java.math.BigDecimal;
import java.util.Calendar;

public class ServiceTools {


    public static boolean isUserBlocked(User user) {
        return user.getStatus().equals(UserStatus.BLOCKED);
    }

    public static boolean containsFreePlaces(Tour tour) {
        return tour.getMaxPlaces() - tour.getTakenPlaces() > 0;
    }

    public static boolean isOutDated(Tour tour, Calendar currentDate) {
        return tour.getStartDate().before(currentDate);
    }

    public static long countClientOrders(OrderDao orderDao, User client) {
        try {
            return orderDao.findAll().stream()
                    .filter(order -> order.getClient().getId() == client.getId()).count();
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }


    public static BigDecimal getPriceWithDiscount(Discount discount, Tour tour, long userOrderNumber) {
        BigDecimal price = tour.getPrice();
        if (userOrderNumber != 0) {
            float discountPercent = (float) (discount.getPercent() * userOrderNumber);
            if (discountPercent < discount.getMaxPercent()) {
                price = price
                        .subtract(price.multiply(BigDecimal.valueOf(discountPercent / 100))
                                .multiply(BigDecimal.valueOf(userOrderNumber)));
            }
        }
        return price;
    }

}
