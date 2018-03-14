package com.ekfans.base.product.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.ekfans.basic.hibernate.dao.GenericDao;

/**
 * 商品模板扩展字段所属分类DAO接口的实现
 * 
 * @Description:
 * @Copyright: Copyright (c) 2014
 * @Company: ekfans
 * @author Lgy
 * @date 2014-1-6
 * @version 1.0
 */
@Repository
public class TemplateFieldsCategoryDao extends GenericDao implements ITemplateFieldsCategoryDao {
	public TemplateFieldsCategoryDao() throws HibernateException {
		super();
		this.beanClass = "com.ekfans.base.product.model.TemplateFieldsCategory";
	}
}