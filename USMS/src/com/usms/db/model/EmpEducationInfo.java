package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the emp_education_info database table.
 * 
 */
@Entity
@Table(name="emp_education_info")
public class EmpEducationInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="DEGREE_NAME")
	private String degreeName;

	@Column(name="UNIV_NAME")
	private String univName;

	@Temporal(TemporalType.DATE)
	@Column(name="YEAR_OF_PASSING")
	private Date yearOfPassing;

	//bi-directional many-to-one association to EmpInfo
	@ManyToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	public EmpEducationInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDegreeName() {
		return this.degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getUnivName() {
		return this.univName;
	}

	public void setUnivName(String univName) {
		this.univName = univName;
	}

	public Date getYearOfPassing() {
		return this.yearOfPassing;
	}

	public void setYearOfPassing(Date yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

}