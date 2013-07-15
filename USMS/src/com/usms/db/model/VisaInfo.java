package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the visa_info database table.
 * 
 */
@Entity
@Table(name="visa_info")
public class VisaInfo implements Serializable {
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

	@Column(name="SOFT_FILE_PATH")
	private String softFilePath;

	@Column(name="VISA_HOLDER_TYPE")
	private String visaHolderType;

	@Column(name="VISA_NO")
	private String visaNo;

	//bi-directional many-to-one association to EmpFamilyInfo
	@OneToOne
	@JoinColumn(name="EMP_FAMILY_INFO_ID")
	private EmpFamilyInfo empFamilyInfo;

	//bi-directional many-to-one association to EmpInfo
	@OneToOne
	@JoinColumn(name="EMP_INFO_EMP_NO") 
	private EmpInfo empInfo;

	public VisaInfo() {
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

	public String getSoftFilePath() {
		return this.softFilePath;
	}

	public void setSoftFilePath(String softFilePath) {
		this.softFilePath = softFilePath;
	}

	public String getVisaHolderType() {
		return this.visaHolderType;
	}

	public void setVisaHolderType(String visaHolderType) {
		this.visaHolderType = visaHolderType;
	}

	public String getVisaNo() {
		return this.visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
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