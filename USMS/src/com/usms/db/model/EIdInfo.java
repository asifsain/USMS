package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the e_id_info database table.
 * 
 */
@Entity
@Table(name="e_id_info")
public class EIdInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="E_ID_HOLDER_TYPE")
	private String eIdHolderType;

	@Column(name="E_ID_NO")
	private String eIdNo;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DT")
	private Date issueDt;

	@Column(name="SOFT_FILE_PATH")
	private String softFilePath;

	//bi-directional many-to-one association to EmpFamilyInfo
	@OneToOne
	@JoinColumn(name="EMP_FAMILY_INFO_ID")
	private EmpFamilyInfo empFamilyInfo;

	//bi-directional many-to-one association to EmpInfo
	@OneToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	public EIdInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEIdHolderType() {
		return this.eIdHolderType;
	}

	public void setEIdHolderType(String eIdHolderType) {
		this.eIdHolderType = eIdHolderType;
	}

	

	public String geteIdNo() {
		return eIdNo;
	}

	public void seteIdNo(String eIdNo) {
		this.eIdNo = eIdNo;
	}

	public Date getExpiryDt() {
		return this.expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getIssueDt() {
		return this.issueDt;
	}

	public void setIssueDt(Date issueDt) {
		this.issueDt = issueDt;
	}

	public String getSoftFilePath() {
		return this.softFilePath;
	}

	public void setSoftFilePath(String softFilePath) {
		this.softFilePath = softFilePath;
	}

	public EmpFamilyInfo getEmpFamilyInfo() {
		return this.empFamilyInfo;
	}

	public void setEmpFamilyInfo(EmpFamilyInfo empFamilyInfo) {
		this.empFamilyInfo = empFamilyInfo;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

}