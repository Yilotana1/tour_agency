package com.example.touragency;

import com.example.touragency.model.dao.*;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.dao.beans.TourHotelBean;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.TourCategory;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.DaoException;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.TourService;
import com.example.touragency.model.service.impl.ClientServiceImpl;
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
            TourService service = new TourServiceImpl();
            TourHotelBean tourHotelBean = service.getTourById(4);
            Tour tour = tourHotelBean.getTour();
            tourHotelBean.getTour().setMaxPlaces(70);
            tour.setName("XXX");
            service.addTour(tour);

        } catch (ServiceException throwables) {
            throwables.printStackTrace();
        }


//        TourHotelBeanDao tourHotelBeanDao = DaoFactory.getInstance().createTourHotelBeanDao();
//        try {
//            List<TourHotelBean> beans = tourHotelBeanDao.findByCategory(TourCategory.EXCURSION);
//            for (TourHotelBean bean:
//                 beans) {
//                message += bean.toString() + "\n\n\n\n";
//            }
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
    }

    public void destroy() {
    }
}