package com.usms.managedbeans.application;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;


@ManagedBean
@SessionScoped
public class MenuBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String test = "wine";
	private String pageName ;
	private int count = 0;
	private String textIndent = "60px";
	//private String pageName = "Nothing is moving";
	//private String pageName;
	//mode is adde by Tariq
	private  String mode;  
	
	
	public String getTextIndent() {
		return textIndent;
	}

	public void setTextIndent(String textIndent) {
		this.textIndent = textIndent;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		System.out.println("set page name invoked");
		System.out.println(pageName);
		this.pageName = pageName;
	}

	public void changePageFile(ActionEvent event){
		this.pageName = "/application/register_employee/file.xhtml";
	}
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		System.out.println("mode="+mode);
		this.mode = mode;
	}

	public void doAction(javax.faces.event.ActionEvent event)
	{
		test = count++ + "man this is really sick!!!";
		System.out.println(test);
		//return "";
	}
	
	public void logout(ActionEvent event)
	{
		HttpSession session;
		
		if(FacesContext.getCurrentInstance().getExternalContext().getSession(false) instanceof HttpSession){
			session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
			if(session != null)
			{
				session.invalidate();
			}
		}
	}
	
	public void login(ActionEvent event)
	{
		HttpSession session;
		
		if(FacesContext.getCurrentInstance().getExternalContext().getSession(false) instanceof HttpSession){
			session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
			if(session != null) 
			{
				session.invalidate();
			}
		}
		
		
	}

}
