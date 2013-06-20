package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the emp_bank_info database table.
 * 
 */
@Entity
@Table(name="emp_bank_info")
public class EmpBankInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="ACC_DRMNT_DT")
	private Date accDrmntDt;

	@Column(name="ACC_HLDR_NAME")
	private String accHldrName;

	@Column(name="BANK_NAME")
	private String bankName;

	private String branch;

	@Column(name="CBR_CODE")
	private String cbrCode;

	@OneToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	@Column(name="IBAN_NO")
	private String ibanNo;

	@Column(name="STATUS_2")
	private String status2;

	public EmpBankInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAccDrmntDt() {
		return this.accDrmntDt;
	}

	public void setAccDrmntDt(Date accDrmntDt) {
		this.accDrmntDt = accDrmntDt;
	}

	public String getAccHldrName() {
		return this.accHldrName;
	}

	public void setAccHldrName(String accHldrName) {
		this.accHldrName = accHldrName;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCbrCode() {
		return this.cbrCode;
	}

	public void setCbrCode(String cbrCode) {
		this.cbrCode = cbrCode;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

	public String getIbanNo() {
		return this.ibanNo;
	}

	public void setIbanNo(String ibanNo) {
		this.ibanNo = ibanNo;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

}