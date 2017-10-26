$(function(){
	
	window.document.onkeydown=SubmitOrHidden;//当有键按下时执行函数
	
	$("#login").click(function(){
		 var user = $("#userName").val();
		 var psd = $("#password").val();
		login(user,psd);
	});
	
	//登陆
	function login(user,psd){
		$.ajax({
				type : "get",
				url : "user/login.action",
				data : {
					account :user,
					password :psd
				},
				success:function(response, status, xhr){
					document.getElementById("enter").click();
				},
				error:function(response, status, xhr){
					alert("用户名或者密码错误！");
				}
			});
		
		
		
	}
	//通过enter登陆
	var SubmitOrHidden = function(evt){
	    evt = window.event || evt;
	    if(evt.keyCode == 13){//如果取到的键值是回车
	    	$("#login").trigger("click");  
	     }else{
	        //其他键  dosomething
	    }
	                 
	}
});