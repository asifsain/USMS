package com.usms.view.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usms.application.objects.UsmsFile;
import com.usms.db.model.AddressInfo;
import com.usms.db.model.EIdInfo;
import com.usms.db.model.EmpBankInfo;
import com.usms.db.model.EmpEducationInfo;
import com.usms.db.model.EmpEmploymentInfo;
import com.usms.db.model.EmpFamilyInfo;
import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmpLaborInfo;
import com.usms.db.model.EmpSalaryInfo;
import com.usms.db.model.MiInfo;
import com.usms.db.model.PassportInfo;
import com.usms.db.model.VisaInfo;

public class EmployeeViewModel {
    
	private String empSearchInfo;
	
	public EmployeeViewModel(String mode) {
		// TODO Auto-generated constructor stub
		
		
		switch (mode) {
		case "register":
			empInfo = new EmpInfo();  

			permAddr = new AddressInfo();
			permAddr.setAddrType("PERMANENT");
			
			localAddr = new AddressInfo();
			localAddr.setAddrType("LOCAL");
			
			emgrAddr = new AddressInfo();
			emgrAddr.setAddrType("EMERGENCY");

			empBankInfo = new EmpBankInfo();
			empEduInfo = new EmpEducationInfo();
			empEmploymentInfo = new EmpEmploymentInfo();
			empSalInfo = new EmpSalaryInfo();

			passportInfo = new PassportInfo();
			miInfo = new MiInfo();
			eidInfo = new EIdInfo();
			laborInfo = new EmpLaborInfo();
			visaInfo = new VisaInfo();

			spouseInfo = new EmpFamilyInfo();
			spouseInfo.setRel("SPOUSE");

			PassportInfo spousePassport = new PassportInfo();
			spousePassport.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setPassportInfos(spousePassport);

			MiInfo spouseMiInfo = new MiInfo();
			spouseMiInfo.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setMiInfos(spouseMiInfo);

			EIdInfo spouseEidInfo = new EIdInfo();
			spouseEidInfo.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setEIdInfos(spouseEidInfo);

			VisaInfo spouseVisaInfo = new VisaInfo();
			spouseVisaInfo.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setVisaInfos(spouseVisaInfo);

			// ---------------------------------------------------------

			child1Info = new EmpFamilyInfo();
			child1Info.setRel("CHILD1");

			PassportInfo child1Passport = new PassportInfo();
			child1Passport.setEmpFamilyInfo(child1Info);
			child1Info.setPassportInfos(child1Passport);

			MiInfo child1MiInfo = new MiInfo();
			child1MiInfo.setEmpFamilyInfo(child1Info);
			child1Info.setMiInfos(child1MiInfo);

			EIdInfo child1EidInfo = new EIdInfo();
			child1EidInfo.setEmpFamilyInfo(child1Info);
			child1Info.setEIdInfos(child1EidInfo);

			VisaInfo child1VisaInfo = new VisaInfo();
			child1VisaInfo.setEmpFamilyInfo(child1Info);
			child1Info.setVisaInfos(child1VisaInfo);

			// ---------------------------------------------------------

			child2Info = new EmpFamilyInfo();
			child2Info.setRel("CHILD2");
			PassportInfo child2Passport = new PassportInfo();
			child2Passport.setEmpFamilyInfo(child2Info);
			child2Info.setPassportInfos(child2Passport);

			MiInfo child2MiInfo = new MiInfo();
			child2MiInfo.setEmpFamilyInfo(child2Info);
			child2Info.setMiInfos(child2MiInfo);

			EIdInfo child2EidInfo = new EIdInfo();
			child2EidInfo.setEmpFamilyInfo(child2Info);
			child1Info.setEIdInfos(child2EidInfo);

			VisaInfo child2VisaInfo = new VisaInfo();
			child2VisaInfo.setEmpFamilyInfo(child2Info);
			child2Info.setVisaInfos(child2VisaInfo);

			System.out.println("all sub objects generated");

			break;

		case "update":
			break;

		default:
			break;
		}
	}
    
	
	 
	
	public void consolidateEmployee() {
		// -------------- address ---------------------

		if (empInfo.getAddressInfos() == null)
			empInfo.setAddressInfos(new ArrayList<AddressInfo>());

		if (permAddr != null) {
			permAddr.setEmpInfoEmpNo(empInfo);
			empInfo.getAddressInfos().add(permAddr);
		}

		if (localAddr != null) {
			localAddr.setEmpInfoEmpNo(empInfo);
			empInfo.getAddressInfos().add(localAddr);
		}

		if (emgrAddr != null) {
			emgrAddr.setEmpInfoEmpNo(empInfo);
			empInfo.getAddressInfos().add(emgrAddr);
		}

		// --------------- family -------------

		if (empInfo.getEmpFamilyInfos() == null)
			empInfo.setEmpFamilyInfos(new ArrayList<EmpFamilyInfo>());

		if (spouseInfo != null && hasSpouse) {
			spouseInfo.setEmpInfo(empInfo);
			empInfo.getEmpFamilyInfos().add(spouseInfo);
		    
			
		}

		if (child1Info != null && hasChild1) {
			child1Info.setEmpInfo(empInfo);
			
			empInfo.getEmpFamilyInfos().add(child1Info);
		
		}

		if (child2Info != null && hasChild1 && hasChild2) {
			child2Info.setEmpInfo(empInfo);
			
			empInfo.getEmpFamilyInfos().add(child2Info);
			
		}

		/* -------------- Documents ----------------- */

		/* --- Passport --- */
		if (passportInfo != null) {

			passportInfo.setEmpInfo(empInfo);
			empInfo.setPassportInfos(passportInfo);

		}

		/* ---- Visa Information --- */
		if (visaInfo != null) {

			visaInfo.setEmpInfo(empInfo);
			empInfo.setVisaInfos(visaInfo);

		}

		/* ---- Labor Card --- */
		if (laborInfo != null) {

			laborInfo.setEmpInfo(empInfo);
			empInfo.setEmpLaborInfos(laborInfo);

		}

		/* ---- Medical Insurance --- */
		if (miInfo != null) {

			miInfo.setEmpInfo(empInfo);
			empInfo.setMiInfos(miInfo);

		}

		/* ---- Emirates ID --- */
		if (eidInfo != null) {

			eidInfo.setEmpInfo(empInfo);
			empInfo.setEIdInfos(eidInfo);

		}

		/* --------------- Mics -------------------- */

		/* ------ Bank ------- */
		if (empBankInfo != null) {

			empBankInfo.setEmpInfo(empInfo);
			empInfo.setEmpBankInfos(empBankInfo);

		}

		/* ------ Education ------- */
		if (empInfo.getEmpEducationInfos() == null)
			empInfo.setEmpEducationInfos(new ArrayList<EmpEducationInfo>());

		if (empEduInfo != null) {

			empEduInfo.setEmpInfo(empInfo);
			empInfo.getEmpEducationInfos().add(empEduInfo);

		}
		//

		/* ------ Employment ------- */
		if (empEmploymentInfo != null) {

			empEmploymentInfo.setEmpInfo(empInfo);
			empInfo.setEmpEmploymentInfos(empEmploymentInfo);

		}

		/* ------ Salary ------- */
		if (empSalInfo != null) {
			empSalInfo.setEmpInfo(empInfo);
			empInfo.setEmpSalaryInfos(empSalInfo);
		}
	
		

	}
	// -------------- searchInfo getter Setter---------------------
	
	public String getEmpSearchInfo() {
		return empSearchInfo;
	}

	public void setEmpSearchInfo(String empSearchInfo) {
		this.empSearchInfo = empSearchInfo;
	}

	public void splitEmployee()
	{
		
		// Address Info
		if (empInfo.getAddressInfos().size() > 0) 
		{
			for (AddressInfo empAddress : empInfo.getAddressInfos()) 
			{				
				if (empAddress.getAddrType().equalsIgnoreCase("PERMANENT"))
					permAddr = empAddress;
				
				if (empAddress.getAddrType().equalsIgnoreCase("LOCAL"))
					localAddr =  empAddress;
				
				if (empAddress.getAddrType().equalsIgnoreCase("EMERGENCY"))
					emgrAddr = empAddress;
			}
		} 
		else 
		{			
			permAddr = new AddressInfo();
			localAddr = new AddressInfo();
			emgrAddr = new AddressInfo();
		}
		
		// Employment Info
		if (empInfo.getEmpEducationInfos().size() > 0) 
		{
			for (EmpEducationInfo empeducation : empInfo.getEmpEducationInfos()) 
			{
				empEduInfo = empeducation;
			}
		} 
		else 
		{
			empEduInfo = new EmpEducationInfo();
		}
		
		// Employee Emirates ID
		if (empInfo.getEIdInfos() != null)
			eidInfo = empInfo.getEIdInfos();
		else
			eidInfo = new EIdInfo();
		
		// Employment Info
		if (empInfo.getEmpEmploymentInfos() != null)
			empEmploymentInfo = empInfo.getEmpEmploymentInfos();
		else
			empEmploymentInfo = new EmpEmploymentInfo();
		
		// Bank Info
		if (empInfo.getEmpBankInfos() != null)
			empBankInfo = empInfo.getEmpBankInfos();
		else
			empBankInfo = new EmpBankInfo();
		
		// Labour Info
		if (empInfo.getEmpLaborInfos() != null)
			laborInfo = empInfo.getEmpLaborInfos();
		else
			laborInfo = new EmpLaborInfo();
		
		// Employee Passport Info
		if (empInfo.getPassportInfos() != null)
			passportInfo = empInfo.getPassportInfos();
		else
			passportInfo = new PassportInfo();
		
		// Employee Visa Info
		if (empInfo.getVisaInfos() != null)
			visaInfo = empInfo.getVisaInfos();
		else
			visaInfo = new VisaInfo();
		
		// Employee Salary Info
		if (empInfo.getEmpSalaryInfos() != null)
			empSalInfo = empInfo.getEmpSalaryInfos();
		else
			empSalInfo = new EmpSalaryInfo();
		
		// Employee Medical Insurance Info
		if (empInfo.getMiInfos() != null)
			miInfo = empInfo.getMiInfos();
		else
			miInfo = new MiInfo();
		 
		// ------ FAMILY ----
		if(empInfo.getEmpFamilyInfos() != null)
		{
			if (empInfo.getEmpFamilyInfos().size() > 0) 
			{
				for (EmpFamilyInfo empFamily : empInfo.getEmpFamilyInfos()) 
				{
					if(empFamily == null)
						continue;
						
					if (empFamily.getRel().equalsIgnoreCase("SPOUSE"))
					  {
						spouseInfo = empFamily;
						hasSpouse=true;
					  }	
					if (empFamily.getRel().equalsIgnoreCase("CHILD1"))
					  {
						child1Info = empFamily;
						hasChild1=true;
					  }
					if (empFamily.getRel().equalsIgnoreCase("CHILD2"))
					  {
						child2Info = empFamily;
						hasChild2=true;
					  }
				}
				
			} 
			
		}
				
		
		if (spouseInfo == null){
			
			
			spouseInfo = new EmpFamilyInfo();
			spouseInfo.setRel("SPOUSE");
			
			PassportInfo spousePassport = new PassportInfo();
			spousePassport.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setPassportInfos(spousePassport);

			MiInfo spouseMiInfo = new MiInfo();
			spouseMiInfo.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setMiInfos(spouseMiInfo);

			EIdInfo spouseEidInfo = new EIdInfo();
			spouseEidInfo.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setEIdInfos(spouseEidInfo);

			VisaInfo spouseVisaInfo = new VisaInfo();
			spouseVisaInfo.setEmpFamilyInfo(spouseInfo);
			spouseInfo.setVisaInfos(spouseVisaInfo);
			
			
			
		
			
		}
		

		if (child1Info == null)
		{
			child1Info = new EmpFamilyInfo();
			child1Info.setRel("CHILD1");

			PassportInfo child1Passport = new PassportInfo();
			child1Passport.setEmpFamilyInfo(child1Info);
			child1Info.setPassportInfos(child1Passport);

			MiInfo child1MiInfo = new MiInfo();
			child1MiInfo.setEmpFamilyInfo(child1Info);
			child1Info.setMiInfos(child1MiInfo);

			EIdInfo child1EidInfo = new EIdInfo();
			child1EidInfo.setEmpFamilyInfo(child1Info);
			child1Info.setEIdInfos(child1EidInfo);

			VisaInfo child1VisaInfo = new VisaInfo();
			child1VisaInfo.setEmpFamilyInfo(child1Info);
			child1Info.setVisaInfos(child1VisaInfo);
		}
		
		if (child2Info == null)
		{
			child2Info = new EmpFamilyInfo();
			child2Info.setRel("CHILD2");
			PassportInfo child2Passport = new PassportInfo();
			child2Passport.setEmpFamilyInfo(child2Info);
			child2Info.setPassportInfos(child2Passport);

			MiInfo child2MiInfo = new MiInfo();
			child2MiInfo.setEmpFamilyInfo(child2Info);
			child2Info.setMiInfos(child2MiInfo);

			EIdInfo child2EidInfo = new EIdInfo();
			child2EidInfo.setEmpFamilyInfo(child2Info);
			child1Info.setEIdInfos(child2EidInfo);

			VisaInfo child2VisaInfo = new VisaInfo();
			child2VisaInfo.setEmpFamilyInfo(child2Info);
			child2Info.setVisaInfos(child2VisaInfo);
		}
		
		
	}
	
	// -------------------------- Emp Info ---------------

	private EmpInfo empInfo;

	public EmpInfo getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}

	// --------------------------- Bank Info ------------

	private EmpBankInfo empBankInfo;

	public EmpBankInfo getEmpBankInfo() {
		return empBankInfo;
	}

	public void setEmpBankInfo(EmpBankInfo empBankInfo) {
		this.empBankInfo = empBankInfo;
	}

	// --------------------------- Employment Info ------------

	private EmpEmploymentInfo empEmploymentInfo;

	public EmpEmploymentInfo getEmpEmploymentInfo() {
		return empEmploymentInfo;
	}

	public void setEmpEmploymentInfo(EmpEmploymentInfo empEmploymentInfo) {
		this.empEmploymentInfo = empEmploymentInfo;
	}

	// --------------------------- Salary Info ------------

	private EmpSalaryInfo empSalInfo;

	public EmpSalaryInfo getEmpSalInfo() {
		return empSalInfo;
	}

	public void setEmpSalInfo(EmpSalaryInfo empSalInfo) {
		this.empSalInfo = empSalInfo;
	}

	// --------------------------- Education Info ------------

	private EmpEducationInfo empEduInfo;

	public EmpEducationInfo getEmpEduInfo() {
		return empEduInfo;
	}

	public void setEmpEduInfo(EmpEducationInfo empEduInfo) {
		this.empEduInfo = empEduInfo;
	}

	// --------------------------- Passport Info ------------

	private PassportInfo passportInfo;

	public PassportInfo getPassportInfo() {
		return passportInfo;
	}

	public void setPassportInfo(PassportInfo passportInfo) {
		this.passportInfo = passportInfo;
	}

	// --------------------------- Visa Info ------------

	private VisaInfo visaInfo;

	public VisaInfo getVisaInfo() {
		return visaInfo;
	}

	public void setVisaInfo(VisaInfo visaInfo) {
		this.visaInfo = visaInfo;
	}

	// --------------------------- Labor Card Info ------------

	private EmpLaborInfo laborInfo;

	public EmpLaborInfo getLaborInfo() {
		return laborInfo;
	}

	public void setLaborInfo(EmpLaborInfo laborInfo) {
		this.laborInfo = laborInfo;
	}

	// --------------------------- E-ID Info ------------

	private EIdInfo eidInfo;

	public EIdInfo getEidInfo() {
		return eidInfo;
	}

	public void setEidInfo(EIdInfo eidInfo) {
		this.eidInfo = eidInfo;
	}

	// --------------------------- Medical Insurance Info ------------

	private MiInfo miInfo;

	public MiInfo getMiInfo() {
		return miInfo;
	}

	public void setMiInfo(MiInfo miInfo) {
		this.miInfo = miInfo;
	}

	// --------------------------- Permanent Address Info ------------

	private AddressInfo permAddr;

	public AddressInfo getPermAddr() {
		return permAddr;
	}

	public void setPermAddr(AddressInfo permAddr) {
		this.permAddr = permAddr;
	}

	// --------------------------- Local Address Info ------------

	private AddressInfo localAddr;

	public AddressInfo getLocalAddr() {
		return localAddr;
	}

	public void setLocalAddr(AddressInfo localAddr) {
		this.localAddr = localAddr;
	}

	// --------------------------- Emergency Address Info ------------

	private AddressInfo emgrAddr;

	public AddressInfo getEmgrAddr() {
		return emgrAddr;
	}

	public void setEmgrAddr(AddressInfo emgrAddr) {
		this.emgrAddr = emgrAddr;
	}

	// --------------------------- Spouse Info ------------

	private boolean hasSpouse ;

	private EmpFamilyInfo spouseInfo;

	public EmpFamilyInfo getSpouseInfo() {
		return spouseInfo;
	}

	public void setSpouseInfo(EmpFamilyInfo spouseInfo) {
		this.spouseInfo = spouseInfo;
	}

	public boolean isHasSpouse() {
		return hasSpouse;
	}

	public void setHasSpouse(boolean hasSpouse) {
		this.hasSpouse = hasSpouse;
	}

	// --------------------------- Child1 Info ------------

	private boolean hasChild1 ;

	private EmpFamilyInfo child1Info;

	public void setChild1Info(EmpFamilyInfo child1Info) {
		this.child1Info = child1Info;
	}

	public EmpFamilyInfo getChild2Info() {
		return child2Info;
	}

	public boolean isHasChild1() {
		return hasChild1;
	}

	public void setHasChild1(boolean hasChild1) {
		this.hasChild1 = hasChild1;
	}

	// --------------------------- Child2 Info ------------

	private boolean hasChild2 ;

	private EmpFamilyInfo child2Info;

	public EmpFamilyInfo getChild1Info() {
		return child1Info;
	}

	public void setChild2Info(EmpFamilyInfo child2Info) {
		this.child2Info = child2Info;
	}

	public boolean isHasChild2() {
		return hasChild2;
	}

	public void setHasChild2(boolean hasChild2) {
		this.hasChild2 = hasChild2;
	}

	// ----------------------- Files To Upload List ---------------
	public List<UsmsFile> filesToUploadList;

	// ----------------------- Passport Files ------------------

	public UsmsFile empPassportFile;
	public UsmsFile spousePassportFile;
	public UsmsFile child1PassportFile;
	public UsmsFile child2PassportFile;

	// ----------------------- Visa Files -----------------------

	public UsmsFile empVisaFile;
	public UsmsFile spouseVisaFile;
	public UsmsFile child1VisaFile;
	public UsmsFile child2VisaFile;

	// ----------------------- Emirates ID Files -----------------

	public UsmsFile empEIDFile;
	public UsmsFile spouseEIDFile;
	public UsmsFile child1EIDFile;
	public UsmsFile child2EIDFile;

	// ---------------------- Medical Insurance Files ---------------

	public UsmsFile empMIFile;
	public UsmsFile spouseMIFile;
	public UsmsFile child1MIFile;
	public UsmsFile child2MIFile;

	// ----------------------- Labour File ---------------------

	public UsmsFile empLabourCardFile;
	
	
	//...................... empList.............
	        //added by tariq
		private	List<EmpInfo> empList;

		public List<EmpInfo> getEmpList() {
			return empList;
		}

		public void setEmpList(List<EmpInfo> empList) {
			this.empList = empList;
		}
		
   public void setTotalSalary(EmpInfo empInfo)
     {   
	   empInfo.getEmpSalaryInfos().setTotal(empInfo.getEmpSalaryInfos().getBasic()+empInfo.getEmpSalaryInfos().getTransport()+
			   empInfo.getEmpSalaryInfos().getHousing()+empInfo.getEmpSalaryInfos().getOtherAllow());
      }
	
	// --------------- Constant---------------
	private static final int DEFAULT_BUFFER_SIZE = 10240;
    
	// --------------- View Documents---------------
	public String viewDocuments(String path) 
     {
		String filePath = path;
		String fileType=path.substring(path.lastIndexOf('.')+1);

        FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
	//	HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		  
		File file = new File(filePath);
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
		    
			input = new BufferedInputStream(new FileInputStream(file),
					DEFAULT_BUFFER_SIZE);
			response.reset();
			response.setHeader("Content-Type", "application"+"/"+fileType); 
		//	response.setHeader("Content-Type", "application"+"/"+fileType); 
			response.setHeader("Content-Length", String.valueOf(file.length())); 
			response.setHeader("Content-Disposition", "inline; filename=\""
					+ filePath + "\"");
			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
     
			output.flush();
			
		
		} catch (IOException e) {
			e.getStackTrace();
		}

		finally {
			close(output);
			close(input);
		}
	  
		facesContext.responseComplete();
		return "";
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	

}
