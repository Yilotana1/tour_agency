package com.example.touragency.controller.commands.utils;

import com.example.touragency.controller.commands.LoginCommand;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.entity.Order;
import com.example.touragency.model.entity.enums.OrderStatus;
import com.example.touragency.model.service.OrderService;
import com.example.touragency.model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag handler that counts opened orders number to show on manager profile page
 */
public class GetOpenedOrdersTagHandler extends TagSupport {
    public final static Logger log = Logger.getLogger(LoginCommand.class);


    public int doStartTag(){
        log.debug("Tag started");
        JspWriter out = pageContext.getOut();
        OrderService orderService = ServiceFactory.getInstance().createOrderService();
        try {
            long count = orderService.getOpenedCount();
            out.print(count);
        } catch (ServiceException | IOException e) {
            log.error(e.getMessage());
        }

        return SKIP_BODY;
    }
}

