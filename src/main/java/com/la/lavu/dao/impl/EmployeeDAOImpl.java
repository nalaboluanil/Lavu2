package com.la.lavu.dao.impl;

import org.hibernate.SessionFactory;

import com.la.lavu.dao.EmployeeDAO;
import com.la.lavu.entity.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(Employee employee) {
		this.sessionFactory.openSession().save(employee);
		
	}

}
