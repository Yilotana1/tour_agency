package com.example.touragency.model.service;

import com.example.touragency.exceptions.*;
import com.example.touragency.model.entity.Discount;

public interface DiscountService extends Service<Discount>{
    int getPercent() throws ServiceException;

    int getMaxPercent() throws ServiceException;

    void changePercent(int percent) throws ServiceException;

    void changeMaxPercent(int maxPercent) throws ServiceException;

}
