package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.DiscountDao;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.service.DiscountService;
import com.example.touragency.exceptions.*;

import java.sql.SQLException;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public int getPercent()  {
        try(DiscountDao discountDao = daoFactory.createDiscountDao()){
            return discountDao.findById(1).getPercent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getMaxPercent() {
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

    @Override
    public Discount getById(int id) {
        return null;
    }

    @Override
    public List<Discount> getAll() {
        return null;
    }

    @Override
    public void update() throws ServiceException {

    }

    @Override
    public void update(Discount entity) {

    }

    @Override
    public List<Discount> getPage(int pageId, int pageSize) {
        return null;
    }

    @Override
    public int add(Discount entity) {
        return 0;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public int getCount() {
        return 0;
    }
}
