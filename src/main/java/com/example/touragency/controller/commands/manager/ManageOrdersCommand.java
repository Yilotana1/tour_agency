package com.example.touragency.controller.commands.manager;

import com.example.touragency.constants.Path;
import com.example.touragency.controller.commands.Command;
import com.example.touragency.controller.commands.LogOutCommand;
import com.example.touragency.controller.commands.Paginator;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.service.DiscountService;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.Service;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageOrdersCommand implements Command, Paginator.NextPageSupplier<Order> {

    public final static Logger log = Logger.getLogger(ManageOrdersCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command started executing");

        OrderService orderService = ServiceFactory.getInstance().createOrderService();

        try {
            fillDiscountForm(request);
            updateOrderFromRequest(request, orderService);
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            log.error(e.getMessage());
            request.getRequestDispatcher("/manager_manage_orders.jsp").forward(request, response);
            return;
        }

        try {
            new Paginator<>(request, orderService).makePagination(this);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            response.sendRedirect(request.getServletContext().getContextPath() + Path.ERROR_503);
            return;
        }

        request.setAttribute("path", request.getServletContext().getContextPath() + Path.MANAGER_MANAGE_ORDERS);
        request.getRequestDispatcher("/manager/manage_orders.jsp").forward(request, response);
        log.debug("Forward to manager/manage_orders.jsp");
    }


    protected void fillDiscountForm(HttpServletRequest request) throws ServiceException {
        DiscountService discountService = ServiceFactory.getInstance().createDiscountService();
        Discount discount = discountService.getDiscount().get();
        request.setAttribute("percentStep", discount.getPercent());
        request.setAttribute("maxPercent", discount.getMaxPercent());
    }

    ;


    protected void updateOrderFromRequest(HttpServletRequest request, OrderService orderService) throws ServiceException {
        String idS = request.getParameter("id");

        if (idS != null) {
            int id = Integer.parseInt(idS);
            OrderStatus status = OrderStatus.getById(Integer.parseInt(request.getParameter("status")));
            switch (status) {
                case OPENED:
                    orderService.setOpened(id);
                    break;
                case PAID:
                    orderService.confirmPaid(id);
                    break;
                case CANCELED:
                    orderService.cancel(id);
            }
        }
    }


    private List<Order> getSearchedOrders(String login, OrderService orderService) throws ServiceException {

        List<Order> orders = orderService.getByLogin(login);
        if (orders.size() > 0) {
            return orders;
        }

        return new ArrayList<>();
    }


    @Override
    public List<Order> getNextPageContent(HttpServletRequest request, int page, int maxPageSize, Service<Order> orderService) throws ServiceException {
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
