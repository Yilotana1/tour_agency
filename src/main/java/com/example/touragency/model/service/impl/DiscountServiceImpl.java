package com.example.touragency.model.service.impl;

import com.example.touragency.model.dao.DiscountDao;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.service.DiscountService;

import java.sql.SQLException;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    DaoFactory daoFactory = DaoFactory.getInstance();


    public Discount getDiscount() {
        try (DiscountDao discountDao = daoFactory.createDiscountDao()) {
            return discountDao.findById(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Discount getById(int id) {
        return null;
    }

    @Override
    public List<Discount> getAll() {
        return null;
    }


    public void update(int percentStep, int maxPercent) {
        try (DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao()) {
            Discount discount = Discount.createDiscount(percentStep, maxPercent);
            discountDao.update(discount);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Discount discount) {
        try (DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao()) {
            discountDao.update(discount);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    public int getCount() {
        return 0;
    }
}
