package com.example.touragency.model.service.impl;

import com.example.touragency.constants.Messages;
import com.example.touragency.controller.Servlet;
import com.example.touragency.exceptions.ServiceException;
import com.example.touragency.model.dao.DiscountDao;
import com.example.touragency.model.dao.Factory.DaoFactory;
import com.example.touragency.model.entity.Discount;
import com.example.touragency.model.service.DiscountService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DiscountServiceImpl implements DiscountService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public final static Logger log = Logger.getLogger(Servlet.class);

    public Optional<Discount> getDiscount() throws ServiceException {
        try (DiscountDao discountDao = daoFactory.createDiscountDao()) {
            return discountDao.findById(1);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public Optional<Discount> getById(int id) {
        return null;
    }

    @Override
    public List<Discount> getAll() {
        return null;
    }


    public void update(int percentStep, int maxPercent) throws ServiceException {
        try (DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao()) {
            Discount discount = Discount.createDiscount(percentStep, maxPercent);
            discountDao.update(discount);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public void update(Discount discount) throws ServiceException {
        try (DiscountDao discountDao = DaoFactory.getInstance().createDiscountDao()) {
            discountDao.update(discount);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException(Messages.UNDEFINED_EXCEPTION);
        }
    }

    @Override
    public List<Discount> getPage(int pageId, int pageSize) {
        return null;
    }


    //TODO make this method
    @Override
    public int add(Discount entity) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
