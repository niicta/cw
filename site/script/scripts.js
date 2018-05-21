$('document').ready(function(){
	$('.new-template-button').click(function(){
		showNewTemplateForm();
	})
});

function showNewTemplateForm(){
	setTimeout(function(){
		$(".create-template-form-block").fadeTo(0, 0).css('display', 'flex').fadeTo(0, 1);
		$('.cancel-create-template-button').click(function(){
			closeNewTemplateForm();
		});
		
	}, 300);
}

function closeNewTemplateForm(){
		$(".create-template-form-block").fadeTo(0.3, 0);
		setTimeout(function(){$(".create-template-form-block").hide()}, 300);
}