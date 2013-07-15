package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the emp_employment_info database table.
 * 
 */
@Entity
@Table(name="emp_employment_info")
public class EmpEmploymentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="CLNT_SITE_NAME")
	private String clntSiteName;

	@Temporal(TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="CONRT_LETTER_PATH")
	private String conrtLetterPath;

	@Temporal(TemporalType.DATE)
	@Column(name="JOINING_DT")
	private Date joiningDt;

	@Temporal(TemporalType.DATE)
	@Column(name="LST_WRK_DT")
	private Date lstWrkDt;

	@Column(name="NOTICE_PRD")
	private int noticePrd;

	@Column(name="OFF_LETTER_PATH")
	private String offLetterPath;

	@Temporal(TemporalType.DATE)
	@Column(name="PROBATION_PRD")
	private Date probationPrd;

	@Temporal(TemporalType.DATE)
	@Column(name="RESIGN_DT")
	private Date resignDt;

	@Column(name="STATUS_2")
	private String status2;
	  
	@Column(name="DESIGNATION")
	private String empDesignation;

	//bi-directional many-to-one association to EmpInfo
	@OneToOne
//	@JoinColumn(name="EMP_INFO_EMP_NO", referencedColumnName="EMP_NO")
	@JoinColumn(name="EMP_INFO_EMP_NO")  
	private EmpInfo empInfo;

	public EmpEmploymentInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClntSiteName() {
		return this.clntSiteName;
	}

	public void setClntSiteName(String clntSiteName) {
		this.clntSiteName = clntSiteName;
	}

	public Date getConfDt() {
		return this.confDt;
	}

	public void setConfDt(Date confDt) {
		this.confDt = confDt;
	}

	public String getConrtLetterPath() {
		return this.conrtLetterPath;
	}

	public void setConrtLetterPath(String conrtLetterPath) {
		this.conrtLetterPath = conrtLetterPath;
	}

	public Date getJoiningDt() {
		return this.joiningDt;
	}

	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}

	public Date getLstWrkDt() {
		return this.lstWrkDt;
	}

	public void setLstWrkDt(Date lstWrkDt) {
		this.lstWrkDt = lstWrkDt;
	}

	public int getNoticePrd() {
		return this.noticePrd;
	}

	public void setNoticePrd(int noticePrd) {
		this.noticePrd = noticePrd;
	}

	public String getOffLetterPath() {
		return this.offLetterPath;
	}

	public void setOffLetterPath(String offLetterPath) {
		this.offLetterPath = offLetterPath;
	}

	public Date getProbationPrd() {
		return this.probationPrd;
	}

	public void setProbationPrd(Date probationPrd) {
		this.probationPrd = probationPrd;
	}

	public Date getResignDt() {
		return this.resignDt;
	}

	public void setResignDt(Date resignDt) {
		this.resignDt = resignDt;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
    
}