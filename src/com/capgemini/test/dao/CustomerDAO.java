package com.capgemini.test.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.capgemini.test.domain.Customer;
import com.capgemini.test.util.Util;


@Service("customerDAO")
public class CustomerDAO implements ICustomerDAO {
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	
	public void save(Customer customer){
		hibernateTemplate.save(customer);
	}
	
	@SuppressWarnings("unchecked")
	public Customer get(String userid, String password){
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.ilike("userid", userid));
		criteria.add(Restrictions.ilike("password", Util.encrypt(password)));
		List<Customer> customers = hibernateTemplate.findByCriteria(criteria);
		return (customers == null || customers.size() == 0)?null:customers.get(0);		
	}
}
