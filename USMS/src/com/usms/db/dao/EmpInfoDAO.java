package com.usms.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.usms.db.model.EmpInfo;

public class EmpInfoDAO {

	EntityManager em;

	public EmpInfoDAO(EntityManager em) {
		this.em = em;
	}
	
	public List<EmpInfo> fetchAllEmpInfo(String id,EntityManager emm,UserTransaction ut)
	  {
	//	EntityTransaction et = emm.getTransaction();
		
		System.out.println("reached 4");
		List<EmpInfo> empList = new ArrayList<EmpInfo>();
		try {
			System.out.println("reached 5");
			//empList.addAll(em.createQuery("SELECT e FROM usms.emp_info e", EmpInfo.class).getResultList());
			 ut.begin();
			  Query q = emm.createQuery("Select A from EmpInfo A where (A.firstName LIKE :name) or (A.EIdInfos.eIdNo LIKE :id)");
			  q.setParameter("name", "%"+id+"%");
			  q.setParameter("id", "%"+id+"%");
			  empList = q.getResultList();
		//	empList.add(em.find(EmpInfo.class, 1));
			System.out.println("reached 3");
			ut.commit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		//	ut.rollback();
		 }
		return empList;
	}
	
	public void saveEmployee(EmpInfo empInfo, EntityManager emm, UserTransaction ut)
	{
		//EntityTransaction et = emm.getTransaction();
		
		System.out.println("reached 4");
		
		try {
			System.out.println("reached 5");
			//empList.addAll(em.createQuery("SELECT e FROM usms.emp_info e", EmpInfo.class).getResultList());
			//et.begin();
			ut.begin();
	  			emm.persist(empInfo);
				emm.flush();
				System.out.println(empInfo.getEmpNo());
				System.out.println("reached 3");
		   	ut.commit();
		   
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//et.rollback();
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
//	public EmpInfo fetchEmpByPK() {
//		
//		 
//		
//	}
	
	//------------------ Update Employee DAO Function-----------------------------
  


	public void updateEmployee(EmpInfo empInfo, EntityManager emm,
			UserTransaction ut) {
		System.out.println("reached on update DAO function");
		try {
			ut.begin();
			emm.merge(empInfo);
			ut.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// et.rollback();
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
        
      //------------------ Update Employee DAO Function-----------------------------
        
}