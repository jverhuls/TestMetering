package com.capgemini.test.dao;

import com.capgemini.test.domain.Customer;

public interface ICustomerDAO {
	public void save(Customer customer);
	public Customer get(String userid, String password);
}
