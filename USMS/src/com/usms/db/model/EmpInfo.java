package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.annotations.Property;

import sun.security.krb5.internal.PAData.SaltAndParams;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the emp_info database table.
 * 
 */
@Entity
@Table(name="emp_info")
public class EmpInfo implements Serializable {  
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMP_NO")
	private String empNo;  
      
	@Column(name="FIRST_NAME")   
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="OFF_EMAIL")
	private String offEmail;

	@Column(name="OTH_EMAIL_1")
	private String othEmail1;

	@Column(name="OTH_EMAIL_2")
	private String othEmail2;
    
	//only for to show the total Arjustment in a month
	@Transient
	private float totalAdj;  
	
	@Transient
	private boolean flag=true;
	
	@Transient 
	private String salayStatus="Pending";
	
	@Transient
	private float tatalPayable;
	//bi-directional many-to-one association to AddressInfo
	@OneToMany(mappedBy="empInfo", cascade=CascadeType.ALL)  
	private List<AddressInfo> addressInfos;

	//bi-directional many-to-one association to EIdInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)
	private EIdInfo EIdInfos;

	//bi-directional many-to-one association to EmpAdjTrx
	@OneToMany(mappedBy="empInfo", cascade=CascadeType.ALL)
	private List<EmpAdjTrx> empAdjTrxs;

	//bi-directional many-to-one association to EmpBankInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)
	//@OneToOne(mappedBy="empInfo")
	private EmpBankInfo empBankInfos;

	//bi-directional many-to-one association to EmpEducationInfo
	@OneToMany(mappedBy="empInfo", cascade=CascadeType.ALL)
	private List<EmpEducationInfo> empEducationInfos;

	//bi-directional many-to-one association to EmpEmploymentInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)
	//@OneToOne(mappedBy="empInfo")
	private EmpEmploymentInfo empEmploymentInfos;

	//bi-directional many-to-one association to EmpFamilyInfo
	@OneToMany(mappedBy="empInfo", cascade=CascadeType.ALL)
	private List<EmpFamilyInfo> empFamilyInfos;

	//bi-directional many-to-one association to EmpLaborInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)
	//@OneToOne(mappedBy="empInfo")
	private EmpLaborInfo empLaborInfos;

	//bi-directional many-to-one association to EmpSalTrx
	@OneToMany(mappedBy="empInfo", cascade=CascadeType.ALL)
	private List<EmpSalTrx> empSalTrxs;

	//bi-directional many-to-one association to EmpSalaryInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)          
	//@OneToOne(mappedBy="empInfo")
	private EmpSalaryInfo empSalaryInfos;

	//bi-directional many-to-one association to MiInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)
	private MiInfo miInfos;

	//bi-directional many-to-one association to PassportInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)
	private PassportInfo passportInfos;

	//bi-directional many-to-one association to VisaInfo
	@OneToOne(mappedBy="empInfo", cascade=CascadeType.ALL)
	private VisaInfo visaInfos;
	
	
	//bi-directional many-to-one association to hrdocuments( added by TARIQ)
	@OneToMany(mappedBy="empInfo", cascade=CascadeType.ALL)
	private List <HrDocuments> hrDocuments;

	public EmpInfo() {
		
		
	}


	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOffEmail() {
		return this.offEmail;
	}

	public void setOffEmail(String offEmail) {
		this.offEmail = offEmail;
	}

	public String getOthEmail1() {
		return this.othEmail1;
	}

	public void setOthEmail1(String othEmail1) {
		this.othEmail1 = othEmail1;
	}

	public String getOthEmail2() {
		return this.othEmail2;
	}

	public void setOthEmail2(String othEmail2) {
		this.othEmail2 = othEmail2;
	}

	public List<AddressInfo> getAddressInfos() {
		return this.addressInfos;
	}

	public void setAddressInfos(List<AddressInfo> addressInfos) {
		this.addressInfos = addressInfos;
	}

	public EIdInfo getEIdInfos() {
		return this.EIdInfos;
	}

	public void setEIdInfos(EIdInfo EIdInfos) {
		this.EIdInfos = EIdInfos;
	}

	public List<EmpAdjTrx> getEmpAdjTrxs() {
		return this.empAdjTrxs;
	}

	public void setEmpAdjTrxs(List<EmpAdjTrx> empAdjTrxs) {
		this.empAdjTrxs = empAdjTrxs;
	}

	public EmpBankInfo getEmpBankInfos() {
		return this.empBankInfos;
	}

	public void setEmpBankInfos(EmpBankInfo empBankInfos) {
		this.empBankInfos = empBankInfos;
	}

	public List<EmpEducationInfo> getEmpEducationInfos() {
		return this.empEducationInfos;
	}

	public void setEmpEducationInfos(List<EmpEducationInfo> empEducationInfos) {
		this.empEducationInfos = empEducationInfos;
	}

	public EmpEmploymentInfo getEmpEmploymentInfos() {
		return this.empEmploymentInfos;
	}

	public void setEmpEmploymentInfos(EmpEmploymentInfo empEmploymentInfos) {
		this.empEmploymentInfos = empEmploymentInfos;
	}

	public List<EmpFamilyInfo> getEmpFamilyInfos() {
		return this.empFamilyInfos;
	}

	public void setEmpFamilyInfos(List<EmpFamilyInfo> empFamilyInfos) {
		this.empFamilyInfos = empFamilyInfos;
	}

	public EmpLaborInfo getEmpLaborInfos() {
		return this.empLaborInfos;
	}

	public void setEmpLaborInfos(EmpLaborInfo empLaborInfos) {
		this.empLaborInfos = empLaborInfos;
	}

	public List<EmpSalTrx> getEmpSalTrxs() {
		return this.empSalTrxs;
	}

	public void setEmpSalTrxs(List<EmpSalTrx> empSalTrxs) {
		this.empSalTrxs = empSalTrxs;
	}

	public EmpSalaryInfo getEmpSalaryInfos() {
		return this.empSalaryInfos;
	}

	public void setEmpSalaryInfos(EmpSalaryInfo empSalaryInfos) {
		this.empSalaryInfos = empSalaryInfos;
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

	
	
	public float getTotalAdj() {
		return totalAdj;
	}

	public void setTotalAdj(float totalAdj) {
		this.totalAdj = totalAdj;
	}
    
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
    
	
	
	public float getTatalPayable() {
		return tatalPayable;
	}

	public void setTatalPayable(float tatalPayable) {
		this.tatalPayable = tatalPayable;
	}
    
	public String getSalayStatus() {
		return salayStatus;
	}

	public void setSalayStatus(String salayStatus) {
		this.salayStatus = salayStatus;
	}
    
	
	public List<HrDocuments> getHrDocuments() {
		return hrDocuments;
	}

	public void setHrDocuments(List<HrDocuments> hrDocuments) {
		this.hrDocuments = hrDocuments;
	}

	@Transient
	public void getTotalAdj(int year , int month,List<EmpAdjTrx> list,float total)
	   {
		float totalAdj=0;
		for(EmpAdjTrx empAdj:list)
		 {
			if(empAdj.getAdjDt().getMonth()==month && (empAdj.getAdjDt().getYear()+1900)==year )
			 {
				if(empAdj.getAmtAdjFlag().equalsIgnoreCase("C"))
				   totalAdj=totalAdj+empAdj.getAmt();
				else
				   totalAdj=totalAdj-empAdj.getAmt();
			 }
			
			
		 }
		 this.totalAdj=totalAdj;
		 this.tatalPayable=total+this.totalAdj;
		 
	   }

}