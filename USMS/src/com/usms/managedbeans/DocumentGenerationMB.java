package com.usms.managedbeans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.usms.db.dao.EmpSalProcessDAO;
import com.usms.db.dao.HrDocumentGenerationDAO;
import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmpSalTrx;
import com.usms.db.model.HrDocuments;
import com.usms.managedbeans.application.ApplicationBean;
import com.usms.pdfgeneration.USMSPDFDocumentGeneration;
import com.usms.view.model.HrDocumentGenerationViewModel;
import com.usms.view.model.SalaryViewModel;

@ManagedBean
@ViewScoped
public class DocumentGenerationMB {

	@ManagedProperty(value = "#{ApplicationBean}")
	private ApplicationBean appBean;

	public ApplicationBean getAppBean() {
		return appBean;
	}

	public void setAppBean(ApplicationBean appBean) {
		this.appBean = appBean;
	}

	// --------------------- Injections ------------------------------

	@PersistenceContext(unitName = "USMS")
	EntityManager em;

	@Resource
	UserTransaction ut;
	// --------------- Constructor ---------------

	public DocumentGenerationMB() {
	
		  model=new SalaryViewModel();
		  docModel=new HrDocumentGenerationViewModel();
		  model.buildMonthOptions();
		  model.buildYearOptions();
		  empSalProcessdao = new EmpSalProcessDAO(em);
		  docDAO=new HrDocumentGenerationDAO(em);

	}

	// ----------------------- Getter & Setter - View Model
	// -------------------------------------
	private SalaryViewModel model;
	private HrDocumentGenerationViewModel docModel;

	

	public SalaryViewModel getModel() {
		return model;
	}

	public void setModel(SalaryViewModel model) {
		this.model = model;
	}
    
	public HrDocumentGenerationViewModel getDocModel() {
		return docModel;
	}

	public void setDocModel(HrDocumentGenerationViewModel docModel) {
		this.docModel = docModel;
	}

	private EmpSalProcessDAO empSalProcessdao;
	
	private HrDocumentGenerationDAO docDAO;



	 public void searchEmployee()
	    {
		    System.out.println(model.getEmpInfo().getFirstName());
		    List<EmpSalTrx> salList =empSalProcessdao.searchEmployee(ut,em,model.getMonthFromInt(model.getMonth()), model.getYear(),model.getEmpName());
		    model.setEmpSalarySlpList(salList);
	    }
	 
      public void searchAllEmployee()
         {
    	    System.out.println("In the Search Employee");
    	    List<EmpInfo> list=docDAO.selectAllEmployee(ut, em, docModel.getName());
    	    docModel.setEmpList(list);
    	    this.disableView();
    	    
         }
	
	  public String viewPDFSalarySlip()
	       {
		    String filePath=model.getEmpSalTrx().getSoftFilePath();
            docModel.viewPDFDocuments(filePath);
            return null;
           } 
	 

	
	 
//	 public String viewPDFExpLetter() 
//	       {
//		     String filePath=model.getEmpSalTrx().getSoftFilePath();  
//		     FacesContext facesContext = FacesContext.getCurrentInstance();
//             ExternalContext externalContext = facesContext.getExternalContext();
//             HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
//             File file = new File(filePath);
//             BufferedInputStream input = null;
//             BufferedOutputStream output = null;
//            try {
//            
//              input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
//          
//              response.reset();
//              response.setHeader("Content-Type", "application/pdf");
//              response.setHeader("Content-Length", String.valueOf(file.length()));
//              response.setHeader("Content-Disposition", "inline; filename=\"" + filePath + "\"");
//              output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
//              byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
//              int length;
//              while ((length = input.read(buffer)) > 0) {
//                 output.write(buffer, 0, length);
//              }
//
//     
//              output.flush();
//             } catch(IOException e)
//              {
//             	 e.getStackTrace();
//              }
//            
//            finally {
//            close(output);
//            close(input);
//          }
//         facesContext.responseComplete();
    //      return "";
   //      }
//	        private static void close(Closeable resource) {
//	            if (resource != null) {
//	              try {
//	                 resource.close();
//	                } catch (IOException e) {
//	                
//	                e.printStackTrace();
//	            }
//	        }
//	    }
   


	 public void generatePDFFinalSettlement() 
	     {
		 for (EmpInfo empInfo : docModel.getEmpList()) 
	      {
			if (empInfo.isFlag()) 
			{
				String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
				String path=filesRoot + File.separator +"FinalSettlement"+File.separator+empInfo.getEmpNo()+ File.separator;
				docModel.getHrDocuments().setSoftFilePath(path+empInfo.getFirstName()+"_"+"FinalSettlement.pdf");
				docModel.consolidateHrDocuments(empInfo);
				docDAO.insertEmpHrDocuments(docModel.getHrDocuments(), em, ut);
				USMSPDFDocumentGeneration.create_Final_Settlement_Pdf(empInfo, path);
		     }
	      }
		 this.searchAllEmployee();
		 String message = appBean.applicationPropreties.getProperty("DOCUMENT_SUCCESS");
	     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	     }
        
	public void generatePDFSalarySlipBulk()
	   {
		   for (EmpSalTrx salModel : model.getEmpSalarySlpList())  
		    {
			 if (salModel.getEmpInfo().isFlag())
			    {
				String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
				String path=filesRoot + File.separator +"SalarySlip"+File.separator+ salModel.getEmpInfo().getEmpNo()+ File.separator;
	 			USMSPDFDocumentGeneration.Create_SalarySlip_Pdf(salModel,path);
				salModel.setSoftFilePath(path+salModel.getEmpName()+"_"+salModel.getSalTrxMonth()+"_"+"SalarySlip.pdf");
				empSalProcessdao.updateSalaryTrx(salModel, ut, em);
			    }
		     }
		    this.searchEmployee();
		    String message = appBean.applicationPropreties.getProperty("SALARYSLIP_SUCCESS");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	    }

	public void generatePDFServiceEndExpLetterBulk() 
	   {
		 for (EmpInfo empInfo : docModel.getEmpList())
		    {
			if (empInfo.isFlag())
			  {
				String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
				String path=filesRoot + File.separator +"ExperienceLetter"+File.separator+empInfo.getEmpNo()+ File.separator;
				docModel.getHrDocuments().setSoftFilePath(path+empInfo.getFirstName()+"_"+"ServiceEndExpLetter.pdf");
				docModel.consolidateHrDocuments(empInfo);
				docDAO.insertEmpHrDocuments(docModel.getHrDocuments(), em, ut);
				USMSPDFDocumentGeneration.create_ENDSERVICE_Exp_Letter_Pdf(empInfo,path);
			  }

		   }
		  this.searchAllEmployee();  
		 String message = appBean.applicationPropreties.getProperty("DOCUMENT_SUCCESS");
	     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	    }

	public void generatePDFSalaryCertificateBulk() 
	  {
		
		for (EmpInfo empInfo : docModel.getEmpList()) 
	    {
			if (empInfo.isFlag()) 
			 {
				String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
				String path=filesRoot + File.separator +"SalaryCertificate"+File.separator+empInfo.getEmpNo()+ File.separator;
				docModel.getHrDocuments().setSoftFilePath(path+empInfo.getFirstName()+"_"+"SalaryCertificate.pdf");
				docModel.consolidateHrDocuments(empInfo);
				docDAO.insertEmpHrDocuments(docModel.getHrDocuments(), em, ut);
				USMSPDFDocumentGeneration.Create_SalaryCertificate_Pdf(empInfo, path);
		     }
   
		 }
		 this.searchAllEmployee();
		 String message = appBean.applicationPropreties.getProperty("DOCUMENT_SUCCESS");
	     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	  }
	
	
	public void generatePDFGenralExpLetter()
	    {
		 for (EmpInfo empInfo : docModel.getEmpList()) 
	      {
			if (empInfo.isFlag()) 
			{
				String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
				String path=filesRoot + File.separator +"ExperienceLetter"+File.separator+empInfo.getEmpNo()+ File.separator;
				docModel.getHrDocuments().setSoftFilePath(path+empInfo.getFirstName()+"_"+"GeneralExpLetter.pdf");
				docModel.consolidateHrDocuments(empInfo);  
				docDAO.insertEmpHrDocuments(docModel.getHrDocuments(), em, ut);
				USMSPDFDocumentGeneration.create_GeneralEXP_Letter_Pdf(empInfo, path);
		     }
   
		}
		 this.searchAllEmployee();
		 String message = appBean.applicationPropreties.getProperty("DOCUMENT_SUCCESS");
	     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	    }
	
	public void generateBeneficiaryDetail()
     {
	   List<EmpInfo> list=new ArrayList<>();
	  for (EmpInfo empInfo : docModel.getEmpList()) 
        {
		if (empInfo.isFlag()) 
		 {
			list.add(empInfo);
	     }

	     }
	    String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
	    String path=filesRoot + File.separator +"BeneficiaryDetail"+File.separator+ File.separator;
        docModel.getHrDocuments().setSoftFilePath(path+"_"+"BeneficiaryDetail.pdf");
	    docModel.consolidateHrDocuments(null);
	    docDAO.insertEmpHrDocuments(docModel.getHrDocuments(), em, ut);
	    USMSPDFDocumentGeneration.beneficiary_Detail_Pdf(list, path); 
	    this.searchAllEmployee();
	    String message = appBean.applicationPropreties.getProperty("DOCUMENT_SUCCESS");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }
	
	public void generatePDFDocument()
	  {
		System.out.println("In the document generation");
		if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("END_SERVICE_EXP_LETTER"))
		   {
			 this.generatePDFServiceEndExpLetterBulk();
		   }
		else if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("GENERAL_EXP_LETTER"))
		   {
			this.generatePDFGenralExpLetter();
		   }   
		else if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("SALARY_CERTIFICATE"))
		   {  
			this.generatePDFSalaryCertificateBulk();
		    }
		 else if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("FINAL_SETELMENT"))
		   {
			 this.generatePDFFinalSettlement();  
		   }
		 else if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("BENEFICIARY_DETAIL"))
		    { 
			  this.generateBeneficiaryDetail();
		     }
			
		 }
	
   public void generateWPSDocument()
	   {
	  	    System.out.println("In the wages Protection System");
	      	String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");
		    String path=filesRoot + File.separator +"WPS"+File.separator+ File.separator+model.getYear()+File.separator;
		    String month=docModel.getMonthFromInt(model.getMonth());
		    docModel.getHrDocuments().setSoftFilePath(path+ month+ "_"+"WPS.sif");
	  	    docModel.setEmployerDetail(docDAO.selectEmployerDetail(em, ut));
	  	    USMSPDFDocumentGeneration.create_WPS_Pdf(model.getEmpSalarySlpList(), path,model.getMonth(),model.getYear(),docModel.getEmployerDetail(),month);
	  	    String message = appBean.applicationPropreties.getProperty("DOCUMENT_SUCCESS");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	   }      
	      
	public String viewpdfDocuments()
	   {
		System.out.println("In the document generation");
		for(HrDocuments hrDocs:docModel.getEmpInfo().getHrDocuments())
		  {
		   if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("END_SERVICE_EXP_LETTER")&& hrDocs.getDocType().equalsIgnoreCase("END_SERVICE_EXP_LETTER"))
		    {
			 docModel.viewPDFDocuments(hrDocs.getSoftFilePath());
		    }
		  if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("GENERAL_EXP_LETTER") && hrDocs.getDocType().equalsIgnoreCase("GENERAL_EXP_LETTER"))
		    {
			  docModel.viewPDFDocuments(hrDocs.getSoftFilePath());
		    }
		  if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("SALARY_CERTIFICATE") && hrDocs.getDocType().equalsIgnoreCase("SALARY_CERTIFICATE"))
		    {
			  docModel.viewPDFDocuments(hrDocs.getSoftFilePath());
		    }
		  if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("FINAL_SETELMENT") && hrDocs.getDocType().equalsIgnoreCase("FINAL_SETELMENT"))
		     {
			   docModel.viewPDFDocuments(hrDocs.getSoftFilePath());
		     }
		  if(docModel.getHrDocuments().getDocType().equalsIgnoreCase("BENEFICIARY_DETAIL") && hrDocs.getDocType().equalsIgnoreCase("BENEFICIARY_DETAIL"))
		     {
			   docModel.viewPDFDocuments(hrDocs.getSoftFilePath());
		     }
		}
		
		return null;
	   }
	
	public void disableView()
	   {
		System.out.println("In the disable function");
		if(docModel.getEmpList()!=null)
		{	
		   for(EmpInfo empInfo:docModel.getEmpList())
		    {
			   empInfo.setFlag(false); 
			 for(HrDocuments docs:empInfo.getHrDocuments())
			 {
				 if(docs.getDocType().equalsIgnoreCase(docModel.getHrDocuments().getDocType()))
				 {
					empInfo.setSalayStatus("generated");
					break;
				 }
				 else
				 {
					 empInfo.setSalayStatus("pending");
				 }
			 }
		    }
		 }
	   }
	    

}
