$( document ).ready(function() {
	$(".loader-container").hide();
	
	$(".home-icon-card").click(function(){
		window.location.href = $(this).attr("data-href"); 
	});
	
});