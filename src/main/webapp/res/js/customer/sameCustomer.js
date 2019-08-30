function getCustomerDetail(id,customerId){
	$("#cId").val(id);
	var sign = $("#sign").val();
	$("#custom").val(customerId);
	$("div.customerSearchBar1 ul > li").removeClass("active");
	$("#oneli").addClass("active");
	$("#tr"+id).siblings().removeClass('active222');
	$("#tr"+id).addClass('active222')
	var detailCustomerFrameUrl = "/accCustomer/getCustomerDetail?id="+id+"&sign="+sign;;
	var releCustomerFrameUrl = "/accCustomer/getLinkMan?customerId="+customerId;
	$("#detailCustomerFrame").attr("src", detailCustomerFrameUrl);
	$("#releCustomerFrame").attr("src", releCustomerFrameUrl);
}
function functionReleChange (url) {
	var sign = $("#sign").val();
	var custom = $("#custom").val();
	url = url+custom+"&sign="+sign;
	$("#releCustomerFrame").attr("src", url);
}