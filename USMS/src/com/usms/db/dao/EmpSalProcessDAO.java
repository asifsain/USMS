package com.usms.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.usms.db.model.EmpAdjTrx;
import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmpSalTrx;


public class EmpSalProcessDAO
 {
	EntityManager em;

	public EmpSalProcessDAO(EntityManager em)
	{
		this.em = em;
	}

	public void saveSalAdjustment(EmpAdjTrx empSalArj, EntityManager emm, UserTransaction ut)
	 {
		System.out.println("Reaced in SalArjustment DAO");
		try
		{
			ut.begin();
			emm.persist(empSalArj);
			ut.commit();
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			try
			{
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1)
			{

				e1.printStackTrace();
			}
		}

	}
	
	
	public List<EmpAdjTrx> selectSalArjustment(String empId, int month,int Year,EntityManager emm, UserTransaction ut)
	     {
		System.out.println("Reached in SalArjustment DAO");
		List<EmpAdjTrx> arjlist= new ArrayList<>();
		CriteriaBuilder cb = emm.getCriteriaBuilder();
		try
		  {
		  ut.begin();
		    CriteriaQuery<EmpAdjTrx> cq = cb.createQuery(EmpAdjTrx.class);
	  	    Root<EmpAdjTrx> root=cq.from(EmpAdjTrx.class);
		    Expression<Integer> year= cb.function("year", Integer.class,root.get("adjDt"));
		    Expression<Integer> mon = cb.function("month", Integer.class,root.get("adjDt"));
		    Expression<Integer> id = root.get("empInfo").get("empNo");
		    Predicate perdicate1=cb.equal(year, Year);
		    Predicate perdicate2=cb.equal(mon,    month+1);
		    Predicate perdicate3=cb.equal(id,  empId);
		    if(month==-1)
		    cq.where(cb.and(perdicate1,perdicate3));
		    else 
		    cq.where(cb.and(perdicate1,perdicate2,perdicate3));
		    TypedQuery<EmpAdjTrx> typedQuery = emm.createQuery(cq);   
		    arjlist=typedQuery.getResultList();  
		    ut.commit();
	     }catch (Exception e)
		 {
			System.out.println(e.getMessage());
			try
			{   
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1)
			{

				e1.printStackTrace();
			}
		}
		return arjlist;
   }
	
	public List<EmpInfo>  selectAllEmployee(UserTransaction ut,EntityManager em)
	   {
		System.out.println("In Select All Employeee DAO function");
		List<EmpInfo>  empList=new ArrayList<>();
		try
		  {
			ut.begin();
			em.getEntityManagerFactory().getCache().evictAll();
			Query q=em.createQuery("Select A from EmpInfo A ");
			empList =q.getResultList();
			ut.commit(); 
		  }catch (Exception e)   
		  {
		    System.out.println(e.getMessage());
			try
			{   
					ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1)
			{

					e1.printStackTrace();
		     }
		  }
		return empList;
	  }
	 public List<EmpAdjTrx> selectAdjustmentDetail(UserTransaction ut,EntityManager em,int Year,int month,String empNo)
	    {
		    System.out.println("In the Arjustment detail detail");
			List<EmpAdjTrx>  arjustmentDetail=new ArrayList<>();
			try
			  {
				ut.begin();
				Query q=em.createQuery("select a from EmpAdjTrx a  WHERE (FUNC('MONTH',a.adjDt)=:month  AND FUNC('YEAR',a.adjDt)=:year AND a.empInfo.empNo=:id)");
				 q.setParameter("year",Year);
		         q.setParameter("month", (month+1));   
		         q.setParameter("id", empNo);
		        arjustmentDetail=q.getResultList();   
		       ut.commit();
			  }catch (Exception e)   
			  {
			    System.out.println(e.getMessage());
				try
				  {   
						ut.rollback();
				  } catch (IllegalStateException | SecurityException | SystemException e1)
			      {

						e1.printStackTrace();
			      }
			  }	
			 return arjustmentDetail;
	     }
	 
	 public void saveSalaryProcess(EmpSalTrx salTrx,UserTransaction ut,EntityManager em)
	      {
		         System.out.println("In the saveSalary process detail detail");
		        try
				   {
		    	     ut.begin();
		    	     em.persist(salTrx);
		    	 	 em.flush();
		    	     ut.commit();
		    	     
				   }catch (Exception e)   
				   {
				    System.out.println(e.getMessage());
					try
					  {   
							ut.rollback();
					  } catch (IllegalStateException | SecurityException | SystemException e1)
				      {

							e1.printStackTrace();
				      }
				  }	
	      }
	 public List<EmpSalTrx> searchEmployee(UserTransaction ut,EntityManager em, String month,int year,String name)
	  {
		  System.out.println("In the saveSalary process detail detail");
		  List<EmpSalTrx> list=new ArrayList<>();
		  try
		   {
   	          ut.begin();
   	          Query q=em.createQuery("select A FROM EmpSalTrx A where (A.empInfo.firstName LIKE :name or A.empInfo.empNo LIKE :id) AND (A.salTrxMonth=:month) AND  FUNC('YEAR',A.fromDt)=:year");
   	            q.setParameter("name","%"+name+"%");
	            q.setParameter("id","%"+name+"%" );   
	            q.setParameter("month",month);
	            q.setParameter("year",year);
	            list=q.getResultList();
   	          ut.commit();
   	     
		   }catch (Exception e)   
		   {
		    System.out.println(e.getMessage());
			try
			  {   
					ut.rollback();
			  } catch (IllegalStateException | SecurityException | SystemException e1)
		      {

					e1.printStackTrace();
		      }
		  }	
		  return list;
	  }
	 
	 
	 public void updateSalaryTrx(EmpSalTrx salTrx, UserTransaction ut, EntityManager em)
	     {
		   
		    System.out.println("In the update Salary Trx");
		    try
			    {
		          ut.begin();
		          em.merge(salTrx);
		          ut.commit();
			    }catch (Exception e)   
			   {
			    System.out.println(e.getMessage());
			   try
				 {   
						ut.rollback();
				  } catch (IllegalStateException | SecurityException | SystemException e1)
			      {

						e1.printStackTrace();
			       }
	       }
	     }
	 
	 
	   
	
 }
