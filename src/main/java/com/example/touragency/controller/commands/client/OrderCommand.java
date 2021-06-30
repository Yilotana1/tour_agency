package com.example.touragency.controller.commands.client;

import com.example.touragency.constants.Messages;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Invoke applyForOrder method from service that implements tour order process
 */
public class OrderCommand implements Command {

    public final static Logger log = Logger.getLogger(OrderCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");
        OrderService orderService = ServiceFactory.getInstance().createOrderService();
        try {
            orderService.applyForOrder(
                    Integer.parseInt(request.getParameter("tourId")),
                    (String) request.getSession().getAttribute("login"));

        } catch (ServiceException e) {
            log.error(e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/order_confirm.jsp").forward(request, response);
            return;
        }


        request.setAttribute("message", Messages.ORDER_IS_OPENED);
        request.getRequestDispatcher("/order_confirm.jsp").forward(request, response);
        log.debug("forward to /order_confirm.jsp");
    }
}
