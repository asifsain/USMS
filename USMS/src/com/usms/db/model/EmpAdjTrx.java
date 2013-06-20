package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the emp_adj_trx database table.
 * 
 */
@Entity
@Table(name="emp_adj_trx")
public class EmpAdjTrx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="ADJ_DT")
	private Date adjDt;

	@Column(name="ADJ_MONTH")
	private String adjMonth;

	@Column(name="ADJ_TYPE")
	private String adjType;
	
	@Column(name="AMT")
	private float amt;
    
	@Column(name="AMT_ADJ_FLAG")
	private String amtAdjFlag;
	
	@Column(name="APPR_BY")
	private String apprBy;

	@Column(name="DESC_2")
	private String desc2;

	@Column(name="SOFT_FILE_PATH")
	private String softFilePath;

	//bi-directional many-to-one association to EmpInfo
	@ManyToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	public EmpAdjTrx() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAdjDt() {
		return this.adjDt;
	}

	public void setAdjDt(Date adjDt) {
		this.adjDt = adjDt;
	}

	public String getAdjMonth() {
		return this.adjMonth;
	}

	public void setAdjMonth(String adjMonth) {
		this.adjMonth = adjMonth;
	}

	public String getAdjType() {
		return this.adjType;
	}

	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}

	public float getAmt() {
		return this.amt;
	}

	public void setAmt(float amt) {
		this.amt = amt;
	}

	public String getApprBy() {
		return this.apprBy;
	}

	public void setApprBy(String apprBy) {
		this.apprBy = apprBy;
	}

	public String getDesc2() {
		return this.desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getSoftFilePath() {
		return this.softFilePath;
	}

	public void setSoftFilePath(String softFilePath) {
		this.softFilePath = softFilePath;
	}

	public EmpInfo getEmpInfo() {	
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

	public String getAmtAdjFlag() {
		return amtAdjFlag;
	}

	public void setAmtAdjFlag(String amtAdjFlag) {
		this.amtAdjFlag = amtAdjFlag;
	}
    
}