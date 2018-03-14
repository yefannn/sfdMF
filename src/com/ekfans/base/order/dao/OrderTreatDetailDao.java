package com.ekfans.base.order.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.ekfans.basic.hibernate.dao.GenericDao;

/**
 * 订单处理日志DAO接口的实现
 * 
 * @Description:
 * @Copyright: Copyright (c) 2014
 * @Company: ekfans
 * @author Lgy
 * @date 2014-1-6
 * @version 1.0
 */
@Repository
public class OrderTreatDetailDao extends GenericDao implements IOrderTreatDetailDao {
	public OrderTreatDetailDao() throws HibernateException {
		super();
		this.beanClass = "com.ekfans.base.order.model.OrderTreatDetail";
	}
}