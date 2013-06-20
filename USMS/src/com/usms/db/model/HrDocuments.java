package com.usms.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EMP_HR_DOCUMENTS")
public class HrDocuments implements Serializable {
	private static final long serialVersionUID = 1L;
  
	@Id
	@Column(name="ID")
	private int id;
	  
	@Column(name="DOCUMENT_TYPE")
	private String docType;
	
	@Column(name="GENERATION_MONTH")
	private String month;
	
	@Column(name="GENERATION_YEAR")
	private int year;
	
	@Temporal(TemporalType.DATE)
	@Column(name="GENERATION_DATE")
	private Date date;
	
	@Column(name="SOFT_FILE_PATH")
	private String softFilePath;
	
	@ManyToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSoftFilePath() {
		return softFilePath;
	}

	public void setSoftFilePath(String softFilePath) {
		this.softFilePath = softFilePath;
	}

	public EmpInfo getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}
	
	
	

}
