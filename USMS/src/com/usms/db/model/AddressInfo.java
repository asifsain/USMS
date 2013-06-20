package com.usms.db.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address_info database table.
 * 
 */
@Entity
@Table(name="address_info")
public class AddressInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="ADDR_1")
	private String addr1;

	@Column(name="ADDR_2")
	private String addr2;

	@Column(name="ADDR_TYPE")
	private String addrType;

	private String city;

	@Column(name="CONT_PRSN_F_NAME")
	private String contPrsnFName;

	@Column(name="CONT_PRSN_L_NAME")
	private String contPrsnLName;

	private String country;

	@Column(name="MOB_NO_1")
	private String mobNo1;

	@Column(name="MOB_NO_2")
	private String mobNo2;

	@Column(name="POSTAL_CODE")
	private String postalCode;

	private String rel;

	private String state;

	@Column(name="TEL_NO")
	private String telNo;

	//bi-directional many-to-one association to EmpInfo
	@ManyToOne
	@JoinColumn(name="EMP_INFO_EMP_NO")
	private EmpInfo empInfo;
	
	public AddressInfo() {
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddr1() {
		return this.addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return this.addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddrType() {
		return this.addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContPrsnFName() {
		return this.contPrsnFName;
	}

	public void setContPrsnFName(String contPrsnFName) {
		this.contPrsnFName = contPrsnFName;
	}

	public String getContPrsnLName() {
		return this.contPrsnLName;
	}

	public void setContPrsnLName(String contPrsnLName) {
		this.contPrsnLName = contPrsnLName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public EmpInfo getEmpInfo() {
		return this.empInfo;
	}

	public void setEmpInfoEmpNo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

	public String getMobNo1() {
		return this.mobNo1;
	}

	public void setMobNo1(String mobNo1) {
		this.mobNo1 = mobNo1;
	}

	public String getMobNo2() {
		return this.mobNo2;
	}

	public void setMobNo2(String mobNo2) {
		this.mobNo2 = mobNo2;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRel() {
		return this.rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

}