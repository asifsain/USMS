package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the emp_family_info database table.
 * 
 */
@Entity
@Table(name="emp_family_info")
public class EmpFamilyInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DT")
	private Date birthDt;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	private String rel;

	@Temporal(TemporalType.DATE)
	@Column(name="REL_END_DT")
	private Date relEndDt;

	@Column(name="REL_STATUS")
	private String relStatus;

	//bi-directional many-to-one association to EIdInfo
	@OneToOne(mappedBy="empFamilyInfo" , cascade=CascadeType.ALL)
	private EIdInfo EIdInfos;

	//bi-directional many-to-one association to EmpInfo
	@ManyToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;

	//bi-directional many-to-one association to MiInfo
	@OneToOne(mappedBy="empFamilyInfo" , cascade=CascadeType.ALL)
	private MiInfo miInfos;

	//bi-directional many-to-one association to PassportInfo  
	@OneToOne(mappedBy="empFamilyInfo" , cascade=CascadeType.ALL)
	private PassportInfo passportInfos; 

	//bi-directional many-to-one association to VisaInfo
	@OneToOne(mappedBy="empFamilyInfo" , cascade=CascadeType.ALL)
	private VisaInfo visaInfos;

	public EmpFamilyInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthDt() {
		return this.birthDt;
	}

	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRel() {
		return this.rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public Date getRelEndDt() {
		return this.relEndDt;
	}

	public void setRelEndDt(Date relEndDt) {
		this.relEndDt = relEndDt;
	}

	public String getRelStatus() {
		return this.relStatus;
	}

	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}

	public EIdInfo getEIdInfos() {
		return this.EIdInfos;
	}

	public void setEIdInfos(EIdInfo EIdInfos) {
		this.EIdInfos = EIdInfos;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

	public MiInfo getMiInfos() {
		return this.miInfos;
	}

	public void setMiInfos(MiInfo miInfos) {
		this.miInfos = miInfos;
	}

	public PassportInfo getPassportInfos() {
		return this.passportInfos;
	}

	public void setPassportInfos(PassportInfo passportInfos) {
		this.passportInfos = passportInfos;
	}

	public VisaInfo getVisaInfos() {
		return this.visaInfos;
	}

	public void setVisaInfos(VisaInfo visaInfos) {
		this.visaInfos = visaInfos;
	}

}