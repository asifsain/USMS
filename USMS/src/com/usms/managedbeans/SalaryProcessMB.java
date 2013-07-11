package com.usms.managedbeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.webapp.FacesServlet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import org.richfaces.event.FileUploadEvent;

import com.steadystate.css.parser.ParseException;
import com.usms.application.objects.UsmsFile;
import com.usms.db.dao.EmpSalProcessDAO;
import com.usms.db.model.EmpAdjTrx;
import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmpSalTrx;
import com.usms.helper.USMSFileUploadHelper;
import com.usms.managedbeans.application.ApplicationBean;
import com.usms.view.model.SalaryViewModel;

@ManagedBean
@ViewScoped
public class SalaryProcessMB
{
   
	@ManagedProperty(value = "#{ApplicationBean}")
	private ApplicationBean appBean;

	public ApplicationBean getAppBean()
	{
		return appBean;
	}

	public void setAppBean(ApplicationBean appBean)
	{
		this.appBean = appBean;
	}

	// --------------------- Injections ------------------------------

	@PersistenceContext(unitName = "USMS")
	EntityManager em;

	@Resource
	UserTransaction ut;

	// ----------------------- Data Access Object -------------------------------------
	private EmpSalProcessDAO empSalAdjustdao;

	// ------------------------Constructor-------------------------------------

	public SalaryProcessMB()
	{
		model = new SalaryViewModel();
		empSalAdjustdao = new EmpSalProcessDAO(em);
		model.buildMonthOptions();
		model.buildYearOptions();
		
	}

	private SalaryViewModel model; 

	public SalaryViewModel getModel()
	{
		return model;
	}

	public void setModel(SalaryViewModel model)
	{
		this.model = model;
	}

	public void selectEmployee()
	 {
		
		System.out.println("In salary Adjustment ");
		model.getEmpSalAdj().setEmpInfo(model.getEmpInfo());
		model.setSalaryArjList(new ArrayList<EmpAdjTrx>());

	 }

	public void saveSalAdj(ActionEvent event)
	{
		System.out.println("In Save Salry function");
		System.out.println(model.getEmpSalAdj());
		this.saveSoftFiles();

		empSalAdjustdao.saveSalAdjustment(model.getEmpSalAdj(), em, ut);
		String message = appBean.applicationPropreties.getProperty("ADD_SUCCESS");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));

	 }
	
	public void selectSalAdj(ActionEvent event)
	   {
		 System.out.println("In select Salary Arjustment");
	     System.out.println( model.getSelectedYear());
		 String empId=model.getEmpSalAdj().getEmpInfo().getEmpNo();
	     List<EmpAdjTrx> list=empSalAdjustdao.selectSalArjustment(empId, model.getMonth(), model.getYear(), em, ut);
		 model.setSalaryArjList(list);
		
	   }

	public void saveSoftFiles()
	 {
		if (model.salAdjSoftFile == null)
			return;

		String filesRoot = appBean.applicationPropreties.getProperty("filesRoot");

		if (model.salAdjSoftFile != null)
		 {
			String relativePath = USMSFileUploadHelper.saveUsmsFile(model.salAdjSoftFile, filesRoot);

			// added by Tariq
			model.getEmpSalAdj().setSoftFilePath(relativePath);
		}
	}

	// ------------------------------SoftFileUpload Listener--------------------------------
	public void salAdjSoftFileUploadListener(FileUploadEvent event)
    	 {
		System.out.println("In soft File");
		 model.salAdjSoftFile = USMSFileUploadHelper.createUsmsFileData(new UsmsFile(), event);

		// setting the employee number in the usms file object - because it is used in creating the file path
		 model.salAdjSoftFile.setEmpNo(model.getEmpInfo().getEmpNo());
	    }

	
	// ------------------------------SoftFileDownLoad-------------------------------
	public void salAdjSoftFileDownload()
	       {
		    System.out.println("Reache in the Download function");
		    System.out.println(model.getEmpSalAdj().getSoftFilePath());
		    File file = new File(model.getEmpSalAdj().getSoftFilePath());
            FileInputStream in;
            FacesContext context=FacesContext.getCurrentInstance();
            HttpServletResponse response=(HttpServletResponse) context.getExternalContext().getResponse();
            try {
	             if (!file.exists())
	                {
	           // context.addMessage(new ErrorMessage("msg.file.notdownloaded"));
	           // context.setForwardName("failure");
	                } 
	             else{
	            	    response.setContentType("application/octet-stream");
	            	    response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()+ "");
	                    in= new FileInputStream(file);
	                    byte[] buf = new byte[(int)file.length()];
	                    int offset = 0;
	                    int numRead = 0;
	                    while ((offset < buf.length) && ((numRead = in.read(buf, offset, buf.length -offset)) >= 0)) 
	                    {
	                      offset += numRead;
	                    }
	                    in.close();
			            response.getOutputStream().write(buf);     
			            response.getOutputStream().flush();
			            response.getOutputStream().close();
			            FacesContext.getCurrentInstance().responseComplete();
	                   }
	                   } catch (IOException  e) {
				
				        e.printStackTrace();
	    	            }
			      }
	
	
	// ------------------------------Salary Process -------------------------------
	
	public void selectAllEmployee()
	     {
		    System.out.println("Select All Employee");
		    List <EmpInfo> list= empSalAdjustdao.selectAllEmployee(ut,em);
		    for(EmpInfo empInfo:list)
		     {
		    	System.out.println(empInfo.getEmpSalTrxs());
		    	System.out.println(empInfo.getEmpSalaryInfos());
		    	if(empInfo.getEmpSalaryInfos()!=null)
		    	empInfo.getTotalAdj(model.getYear(),model.getMonth(), empInfo.getEmpAdjTrxs(),empInfo.getEmpSalaryInfos().getTotal());
		    	for(EmpSalTrx salTrx: empInfo.getEmpSalTrxs())
		    	 {   
		    		System.out.println(empInfo.getEmpSalTrxs().size());
		    		System.out.println(empInfo.getFirstName());
		    		System.out.println(salTrx.getSalTrxMonth());
		    		if(salTrx.getSalTrxMonth()!=null && salTrx.getSalTrxMonth().equalsIgnoreCase(model.getMonthFromInt(model.getMonth()))
		    			&&salTrx.getFromDt()!=null	&& (salTrx.getFromDt().getYear()+1900)==model.getYear())
		    		 {
		    			empInfo.setSalayStatus("Processed");
		    			empInfo.setFlag(false);   
		    			break;
		    		 }
		    			  
		    	   }
		    	
		     }
		    model.setEmpList(list);
		 }
	
	public void selectAdjustmentDetail()  
	    {
		  System.out.println("In the Arjustment detail");
		  List<EmpAdjTrx> list=empSalAdjustdao.selectAdjustmentDetail(ut, em, model.getYear(),model.getMonth(),model.getEmpInfo().getEmpNo());
		  model.setAdjustmentList(list);
	    }
	
	public void resetAdjustment()
	  {
		 System.out.println("In reset Adjustment");
		 EmpAdjTrx empAdj=new EmpAdjTrx();
		 empAdj.setEmpInfo(model.getEmpInfo());
		 model.setEmpSalAdj(empAdj);
	  }
	
	public void processSalary()
	    {
		  System.out.println("In the the Salary process");
		  System.out.println(model.getEmpInfo().getEmpSalTrxs().get(0).getEmpName());
		  System.out.println(model.getEmpInfo().getEmpSalTrxs().get(0).getEmpInfo().getFirstName());
		  EmpSalTrx salTrx=new EmpSalTrx();
          model.consolidateSalaryProcess(salTrx, model.getEmpInfo(),model.getMonth());
		  empSalAdjustdao.saveSalaryProcess(salTrx, ut, em);
		  
	    }
	
	public void bulkSalaryProcess()
	  {
		System.out.println("In the the Bulk Salary process");
		for(EmpInfo empInfo:model.getEmpList())
		 {
			if(empInfo.isFlag())
			{
				  EmpSalTrx salTrx=new EmpSalTrx();
				  model.consolidateSalaryProcess(salTrx, empInfo,model.getMonth());
				  empSalAdjustdao.saveSalaryProcess(salTrx, ut, em);
				  
			}
		 }
		String message = appBean.applicationPropreties.getProperty("SALARYPROCESS_SUCCESS");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		this.selectAllEmployee();
	  }
             
  
}



  

