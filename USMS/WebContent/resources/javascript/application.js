function checkEmail(obj) {
    var email =obj; 
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!filter.test(email.value)) {
    alert('Please provide a valid email address');
    obj.value="";
    obj.focus();
    return false;
    }
    return true;
}  

function validateFields(formObjSelector){
	var error = false;
	  alert(formObjSelector);
	// We need to make sure that only 1 form is contained. This has been done to counter multi instances of same form in 
	// Fancybox screens like programVersion.
	  $(".inputText").each(function(){
		  alert("Hi");
				var obj = this;
				var val = obj.value;
				alert(val);
				if(val==""){
					  alert("in th valueHi");
					  $(this).css("background-color","red");
					alert("in the css");
					$(this).bind('focus',function () {
						$(this).css("background-color","yellow");
						alert("in the bind1 css");
					});
					$(this).bind('blur',function () {
						alert("in the bind2 css");
						if(this.value=='')
							$(this).css("background-color","red");
					
						else
							$(this).css("background-color","yellow");
					});
					alert(error);
					error = true;
				}else
					$(this).css("background-color"," ");
				obj.value = val;
			});
	if(error){
	
		alert("Please Enter Required value");
		return false;
	}
	return true;
}


function clearForm(formObjSelector) {
	jQuery(formObjSelector).each(function(index){  
		for(var i=0;this.length > i;i++){
			this.reset();
			var elements = this.elements; 
			alert(elements);
			for(i=0; i<elements.length; i++) {
				if(jQuery(elements[i]).hasClass("ignore"))
					continue;
				field_type = elements[i].type.toLowerCase();  
				switch(field_type) {
					case "text": 
					case "textarea":
						elements[i].value = ""; 
						break;
					case "radio":
					case "checkbox":
			  			if (elements[i].checked) {
			   				elements[i].checked = false; 
						}
						break;
					case "select-one":
					case "select-multi":
			            		elements[i].selectedIndex = 0;
						break;
					default: 
						break;
				}
		    }
		}
	});
	 jQuery(".outputField").empty();
	jQuery(formObjSelector + ' table.radioField input').each(function(index){
		var zerothRadio = new Number(this.id.substring(this.id.lastIndexOf(":")+1));
		this.checked = zerothRadio == 0;
		});
	 
}

      $(".inputText").focus(function (){
    	   
    	  
    	  $(this).css("background-color","#FFEEAA");
    	  $(this).css("border","2px solid #AA88FF");
    	  $(this).blur(function (){
    		  $(this).css("background-color","");
    		  $(this).css("border","");
    		
    	     });
     });
      
 function checkAllCheckboxesInTable(obj){
	   if(obj.checked){
           $(".checkBoxClass").each(function()
       	      {  
        	    if( $(this).prop('disabled')==false)       
        	          $(this).prop('checked', true); 
       		   });
             }  
	      else {
	    	  $(".checkBoxClass").each(function()
	    	      {  
	    	         $(this).prop('checked', false); 
	    	      });
	          };
     
   };
   

   


  