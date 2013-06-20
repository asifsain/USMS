    var   pasportDateValidate=function() {
    	  var iDate=document.getElementById('centreContentForm:PASPORT_ISUE_DTInputDate').value;
	      var issueDate=new Date(iDate);
	      var expiryDate=new Date(document.getElementById("centreContentForm:PASPORT_EXP_DTInputDate").value);
	        if(expiryDate<issueDate || iDate.length==0)
	         {
	         document.getElementById("centreContentForm:PASPORT_EXP_DTInputDate").value="";
	    	 alert("Expiry Date Should be greater than Issue Date");
	    	
	    	return false;
	    	
	         }
	        
	      
	     return true;
	    
	 
    };
 
   var  laborDateValidate=function() {
	      var iDate=document.getElementById('centreContentForm:LABOUR_ISSUE_DTInputDate').value;
	      var issueDate=new Date(iDate);
	      var expiryDate=new Date(document.getElementById("centreContentForm:LABOUR_EXP_DTInputDate").value);
      if(expiryDate<issueDate || iDate.length==0)
         {
          document.getElementById("centreContentForm:LABOUR_EXP_DTInputDate").value="";
  	      alert("Expiry Date Should be greater than Issue Date");
  	      return false;
  	     }
      
    
   return true;
   

    };
 
   var visaDateValidate=function() {
	   var iDate=document.getElementById('centreContentForm:VISA_ISUE_DTInputDate').value;
       var issueDate=new Date(iDate);
       var expiryDate=new Date(document.getElementById("centreContentForm:VISA_EXP_DTInputDate").value);
     if(expiryDate<issueDate || iDate.length==0)
        {
        document.getElementById("centreContentForm:VISA_EXP_DTInputDate").value="";
    	alert("Expiry Date Should be greater than Issue Date");
    	
    	return false;
    	
        }
     return true;
    
 
};



var eIdDateValidate=function() {
	var iDate=document.getElementById('centreContentForm:EID_ISSUE_DTInputDate').value;
    var issueDate=new Date(iDate);
    var expiryDate=new Date(document.getElementById("centreContentForm:EID_EXP_DTInputDate").value);
     if(expiryDate<issueDate ||iDate.length==0)
       {
        document.getElementById("centreContentForm:EID_EXP_DTInputDate").value="";
    	alert("Expiry Date Should be greater than Issue Date");
   	    return false;
   	   }   
    return true;
   

};

var mCardDateValidate=function() {
	 var iDate=document.getElementById('centreContentForm:M_ISUE_DTInputDate').value;
    var issueDate=new Date(iDate);
    var expiryDate=new Date(document.getElementById("centreContentForm:M_EXP_DTInputDate").value);
    if(expiryDate<issueDate || iDate.length==0)
       {
       document.getElementById("centreContentForm:M_EXP_DTInputDate").value="";
   	  alert("Expiry Date Should be greater than Issue Date");
   	
   	return false;
   	
       }
    return true;
   

};

function disablementFunction(day){
	 
	  var iDate=document.getElementById('centreContentForm:M_ISSUE_DTInputDate').value;
      var  curDt=new Date(iDate);
  //   alert(curDt.getTime() - day.date.getTime());
    if (curDt==undefined){

        curDt = day.date.getDate;

    }

    if (curDt.getTime() - day.date.getTime()<=0)
    	{ 
    	 return true;
    	
    	}
    else return false; 

};

function disabledClassesProv(day){
	    var iDate=document.getElementById('centreContentForm:M_ISSUE_DTInputDate').value;
        var  curDt=new Date(iDate);
        if (curDt.getTime() - day.date.getTime()>0) return 'rf-cal-boundary-day';
           var res = '';
         if (day.isWeekend) res+='weekendBold';
         if (day.day%3==0) res+='everyThirdDay';
    return res;

}

var salaryProcess=function() {
	 var iDate=document.getElementById('centreContentForm:fromDateInputDate').value;
     var issueDate=new Date(iDate);
     var expiryDate=new Date(document.getElementById("centreContentForm:toDateInputDate").value);
    if(expiryDate<issueDate || iDate.length==0)
       {
       document.getElementById("centreContentForm:toDateInputDate").value="";
  	   alert("To Date Should be greater than From Date");
    	return false;
  	
      }
   return true;
  

};


