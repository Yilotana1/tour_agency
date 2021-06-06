package com.example.touragency;

import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.Discount;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "My name is Anatoliy";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao();
        discountDao.delete(1);
//        tour.setName("HELLO WORLD!");
//        tourDao.update(tour);
//        Tour tour = Tour.createTour(7, "PORNHUB",
//                "Hungary", new BigDecimal("5.357"), 10, 4, 7, Calendar.getInstance(), Calendar.getInstance(), TourCategory.EXCURSION,
//                TourStatus.BURNING, 3, 3, "Lviv", "FWQE");
//        message = String.valueOf(tour.getStatus().getId());
//        tourDao.create(tour);
//        message = tour.toString();
//        tour.setLogin("GRISHA_UBIYCA");
//        userDao.update(user);

//        List<User> list = userDao.findAll();
//        for (User user : list) {
//            message += user.toString() + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();
//        }
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}