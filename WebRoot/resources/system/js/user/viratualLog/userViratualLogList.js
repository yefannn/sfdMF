$(function(){
	$(".ht_list tr").mouseover(function(){
		$(this).addClass("tr_bg");
	});
	$(".ht_list tr").mouseout(function(){
		$(this).removeClass("tr_bg");
	});
	$(".pro_ss dt span.span_down").click(function(){
		$(this).hide();
		$(this).next(".span_up").show();
		$(this).parent().nextAll("dd").hide();
		$(this).parent().parent().next(".pro_ss_btn").hide();
	});
	$(".pro_ss dt span.span_up").click(function(){
		$(this).hide();
		$(this).prev(".span_down").show();
		$(this).parent().nextAll("dd").show();
		$(this).parent().parent().next(".pro_ss_btn").show();
	});
});	

//searchIntegralLogForm的提交
function searchSubmit(){
	document.getElementById("searchIntegralLogForm").submit();
}
//searchIntegralLogForm重置
function resetForm(){
	document.getElementsByName("name")[0].value="";
	document.getElementsByName("status")[0].checked=true;
	document.getElementsByName("beginDate")[0].value="";
	document.getElementsByName("endDate")[0].value="";
}

//增加积分
function userIntegralAdd(contextPath){
	window.location.href = contextPath + "/system/user/amountLog/modify";
}

//分页跳转
function goPage(pageNum){
	$("input[name='pageNum']").val(pageNum);
	document.getElementById("searchIntegralLogForm").submit();
}


