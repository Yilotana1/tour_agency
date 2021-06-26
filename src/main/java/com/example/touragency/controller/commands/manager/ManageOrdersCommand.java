package com.example.touragency.controller.commands.manager;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.Paginator;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.Service;
import com.example.touragency.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ManageOrdersCommand implements Command, Paginator.NextPageSupplier<Order> {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderService orderService = ServiceFactory.getInstance().createOrderService();

        updateOrderFromRequest(request, orderService);

        new Paginator<>(request, orderService).makePagination(this);
        request.setAttribute("path", request.getServletContext().getContextPath() + Path.MANAGER_MANAGER_ORDERS);
        request.getRequestDispatcher("/manager/manage_orders.jsp").forward(request, response);
    }






    protected void updateOrderFromRequest(HttpServletRequest request, OrderService orderService) {
        String id = request.getParameter("id");

        if (id != null) {

            OrderStatus status = OrderStatus.getById(Integer.parseInt(request.getParameter("status")));
            orderService.changeStatus(Integer.parseInt(id), status);
        }
    }


    private List<Order> getSearchedOrders(String login, OrderService orderService) {
        try {
            List<Order> orders = orderService.getByLogin(login);
            if (orders.size() > 0) {
                return orders;
            }
        } catch (NoSuchElementException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public List<Order> getNextPageContent(HttpServletRequest request, int page, int maxPageSize, Service<Order> orderService) {
        if (request.getParameter("search") != null) {
            String login = request.getParameter("search");
            return getSearchedOrders(login, (OrderService) orderService);
        }

        String orderBy = request.getParameter("order");
        if (orderBy == null) orderBy = "";
        request.setAttribute("order", orderBy);

        List<Order> orders = orderService.getPage(page, maxPageSize);
        switch (orderBy) {
            case "opened":
                orders = ((OrderService) orderService).getPageOpenedFirst(page, maxPageSize);
                break;
            case "paid":
                orders = ((OrderService) orderService).getPagePaidFirst(page, maxPageSize);
                break;
        }
        return orders;
    }
}
