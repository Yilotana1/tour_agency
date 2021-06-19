package com.example.touragency.model.service;

import com.example.touragency.exceptions.*;

public interface DiscountService {
    int getPercent() throws ServiceException;

    int getMaxPercent() throws ServiceException;

    void changePercent(int percent) throws ServiceException;

    void changeMaxPercent(int maxPercent) throws ServiceException;

}
