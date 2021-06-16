package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.DiscountDao;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.exceptions.ServiceException;
import com.example.touragency.model.service.DiscountService;

import java.sql.SQLException;

public class DiscountServiceImpl implements DiscountService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public int getPercent() throws ServiceException {
        try(DiscountDao discountDao = daoFactory.createDiscountDao()){
            return discountDao.findById(1).getPercent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getMaxPercent() throws ServiceException {
        try(DiscountDao discountDao = daoFactory.createDiscountDao()){
            return discountDao.findById(1).getMaxPercent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public void changePercent(int percent) throws ServiceException {
        try(DiscountDao discountDao = daoFactory.createDiscountDao()){
            Discount discount = discountDao.findById(1);
            if (percent > discount.getMaxPercent()) throw new ServiceException("percent is more than maxPercent");

            discount.setPercent(percent);
            discountDao.update(discount);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeMaxPercent(int maxPercent) throws ServiceException {
        try(DiscountDao discountDao = daoFactory.createDiscountDao()){
            Discount discount = discountDao.findById(1);
            if (maxPercent < discount.getPercent()) throw new ServiceException("percent is more than maxPercent");
            discount.setMaxPercent(maxPercent);
            discountDao.update(discount);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}