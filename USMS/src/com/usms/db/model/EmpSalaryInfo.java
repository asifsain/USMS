package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the emp_salary_info database table.
 * 
 */
@Entity
@Table(name="emp_salary_info")
public class EmpSalaryInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float basic;

	private float housing;

	@Column(name="OTHER_ALLOW")
	private float otherAllow;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DT")
	private Date startDt;

	@Column(name="STATUS_2")
	private String status2;

	private float transport;
	
	private float total;

	//bi-directional many-to-one association to EmpInfo
	@OneToOne
	@JoinColumn(name="EMP_INFO_EMP_NO", referencedColumnName="EMP_NO")
	private EmpInfo empInfo;

	public EmpSalaryInfo() {    
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBasic() {
		return this.basic;
	}

	public void setBasic(float basic) {
		this.basic = basic;
	}

	public float getHousing() {
		return this.housing;
	}

	public void setHousing(float housing) {
		this.housing = housing;
	}

	public float getOtherAllow() {
		return this.otherAllow;
	}

	public void setOtherAllow(float otherAllow) {
		this.otherAllow = otherAllow;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public float getTransport() {
		return this.transport;
	}

	public void setTransport(float transport) {
		this.transport = transport;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}