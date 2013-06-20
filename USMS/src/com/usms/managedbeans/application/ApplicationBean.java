package com.usms.managedbeans.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ApplicationBean", eager = true)
@SessionScoped
public class ApplicationBean {


	public Properties applicationPropreties = new Properties();

	  {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
			System.getProperty("user.home");
			new File(".").listFiles();
			// load a properties file
		   applicationPropreties.load(new FileInputStream(new File(System.getProperty("user.home")+ File.separator+ "application.properties")));
			//added by tariq 
         //   applicationPropreties.load(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/application.properties"));
			// get the property value and print it out

		} catch (IOException ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
	}

}
