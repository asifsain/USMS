package com.usms.helper;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
@FacesComponent("coreservlets.DateComponent1")
public class InputDate extends UIInput implements NamingContainer {

   public InputDate() {
      System.out.println("date constructed");
   }

   public String getFamily() {
      return "javax.faces.NamingContainer";
   }

   @Override
   public void encodeBegin(FacesContext context) throws IOException {
	   Calendar cal = Calendar.getInstance();
       Date date = (Date) getValue();
      if(date!=null){
    	cal.setTime(date);
       int day =cal.get(Calendar.DATE);
       int month =cal.get(Calendar.MONTH) + 1;
       int year = cal.get(Calendar.YEAR) + 2010;
       UIInput dayComponent = (UIInput) findComponent("day");
       UIInput monthComponent = (UIInput) findComponent("month");
       UIInput yearComponent = (UIInput) findComponent("year");
       dayComponent.setValue(day);
       monthComponent.setValue(month);
       yearComponent.setValue(+year);
       }
       super.encodeBegin(context);
   }

   @Override
   public Object getSubmittedValue() {
      return this; // Any non-null value will do
     
   }

   @Override
   protected Object getConvertedValue(FacesContext context, Object newSubmittedValue)
           throws ConverterException {
      UIInput dayComponent = (UIInput) findComponent("day");
      UIInput monthComponent = (UIInput) findComponent("month");
      UIInput yearComponent = (UIInput) findComponent("year");
      String day1 = (String)dayComponent.getSubmittedValue();
      int day =Integer.parseInt(day1);
      String month1 = (String)monthComponent.getSubmittedValue();
      int month = Integer.parseInt(month1);
      String year1 = (String) yearComponent.getSubmittedValue();
      int year = Integer.parseInt(year1);
      if (isValidDate(day, month, year)) 
         return new Date(year - 1900, month - 1, day);
      
      else
         throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
              "Invalid date", "Invalid date"));
   }

   private static boolean isValidDate(int d, int m, int y) {
      if (d < 1 || m < 1 || m > 12) {
         return false;
      }
      if (m == 2) {
         if (isLeapYear(y)) {
            return d <= 29;
         } else {  
            return d <= 28;
         }
      } else if (m == 4 || m == 6 || m == 9 || m == 11) {
         return d <= 30;
      } else {
         return d <= 31;
      }
   }

   private static boolean isLeapYear(int y) {
      return y % 4 == 0 && (y % 400 == 0 || y % 100 != 0);
   }
}