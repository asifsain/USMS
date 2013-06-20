package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the emp_sal_trx database table.
 * 
 */
@Entity
@Table(name="emp_sal_trx")
public class EmpSalTrx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="ADJ_AMT")
	private float adjAmt;

	@Column(name="EMP_NAME")
	private String empName;

	@Temporal(TemporalType.DATE)
	@Column(name="FROM_DT")
	private Date fromDt;

	@Column(name="SAL_AMT")
	private float salAmt;

	@Column(name="SAL_TRX_MONTH")
	private String salTrxMonth;

	@Temporal(TemporalType.DATE)
	@Column(name="TO_DATE")
	private Date toDate;

	@Column(name="TOTAL_AMT")
	private float totalAmt;
	
	@Column(name="SOFT_FILE_PATH")
	private String softFilePath;

	//bi-directional many-to-one association to EmpInfo
	@ManyToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	public EmpSalTrx() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAdjAmt() {
		return this.adjAmt;
	}

	public void setAdjAmt(float adjAmt) {
		this.adjAmt = adjAmt;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getFromDt() {
		return this.fromDt;
	}

	public void setFromDt(Date fromDt) {
		this.fromDt = fromDt;
	}

	public float getSalAmt() {
		return this.salAmt;
	}

	public void setSalAmt(float salAmt) {
		this.salAmt = salAmt;
	}

	public String getSalTrxMonth() {
		return this.salTrxMonth;
	}

	public void setSalTrxMonth(String salTrxMonth) {
		this.salTrxMonth = salTrxMonth;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public float getTotalAmt() {
		return this.totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

	public String getSoftFilePath() {
		return softFilePath;
	}

	public void setSoftFilePath(String softFilePath) {
		this.softFilePath = softFilePath;
	}
  
}