package com.ekfans.base.order.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.ekfans.basic.hibernate.dao.GenericDao;

@Repository
public class OrderWfpDao extends GenericDao implements IOrderWfpDao{
    public OrderWfpDao() throws HibernateException {
        super();
        this.beanClass = "com.ekfans.base.order.model.OrderWfp";
    }
}
