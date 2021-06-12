package com.example.touragency;

import com.example.touragency.model.entity.*;
import com.example.touragency.model.entity.enums.TourCategory;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.UserService;
import com.example.touragency.model.service.impl.OrderServiceImpl;
import com.example.touragency.model.service.impl.TourServiceImpl;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "My name is Anatoliy\n\n\n";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        try {
            OrderService service = new OrderServiceImpl();
            service.applyForOrder("private full day tour", "Alex5");


        } catch (ServiceException throwables) {
            throwables.printStackTrace();
        }


//        DaoFactory daoFactory = DaoFactory.getInstance();
//        try (TourDao tourDao = daoFactory.createTourDao();
//             OrderDao orderDao = daoFactory.createOrderDao();
//             DiscountDao discountDao = daoFactory.createDiscountDao();
//             UserDao userDao = daoFactory.createUserDao();) {
//            User client = userDao.findById(6);
//            Tour tour = tourDao.findById(3);
//            long userTourNumber = orderDao.findAll().stream()
//                    .filter(order -> order.getClientId() == client.getId()).count();
//
//            Discount discount = discountDao.findById(1);
//            message += "percent = " + discount.getPercent() + "\n\n\n";
//            message += "tournumber = " + userTourNumber + "\n\n\n";
//
//            BigDecimal price = tour.getPrice();
//            message += "price = " + price.toString() + "\n\n\n";
//            double discountPercent = (int) (discount.getPercent() * 3);
//            if (userTourNumber != 0) {
//                if (discountPercent < discount.getMaxPercent()) {
//                    price = price.multiply(BigDecimal.valueOf(21 / 100));
//                }
//            }
//            price = price.multiply(BigDecimal.valueOf(discountPercent));
//            message += "prco " + price;


//            try {
//                client = DaoFactory.getInstance().createUserDao().findById(6);
//                tour = new TourServiceImpl().getTourById(3).getTour();
//                new OrderServiceImpl().applyForOrder(tour, client);
//            } catch (DaoException | ServiceException throwables) {
//                throwables.printStackTrace();
//            }


//        OrderTourBeanDao orderTourBeanDao = DaoFactory.getInstance().createOrderTourBeanDao();
//        try {
//            OrderTourBean bean = orderTourBeanDao.findById(1);
//            message = bean.toString();
//        } catch (DaoException throwables) {
//            throwables.printStackTrace();
//        }


//        ClientService service = new ClientServiceImpl();
//        List<User> list = service.getClientsWithOrders();
//        String s = "";
//
//        for (User user: list) {
//            s += user.toString() + "\n\n\n";
//        }
//
//        message = s;

//        TourServiceImpl service = new TourServiceImpl();
//        try {
//            List<TourHotelBean> tours = service.getToursByHotelStarsLessThan(4);
//            String s = "";
//            for (TourHotelBean bean:
//                 tours) {
//                s += bean.toString() + "\n\n\n";
//            }
//            message = s;
//        } catch (ServiceException throwables) {
//            throwables.printStackTrace();
//        }


        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
        out.println(message);
//        out.println("</body></html>");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }

    public void destroy() {
    }
}