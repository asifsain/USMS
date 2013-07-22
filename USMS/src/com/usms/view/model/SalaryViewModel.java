package com.usms.view.model;


import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.usms.application.objects.UsmsFile;
import com.usms.db.model.EmpAdjTrx;
import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmpSalTrx;

public class SalaryViewModel {
	
	
	 public SalaryViewModel() {
		       
        }
	// -------------------------- Sal Info ---------------
	private EmpInfo empInfo=new EmpInfo();
	private EmpAdjTrx empSalAdj=new EmpAdjTrx();
	private EmpSalTrx  empSalTrx=new EmpSalTrx();
	public EmpInfo getEmpInfo() {
		return empInfo;
	}
	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}
	public EmpAdjTrx getEmpSalAdj() {
		return empSalAdj;
	}
	public void setEmpSalAdj(EmpAdjTrx empSalAdj) {
		this.empSalAdj = empSalAdj;
	}
	public EmpSalTrx getEmpSalTrx() {
		return empSalTrx;
	}
	public void setEmpSalTrx(EmpSalTrx empSalTrx) {
		this.empSalTrx = empSalTrx;
	}
	
	
	//-------------------------Consolidate--------------------
	 public void consolidateSalaryAdj(){
		 
		
	  }
	
	//-------------------------SoftFile--------------------
	// public UsmsFile softCopy;
	  public UsmsFile salAdjSoftFile;
	 
	//-------------------------Adjustment History--------------------
	  
	  private int month;
	  private int year;
	  private String empName;
	  private Date selectedMonth;
	  private Date selectedYear;
	  private List<EmpAdjTrx> salaryArjList;
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<EmpAdjTrx> getSalaryArjList() {
		return salaryArjList;
	}
	public void setSalaryArjList(List<EmpAdjTrx> salaryArjList) {
		this.salaryArjList = salaryArjList;
	}
	public Date getSelectedMonth() {
		return selectedMonth;
	}
	public void setSelectedMonth(Date selectedMonth) {
		this.selectedMonth = selectedMonth;
	}
	public Date getSelectedYear() {
		return selectedYear;
	}
	public void setSelectedYear(Date selectedYear) {
		
		this.selectedYear = selectedYear;
	}

	//-------------------------Salary Process--------------------
	private boolean checkAllNone=true;
	private List<EmpInfo> empList;
	private List<EmpAdjTrx> adjustmentList;
	private String salaryOption="I";
	private Date fromDate;
	private Date toDate;
	private List<EmpSalTrx> empSalarySlpList;
	private List<SelectItem> years=new ArrayList<>();
	private List<SelectItem> months=new ArrayList<>();
	public List<EmpInfo> getEmpList() {
		return empList;
	}
	public void setEmpList(List<EmpInfo> empList) {
		this.empList = empList;
	}
	public List<EmpAdjTrx> getAdjustmentList() {
		return adjustmentList;
	}
	public void setAdjustmentList(List<EmpAdjTrx> adjustmentList) {
		this.adjustmentList = adjustmentList;
	}
	public String getSalaryOption() {
		return salaryOption;
	}
	public void setSalaryOption(String salaryOption) {
		this.salaryOption = salaryOption;
	}
    
	
	public List<EmpSalTrx> getEmpSalarySlpList() {
		return empSalarySlpList;
	}
	public void setEmpSalarySlpList(List<EmpSalTrx> empSalarySlpList) {
		this.empSalarySlpList = empSalarySlpList;
	}
	
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public void consolidateSalaryProcess(EmpSalTrx salTrx,EmpInfo empInfo,int month)
	  {  
		  salTrx.setSalTrxMonth(this.getMonthFromInt(month));
		  salTrx.setAdjAmt(empInfo.getTotalAdj());
		  salTrx.setTotalAmt(empInfo.getTatalPayable());
		  salTrx.setSalAmt(empInfo.getEmpSalaryInfos().getTotal());
		  salTrx.setEmpName(empInfo.getFirstName());
		  salTrx.setFromDt(this.fromDate);
		  salTrx.setToDate(this.toDate);
		  salTrx.setEmpInfo(empInfo);
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
	

	public void buildYearOptions() 
	  {
	    final int initialYear = 2007;
	    final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    for (int year = initialYear; year <= currentYear; year++) 
	     {
	        years.add(new SelectItem(year, String.valueOf(year)));
	     }        
	  } 
	
	public void buildMonthOptions() 
	  {
		 String month = "invalid";
		 months.add(new SelectItem(-1,"All"));
		 DateFormatSymbols dfs = new DateFormatSymbols();
		 String[] smonths = dfs.getMonths();
		 for(int imonth=0 ;imonth<=11; imonth++)
		 {	 
		    month = smonths[imonth];
		    months.add(new SelectItem(imonth, month));
		 } 
		
	  } 
	public List<SelectItem> getYears() {
		return years;
	 }
	public void setYears(List<SelectItem> years) {
		this.years = years;
	}
	public List<SelectItem> getMonths() {
		return months;
	  }
	 public void setMonths(List<SelectItem> months) {
		this.months = months;
	  }
	public boolean isCheckAllNone() {
		return checkAllNone;
	}
	public void setCheckAllNone(boolean checkAllNone) {
		this.checkAllNone = checkAllNone;
	}
   
	
	 
	

}
