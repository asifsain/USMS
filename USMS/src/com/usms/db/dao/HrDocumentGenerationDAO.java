package com.usms.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmployerDetail;
import com.usms.db.model.HrDocuments;

public class HrDocumentGenerationDAO {
	EntityManager em;
	
	public HrDocumentGenerationDAO(EntityManager em)
	  {
		this.em=em;
		
	 }
	
	public List<EmpInfo> selectAllEmployee(UserTransaction ut, EntityManager em,String id)
     	{
		List<EmpInfo> empList = new ArrayList<EmpInfo>();
		try {
			System.out.println("reached 5");
			  ut.begin();
			  em.getEntityManagerFactory().getCache().evictAll();
			  Query q = em.createQuery("Select A from EmpInfo A where (A.firstName LIKE :name) or (A.empNo LIKE :id)");  
			  q.setParameter("name", "%"+id+"%");
			  q.setParameter("id", "%"+id+"%");
			  empList = q.getResultList();
			  ut.commit();
			
		    } catch (Exception e) {  
			System.out.println(e.getMessage());
		//	ut.rollback();
		  }
		return empList;
	    }    
	
	//------------------------------------Insert into Emp_Hr_Documents---------------
	
	public void insertEmpHrDocuments(HrDocuments hrDocs,EntityManager em,UserTransaction ut)
	   {
		System.out.println("In the HrDocsDAO");
		try {
		      ut.begin();
		      em.persist(hrDocs);  
		      em.flush();      
		      ut.commit();
		    } catch (Exception e) {
			 System.out.println(e.getMessage());
		
			 try {
				ut.rollback();
			    } catch (IllegalStateException | SecurityException
					| SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	   }
	  public EmployerDetail selectEmployerDetail(EntityManager em,UserTransaction ut)
	    {
		  System.out.println("In the HrDocsDAO");
		  EmployerDetail employerDetail=null;
			try {
			      ut.begin();
			      Query q=em.createQuery("Select e from EmployerDetail e") ;
			      
			      employerDetail=(EmployerDetail) q.getSingleResult();
			         
			      ut.commit();
			     } catch (Exception e) {
				 System.out.println(e.getMessage());
			      try {
					  ut.rollback();
				     } catch (IllegalStateException | SecurityException
						| SystemException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			  }
			return employerDetail;
	    }
	
	

}
