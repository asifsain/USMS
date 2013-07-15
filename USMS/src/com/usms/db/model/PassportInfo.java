package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the passport_info database table.
 * 
 */
@Entity
@Table(name="passport_info")
public class PassportInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DT")
	private Date issueDt;

	@Column(name="PP_HOLDER_TYPE")
	private String ppHolderType;

	@Column(name="PP_NO")
	private String ppNo;

	@Column(name="SOFT_FILE_PATH")
	private String softFilePath;

	//bi-directional many-to-one association to EmpFamilyInfo
	@OneToOne
//	@JoinColumn(name="EMP_FAMILY_INFO_ID", referencedColumnName="ID")
	@JoinColumn(name="EMP_FAMILY_INFO_ID")  
	private EmpFamilyInfo empFamilyInfo;

	//bi-directional many-to-one association to EmpInfo
	@OneToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	public PassportInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPpHolderType() {
		return this.ppHolderType;
	}

	public void setPpHolderType(String ppHolderType) {
		this.ppHolderType = ppHolderType;
	}

	public String getPpNo() {
		return this.ppNo;
	}

	public void setPpNo(String ppNo) {
		this.ppNo = ppNo;
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