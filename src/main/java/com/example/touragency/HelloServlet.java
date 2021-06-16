package com.example.touragency;

import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.Hotel;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.Tour;
import com.example.touragency.model.entity.User;
import com.example.touragency.model.exceptions.DaoException;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.*;
import com.example.touragency.model.service.factory.JDBCServiceFactory;
import com.example.touragency.model.service.factory.ServiceFactory;
import com.example.touragency.model.service.impl.DiscountServiceImpl;
import com.example.touragency.model.service.impl.HotelServiceImpl;
import com.example.touragency.model.service.impl.OrderServiceImpl;
import com.example.touragency.model.service.impl.UserServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "My name is Anatoliy\n\n\n";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        TourService service = ServiceFactory.getInstance().createTourService();
        String country = request.getParameter("country");
        try {
            List<Tour> tours = service.getToursByCountry(country);
            if (tours != null){
                request.setAttribute("tours", tours);
                request.getRequestDispatcher("test.jsp").forward(request, response);
            }
        } catch (ServiceException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

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