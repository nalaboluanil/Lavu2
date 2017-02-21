package com.la.lavu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.la.lavu.dao.EmployeeDAO;
import com.la.lavu.entity.Employee;
import com.la.lavu.service.EmployeeService;
@EnableScheduling
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDAO employeeDAO;
	
@Scheduled(cron="0 32 13 * * ?")
	public void execuate() {
		runService();
	}

	@Override
	public boolean runService() {
		boolean processed = false;
		try {
			this.saveEmployee();
		} catch (Exception e) {

		}
		processed = true;
		return processed;
	}

	@Override
	public boolean saveEmployee() {
		boolean processed = false;
		Employee employee = null;
		try {
			employee = new Employee();
			employee.setEname("Ashok");
			employee.setSal(1000);
			employee.setDesig("Software");
			this.employeeDAO.save(employee);
			processed = true;
		} catch (Exception e) {

		}
		return processed;
	}

}
