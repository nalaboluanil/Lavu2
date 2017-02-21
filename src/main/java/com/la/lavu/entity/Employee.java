package com.la.lavu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="emp")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name = "id")
	private Integer id;
	@Getter
	@Setter
	@Column(name="ename")
	private String ename;
	@Getter
	@Setter
	@Column(name="sal")
	private int sal;
	@Getter
	@Setter
	@Column(name="desig")
	private String desig;
}
