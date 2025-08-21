/**
 * 
 */
console.log("testing");

//check the side bar visibility
const BarVisibility=()=>{
	if($(".sidebar").is(":visible")){
		//if visible then do this to hide the bar when click on cross or bar
	  $(".sidebar").css("display","none");
	  $(".content").css("margin-left","2%");
	}
	else{
		//if not visible then do this to show the bar when click on cross or bar again
	  $(".sidebar").css("display","block");
	  $(".content").css("margin-left","15%");

	}
};