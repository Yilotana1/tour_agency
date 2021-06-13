package com.example.touragency;

import com.example.touragency.model.dao.OrderDao;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.exceptions.DaoException;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.impl.OrderServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
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

        OrderService service = new OrderServiceImpl();
        try {
            List<Order> orders = service.getOpenedOrders();
            orders.forEach(order -> message += order.toString() + "\n\n\n");
        } catch (ServiceException throwables) {
            throwables.printStackTrace();
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