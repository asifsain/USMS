package com.usms.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employer_details")
public class EmployerDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Name")
	private String name;
	@Column(name="Employer_id")
	private String employerId;
	
	@Column(name="CBR_CODE")
	private String cbrCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployerId() {
		return employerId;
	}
	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}
	public String getCbrCode() {
		return cbrCode;
	}
	public void setCbrCode(String cbrCode) {
		this.cbrCode = cbrCode;
	}
	
	

}
