package com.usms.view.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmployerDetail;
import com.usms.db.model.HrDocuments;

public class HrDocumentGenerationViewModel {


	
	public HrDocumentGenerationViewModel()
	     {
		 hrDocuments=new HrDocuments();
		 employerDetail=new EmployerDetail();
	     }
	 
	 EmpInfo empInfo=new EmpInfo();
	 
	 
	
	
	public EmpInfo getEmpInfo() {
		return empInfo;
	}
	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}
	// --------------- Constant---------------
		private static final int DEFAULT_BUFFER_SIZE = 10240;
	//-------------------------HR-Documents-------------------- 
	
	private List<EmpInfo> empList;
	private String docType;
	private String name;
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;  
	}
	
	public List<EmpInfo> getEmpList() {
		return empList;
	}
	public void setEmpList(List<EmpInfo> empList) {
		this.empList = empList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	//-------------------------hrDocuments model-------------------- 
	
	private HrDocuments hrDocuments;  
    private EmployerDetail employerDetail;
    
	private  String wpsSoftFilePath ;
	private  String wpsfileStatus="Pending";

	public HrDocuments getHrDocuments() {
		return hrDocuments;
	}
	public void setHrDocuments(HrDocuments hrDocuments) {
		this.hrDocuments = hrDocuments;
	}
	public EmployerDetail getEmployerDetail() {
		return employerDetail;
	}
	public void setEmployerDetail(EmployerDetail employerDetail) {
		this.employerDetail = employerDetail;
	}
	
	public String getWpsSoftFilePath() {  
		return wpsSoftFilePath;
	}
	public void setWpsSoftFilePath(String wpsSoftFilePath) {
		this.wpsSoftFilePath = wpsSoftFilePath;
	}
	
	public String getWpsfileStatus() {
		return wpsfileStatus;
	}
	public void setWpsfileStatus(String wpsfileStatus) {
		this.wpsfileStatus = wpsfileStatus;
	}
	//-------------------------Consolidate HrDocuments--------------------
	public void consolidateHrDocuments(EmpInfo empInfo)
	    {
		  this.hrDocuments.setYear(hrDocuments.getDate().getYear()+1900);   
		  this.hrDocuments.setMonth(getMonthFromInt(hrDocuments.getDate().getMonth()));
		  if(empInfo!=null)
		  this.hrDocuments.setEmpInfo(empInfo);
	    }
	
	public String getMonthFromInt(int iMonth)
	   {
		String month = "invalid";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		 if (iMonth >= 0 && iMonth <= 11)
		    month = months[iMonth];
		  return month;
	   }
   
	 public String viewPDFDocuments(String path) 
      {
	   String filePath=path;  
	   FacesContext facesContext = FacesContext.getCurrentInstance();
       ExternalContext externalContext = facesContext.getExternalContext();
       HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
       File file = new File(filePath);
       BufferedInputStream input = null;
       BufferedOutputStream output = null;
       try {
      
        input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
    
        response.reset();
        response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filePath + "\"");
        output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = input.read(buffer)) > 0) {
           output.write(buffer, 0, length);  
         }


          output.flush();
        } catch(IOException e)
        {
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
      
      public void downloadFile(String path)
        {
	    System.out.println("Reache in the Download function");
	    System.out.println(path);
	    File file = new File(path);
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

	
}
