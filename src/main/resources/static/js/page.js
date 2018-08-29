$(document).ready(function(){
	/*==============tab=============*/
	$(".tab_ul li").click(function(){
		$(this).addClass("new").siblings().removeClass("new");
		$(this).parent().next("div").find(".box").eq($(this).index()).show().siblings().hide();
	})
	
	$(".title li").click(function(){
		$(this).removeClass("link").siblings().addClass("link");
		$(this).parent().next("div").find(".data").eq($(this).index()).show().siblings().hide();
	})
	
	/*===============优化下拉列表效果===================*/
	// 初始化下拉选择
	$(".m-select").each(function() {
		var val = $(this).val();
		$(this).siblings(".m-select-result").text(val);
	})	
	// 通用下拉选择
	$(".m-select").change(function() {
		var val = $(this).val();
		$(this).siblings(".m-select-result").text(val);
	})
	
	/*==============单选======================*/
	$(".fill_table tr td ul li").click(function(){
			if($(this).children(".chose_no").hasClass("chose_yes")){
				$(this).children(".chose_no").removeClass("chose_yes");
			}else{
				$(this).children(".chose_no").addClass("chose_yes").parents("li").siblings("li").children(".chose_no").removeClass("chose_yes");
			}
	})
	
	/*==============星星打分===============*/
	$(".publish_box .info .start li").click(function(){
			if($(this).hasClass("on")){
				$(this).removeClass("on");	
			}else{
				$(this).addClass("on");
			}
	})
	
	
	
	
	
	
})