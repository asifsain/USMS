
function changeCenterPage(page_name, menu_item_id) {
	if($("#" + menu_item_id).css('visibility') == 'visible'){
		
		var clickEvent = jQuery.Event('click');
	
		$J("#" + menu_item_id).trigger(clickEvent);
	
		$J("#" + menu_item_id).addClass('ui-selected');
	
		$J("#" + menu_item_id).siblings('li').each(function() {
			if($J(this).hasClass('ui-selected'))
			$J(this).removeClass('ui-selected');
		});
	
		changePage(page_name);
	}

	
}

function validateNumber(Obj){
	if(isNaN(Obj.value)){
		alert('Only Number is Allowed');
		Obj.value=null;
		return;
	}
	
}
