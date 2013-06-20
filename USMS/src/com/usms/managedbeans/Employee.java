package com.usms.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.richfaces.event.FileUploadEvent;

import com.usms.application.objects.UsmsFile;
import com.usms.businessagents.EmployeeRegistrationAgent;
import com.usms.db.dao.EmpInfoDAO;
import com.usms.db.model.EmpInfo;
import com.usms.helper.USMSFileUploadHelper;
import com.usms.managedbeans.application.ApplicationBean;
import com.usms.managedbeans.application.MenuBean;
import com.usms.view.model.EmployeeViewModel;

@ManagedBean
@ViewScoped
public class Employee {

	
	
	@ManagedProperty(value="#{ApplicationBean}")
	private ApplicationBean appBean;
	
	public ApplicationBean getAppBean() {
		return appBean;
	}
	public void setAppBean(ApplicationBean appBean) {
		this.appBean = appBean;
	  }
    
	//---------------------   Injections               ------------------------------
	
	@PersistenceContext(unitName="USMS")
	EntityManager em;
	
	@Resource
	UserTransaction ut;
	
	//------------------------Constructor-------------------------------------
	
	public Employee() {
		model = new EmployeeViewModel("register");	
		registrationAgent = new EmployeeRegistrationAgent();
		empInfodao = new EmpInfoDAO(em);
			
	}
	
	//----------------------- Getter & Setter - View Model -------------------------------------
	
	private EmployeeViewModel model;
	
	public EmployeeViewModel getModel() {
		return model;
	}
	public void setModel(EmployeeViewModel model) {
		this.model = model;
	}
	
	//----------------------- Getter & Setter - Registration Agent -------------------------------------
	
	private EmployeeRegistrationAgent registrationAgent;
	
	public EmployeeRegistrationAgent getRegistrationAgent() {
		return registrationAgent;
	}
	public void setRegistrationAgent(EmployeeRegistrationAgent registrationAgent) {
		this.registrationAgent = registrationAgent;
	}
	
	//----------------------- Data Access Object -------------------------------------
	

	private EmpInfoDAO empInfodao;
	
	//------------------------Action & Action Listener-------------------------------------
	
	
	public void searchEmployee() 
	{
		// Search The Employees From the DB and Populate the DB In the Table
		// accordingly
		// if only 1 result is fetched then show the file details page
		System.out.println(model.getEmpInfo().getFirstName());
		List<EmpInfo> empList = empInfodao.fetchAllEmpInfo(model.getEmpInfo().getFirstName(), em, ut);
		
		model.setEmpList(empList);
	}
	
	public void addEmployee() 
	{
		
	}
	
	public void selectEmployee()
	{
		//breaking the model object into individual object
		System.out.println(model.getEmpInfo().getEmpEducationInfos().size());
		System.out.println(model.getEmpInfo().getFirstName());
		
		model.splitEmployee();
		
	}
	
	public void deleteEmployee()
	{
		
	}
	
	//--------------------- New Employee Registration Process (form submit action listeners) -------------------------
	
	
	public void updateFileDetails(ActionEvent event) 
	{

		System.out.println(model.getEmpInfo());
		System.out.println(model.getPassportInfo());
		System.out.println(model.getLaborInfo());
		System.out.println(model.getMiInfo());
		System.out.println(model.getEidInfo());
		System.out.println(model.getEmpEmploymentInfo());
		System.out.println(model.getEmpEduInfo());

		System.out.println("reached update file - validation passed");
		if (registrationAgent.validateFileInfo(model))
			System.out.println("reached update file - validation passed");
		// redirect to next page
		else
			System.out.println("reached update file - validation failed");
		// stay on the same page
		// consolidate the object and call saveEmployee

		// saveEmployee(null);

	}
	
	public void updateCommunicationDetails(ActionEvent event)
	 {
		
        System.out.println(model.getPermAddr());
	   	System.out.println(model.getLocalAddr());
		System.out.println(model.getEmgrAddr());
		
		if(registrationAgent.validateCommInfo(model))
				System.out.println("reached update communication - validation passed");
				// redirect to next page
			else
				System.out.println("reached update communication - validation failed");
				// stay on the same page
	 }
	
	public void updateBankDetails(ActionEvent event)
	{
		System.out.println(model.getEmpBankInfo());
		
		if(registrationAgent.validateBankInfo(model))
			System.out.println("reached update Bank Detail - validation passed");
			// redirect to next page
		else
			System.out.println("reached update Bank Detail - validation failed");
			// stay on the same page
	}
	
	public void updateSalaryDetails(ActionEvent event)
	{
		System.out.println(model.getEmpSalInfo());
		
		if(registrationAgent.validateSalInfo(model))
			System.out.println("reached update salary Detail - validation passed");
			// redirect to next page
		else
			System.out.println("reached update salary Detail - validation failed");
			// stay on the same page
	}
	
	public void updateFamilyDetails(ActionEvent event)
	{
		System.out.println(model.getChild1Info());
		System.out.println(model.getChild2Info());
		System.out.println(model.getSpouseInfo());
		
		if(registrationAgent.validateFamilyInfo(model))
			System.out.println("reached update family Detail - validation passed");
			// redirect to next page
 		else
			System.out.println("reached update Bank Detail - validation failed");
			// stay on the same page
		
		// consolidate the object and call saveEmployee
	   saveEmployee(null);
	}  
	
	public void saveEmployee(ActionEvent event) 
	{
		// model.consolidateEmployee();
		// empInfodao.saveEmployee(model.getEmpInfo(), em, ut);

		saveUploadedFiles();

		model.consolidateEmployee();
		
		empInfodao.saveEmployee(model.getEmpInfo(), em, ut);

	}
	
	//------------------ Update the Employee Detail(added by Tariq)-----------------------------
	 
	public void updateEmployee() 
	{
		System.out.println("Reached at file update Function");
		System.out.println(model.getEmpInfo().getAddressInfos().get(0).getAddr1());
		System.out.println(model.getEmpInfo().getFirstName());
		System.out.println(model.getEmpInfo().getEmpBankInfos());
		System.out.println(model.getEmpInfo().getEmpSalaryInfos());
		System.out.println(model.getEmpInfo().getEmpLaborInfos());
		empInfodao.updateEmployee(model.getEmpInfo(), em, ut);
		
		String message = appBean.applicationPropreties.getProperty("UPDATE_SUCCESS");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			
	}
	

	
	private void saveUploadedFiles()
	  {
		
		if(model.filesToUploadList == null)
			return;
		
		String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
		
		if(model.empPassportFile != null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);
			
			 //added by Tariq
	        model.getPassportInfo().setSoftFilePath(relativePath);
		}
			
		// added by Tariq
		if (model.empVisaFile != null) 
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getVisaInfo().setSoftFilePath(relativePath);
		}
		
		//added by Tariq
		if(model.empEIDFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getEidInfo().setSoftFilePath(relativePath);
		}
		
		
		//added by Tariq
		if(model.empLabourCardFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getLaborInfo().setSoftFilePath(relativePath);
		}
			 
				
		if(model.empMIFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getMiInfo().setSoftFilePath(relativePath);
		}
				
				
		if(model.spousePassportFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getSpouseInfo().getPassportInfos().setSoftFilePath(relativePath);
		}
		
				
		if(model.spouseMIFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getSpouseInfo().getMiInfos().setSoftFilePath(relativePath);
		}
		
		
		if(model.child1PassportFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getChild1Info().getPassportInfos().setSoftFilePath(relativePath);
		}
		
		if(model.child1MIFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getChild1Info().getMiInfos().setSoftFilePath(relativePath);
		}
				
		
		if(model.child2PassportFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getChild2Info().getPassportInfos().setSoftFilePath(relativePath);
		}
    	
		
		if(model.child2MIFile!= null)
		{
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.empPassportFile, filesRoot);

			// added by Tariq
			model.getChild2Info().getMiInfos().setSoftFilePath(relativePath);
		}

		
				
//		for (Iterator<UsmsFile> iterator = model.filesToUploadList.iterator(); iterator.hasNext();) {
//			
//			UsmsFile fileToSave = (UsmsFile) iterator.next();
//
//			File dir = new File(filesRoot + File.separator
//					+ fileToSave.fileType + File.separator
//					+ model.getEmpInfo().getEmpNo());
//			
//			dir.mkdirs();
//
//			try {
//				
//				File file = new File(dir, fileToSave.ownerType + ".png");
//				
//				if (file.createNewFile()) {
//					
//					FileOutputStream stream = new FileOutputStream(file);
//					
//					stream.write(fileToSave.getData());
//			        stream.close();
//					
//			        fileToSave.setRelativePath(dir + File.separator+ fileToSave.ownerType +".png");
//					
//					System.out.println("new file created, FileType - "
//							+ fileToSave.fileType + " OwnerType - "
//							+ fileToSave.ownerType);
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
		
		
    	
	}
	
	//------------------ File Upload Action Listeners -----------------------------
	
	
	
	public void passportCopyUploadListener(FileUploadEvent event)
	{
		model.empPassportFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);
		
		// setting the employee number in the usms file object - because it is used in creating the file path
		model.empPassportFile.setEmpNo(model.getEmpInfo().getEmpNo());
		
		if(model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();
		
		model.filesToUploadList.add(model.empPassportFile);
	}

	public void visaCopyUploadListener(FileUploadEvent event)
	{
		model.empVisaFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);
		
		// setting the employee number in the usms file object - because it is used in creating the file path
				model.empPassportFile.setEmpNo(model.getEmpInfo().getEmpNo());
		
		if(model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();
		
		model.filesToUploadList.add(model.empPassportFile);
	}
	
	public void eidCopyUploadListener(FileUploadEvent event)
	{
		model.empEIDFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.empEIDFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.empEIDFile);
	}

	public void laborCopyUploadListener(FileUploadEvent event)
	{
		model.empLabourCardFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.empLabourCardFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.empLabourCardFile);
	}

	public void miCopyUploadListener(FileUploadEvent event)
	{
		model.empMIFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.empMIFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.empMIFile);
	}

	// below funtion created by Tariq
	public void spousePassportCopyUploadListener(FileUploadEvent event)
	{
		model.spousePassportFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.spousePassportFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.spousePassportFile);
	}

	public void spousemiCopyUploadListener(FileUploadEvent event)
	{
		model.spouseMIFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.spouseMIFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.spouseMIFile);
	}

	public void child1PassportCopyUploadListener(FileUploadEvent event)
	{
		model.child1PassportFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.child1PassportFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.child1PassportFile);
	}

	public void child1miCopyUploadListener(FileUploadEvent event)
	{
		model.child1MIFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.child1MIFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.child1MIFile);
	}

	public void child2PassportCopyUploadListener(FileUploadEvent event)
	{
		model.child2PassportFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.child2PassportFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.child2PassportFile);
	}

	public void child2miCopyUploadListener(FileUploadEvent event)
	{
		model.child2MIFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		model.child2MIFile.setEmpNo(model.getEmpInfo().getEmpNo());

		if (model.filesToUploadList == null)
			model.filesToUploadList = new ArrayList<UsmsFile>();

		model.filesToUploadList.add(model.child2MIFile);
	}
	
	//------------------ Reset Model-----------------------------
	 public void resetViewModel()
	 {
	   System.out.println("In the resetModelView");
	   String mode=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mode");
	   if(mode.equalsIgnoreCase("add"))
	     {
	      model=new EmployeeViewModel("register");
	     }
	 }
	
	

}
