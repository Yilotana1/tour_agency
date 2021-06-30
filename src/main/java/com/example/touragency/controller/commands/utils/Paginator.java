package com.example.touragency.controller.commands.utils;

import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class Paginator<Item> {


    public static final String ORDER = "order";

    public static final String PAGE_ITEMS = "items";

    public static final String PAGE_COUNT = "page_count";

    public static final String NEXT_PAGE = "next_page";

    public static final String PREVIOUS_PAGE = "previous_page";

    public static final String PAGE = "page";

    private final HttpServletRequest request;

    private final Service<Item> service;

    private int maxPageSize = 3;


    public Paginator(HttpServletRequest request, Service<Item> service, int maxPageSize) {
        this.request = request;
        this.service = service;
        this.maxPageSize = maxPageSize;
    }

    public Paginator(HttpServletRequest request, Service<Item> service) {
        this.request = request;
        this.service = service;
    }



    public void makePagination(NextPageSupplier<Item> supplier) throws ServiceException {
        int itemsCount = service.getCount();
        int pageCount = getPageCount(itemsCount, maxPageSize);

        int page = addNextPageIdToRequest(request, pageCount);

        List<Item> items = supplier.getNextPageContent(request, page, maxPageSize, service);

        request.setAttribute(PAGE_ITEMS, items);
        request.setAttribute(PAGE_COUNT, pageCount);
    }


    public int getPageCount(int usersNumber, int pageSize) {
        return (int) Math.ceil((double) usersNumber / pageSize);
    }



    public int addNextPageIdToRequest(HttpServletRequest request, int pagesNumber) {
        int page = 1;

        if (request.getParameter(NEXT_PAGE) != null
                && Integer.parseInt(request.getParameter(NEXT_PAGE)) < pagesNumber) {

            page = Integer.parseInt(request.getParameter(NEXT_PAGE)) + 1;
        }

        if (request.getParameter(PREVIOUS_PAGE) != null
                && Integer.parseInt(request.getParameter(PREVIOUS_PAGE)) > 1) {

            page = Integer.parseInt(request.getParameter(PREVIOUS_PAGE)) - 1;
        }

        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        request.setAttribute(PAGE, page);

        return page;
    }

// This method defines previous page order parameters and retrieve according data to show on the next page
    public interface NextPageSupplier<Item> {
        List<Item> getNextPageContent(HttpServletRequest request, int page, int maxPageSize, Service<Item> service) throws ServiceException;
    }
}
