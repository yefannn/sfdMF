$(document).ready(function() {
	/*全部复选框*/
	$(".checkAll").live("click",function(){	
	$main = $(this);
	if($main.find("input").is(":checked")){
		
		//下级都被选中
		$main.find(".check").addClass("checked");
		$main.parents(".tabList").find("input").attr("checked",true);
		$main.parents(".tabList").find(".check").addClass("checked");
	}else{
		//下级都取消选中
		$main.find(".check").removeClass("checked");
		$main.parents(".tabList").find(":checkbox").attr("checked",false);
		$main.parents(".tabList").find(".check").removeClass("checked")
	}
	});
	
	
	
	}
)

// 全部取消
function deleteAll(){
	var id = "";
	$(".tabList td .check input").each(function(){
		if($(this).is(":checked")){
			if(id == ""){
				id = id + $(this).val()
			}else{
				id= id + ","+$(this).val();
			}
		}
	})
	if(id == ""){
		falert("请先选择订单！");
	}else{
		econfirm('是否确认删除？',deleteOrder,[$("#ctxpath").val(),id],null,null);
	}
}
// 搜索表单提交
function formSubmit(){
	$("#searchStoreRefundOrder").submit();
}

// 搜索表单重置
function formReset(){
	$("#orderId").val("");
	$("#userName").val("");
	$("#beginDate").val("");
	$("#endDate").val("");
}

// 订单列表的JS操作
function xxx(ctxpath,orderId,status,shippingStatus,userApp){
	//确认收货
	if(status == "3" && shippingStatus == "true"){
		econfirm('是否确认收货？',queRenShouHuo,[ctxpath,orderId],null,null);
	}else if(status == "2"){
		window.location.href = ctxpath + "/web/caiGouOrder/userByNow/"+orderId;
	}else{
	// 发送请求
		document.write("<form action='"+ctxpath+"/store/order/orderListHandel' method='post' name='formx1' style='display:none'>");
		document.write("<input type='hidden' name='orderId' value='"+orderId+"' />");
		document.write("<input type='hidden' name='status' value='"+status+"' />");
		document.write("<input type='hidden' name='shippingStatus' value='"+shippingStatus+"' />");
		document.write("<input type='hidden' name='userApp' value='"+userApp+"' />");
		document.write("<input type='hidden' name='jiaGeType' value='"+1+"' />");
		document.write("<input type='hidden' name='faHuoType' value='"+1+"' />");
		document.write("</form>");
		document.formx1.submit();
	}
}

//确认收货
function queRenShouHuo(ctxpath,orderId){
	window.location.href = ctxpath + "/store/coreOrder/confirm/"+orderId;
}
//分页跳转
function goPage(pageNum){
	$("input[name='pageNum']").val(pageNum);
	document.getElementById("searchStoreRefundOrder").submit();
}



function deleteOrder(contextPath,id){
	window.location.href = contextPath + "/store/gongYing/orderDelete/"+id;
}