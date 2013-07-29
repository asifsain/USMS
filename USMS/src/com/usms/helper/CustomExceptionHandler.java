package com.usms.helper;

import java.util.Iterator;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import com.usms.managedbeans.application.MenuBean;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
   
	private ExceptionHandler wrapped;
	MultiPageMessagesSupport message=new MultiPageMessagesSupport();
	
	 public CustomExceptionHandler(ExceptionHandler wrapped) {
		    this.wrapped=wrapped;
		   }
	
	@Override
	    public ExceptionHandler getWrapped() {
	
		return wrapped;
	  }
	
	   @Override
	   public void handle() throws FacesException {
	    Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
	    MenuBean menuBean=new MenuBean();
	    while (iterator.hasNext()) {
	      ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
	      ExceptionQueuedEventContext context = (ExceptionQueuedEventContext)event.getSource();
	 
	      Throwable throwable = context.getException();
	      ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	      FacesContext fc = FacesContext.getCurrentInstance();
	      NavigationHandler navigationHandler = fc.getApplication().getNavigationHandler(); 
	         try {
	        
	          Flash flash = fc.getExternalContext().getFlash();
	          
	          // Put the exception in the flash scope to be displayed in the error 
	          // page if necessary ...
	            flash.put("errorDetails", throwable.getMessage());
	            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,throwable.getMessage(), throwable.getMessage());
		       	FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		       	fc.getExternalContext().getFlash().setKeepMessages(true);    
	            System.out.println("the error is put in the flash: " + throwable.getMessage());
	            message.saveMessages(fc);
	            if(throwable instanceof ViewExpiredException)
	                navigationHandler.handleNavigation(fc, null, "/login?faces-redirect=true");
	            else
	               navigationHandler.handleNavigation(fc, null, "/error?faces-redirect=true");
	               
	              fc.renderResponse();
	              } finally {
	            iterator.remove();
	         }        
	      }
	    
	    // Let the parent handle the rest
	    getWrapped().handle();
	  }
	}


