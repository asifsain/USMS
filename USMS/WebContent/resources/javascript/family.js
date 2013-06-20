var alpha = true;

//centreContentForm:familyDetailsForm

//"centreContentForm:cb_hasSpouse",
var spouseElements = Array("centreContentForm:it_spouse_firstName",
"centreContentForm:it_spouse_middleName",
"centreContentForm:it_spouse_lastName",
"centreContentForm:it_spouse_passportNo",
"ful_spouse_passportUpload",
"centreContentForm:it_spouse_miCardNo",
"ful_spouse_miUpload");
//
//"centreContentForm:cb_hasChild1",
var child1Elements = Array("centreContentForm:it_child1_firstName",
"centreContentForm:it_child1_middleName",
"centreContentForm:it_child1_lastName",
"centreContentForm:it_child1_passportNo",
"ful_child1_passportUpload",
"centreContentForm:it_child1_miCardNo",
"ful_child1_miCardUpload");
//
//"centreContentForm:cb_hasChild2",
var child2Elements = Array("centreContentForm:it_child2_firstName",
"centreContentForm:it_child2_middleName",
"centreContentForm:it_child2_lastName",
"centreContentForm:it_child2_passportNo",
"ful_child2_passportUpload",
"centreContentForm:it_child2_miCardNo",
"ful_child2_miCardUpload");

var checkboxElements = Array("centreContentForm:cb_hasChild1", "centreContentForm:cb_hasChild2");



var spouseSelected = function(obj) {
	
	var allElements = spouseElements.concat(child1Elements, child2Elements, checkboxElements);
	if(obj.checked)
	{
		$J.each( allElements, function( index, value ) {  
			value = value.replace(/:/g, "\\:");
			$J("#" + value).removeAttr("disabled");
		});
	}
	else
	{
		$J.each( allElements, function( index, value ) {  
			value = value.replace(/:/g, "\\:");
			$J("#" + value).attr("disabled","true");			
		});
	}
};

var child1Selected = function(obj) {
	if(obj.checked)
	{
		$J.each( child1Elements, function( index, value ) {  
			value = value.replace(/:/g, "\\:");
			$J("#" + value).removeAttr("disabled");
		});
	}
	else
	{
		$J.each( child1Elements, function( index, value ) {  
			value = value.replace(/:/g, "\\:");
			$J("#" + value).attr("disabled", "disabled");
		});
	}
};

var child2Selected = function(obj) {
	if(obj.checked)
	{
		$J.each( child2Elements, function( index, value ) {  
			value = value.replace(/:/g, "\\:");
			$J("#" + value).removeAttr("disabled");
		});
	}
	else
	{
		$J.each( child2Elements, function( index, value ) {  
			value = value.replace(/:/g, "\\:");
			$J("#" + value).attr("disabled", "disabled");
		});
	}
};

