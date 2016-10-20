Regeister = function(){
	var thisObj = this;
	
	this.constant = {
		resContext : "",
		context : ""
	};
	
	this.setOverallVeriable = function(resContext,context){
		thisObj.constant.resContext = resContext;
		thisObj.constant.context = context;
	};
	
	this.init = function(resContext,context){
		thisObj.setOverallVeriable(resContext,context);
		
		$("#regeister").bind("click",function(){
			thisObj.regeister();
		});
	};
	
	this.regeister=function(){
		var username = $("#username").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var confirmpwd = $("#confirmpwd").val();
		if( sdxUtil.isEmpty(username) || $.trim(username) == '邮箱'){
			alert("请输入用户名");
			return false;
		}
		if(!sdxUtil.isEmail(email) || $.trim(email) == '邮箱'){
			alert("请输入邮箱");
			return false;
		}
		
		if(sdxUtil.isEmpty(password) || $.trim(password) == '密码'){
			alert("请输入密码");
			return false;
		}
		
		if(sdxUtil.isEmpty(confirmpwd) || $.trim(confirmpwd) == '密码'){
			alert("请输入确认密码");
			return false;
		}
		
		if($.trim(password)!=$.trim(confirmpwd)){
			alert("两次输入不相同");
			return false;
		}
		
		$.ajax({
			url: thisObj.constant.context + '/regeisterinfo.htm',
			type: 'POST',
			async: false,
			dataType: "json",
			data: {"username" : username,"email" : email,"password" : password},
			success: function(data){
				if(data.flag){
					window.location.href=thisObj.constant.context+"/user/wsxx.htm";
				} else {
					alert(data.message);
					return false;
				}
			},
			error: function(){
				alert("对不起，您的操作失败，请重新操作！");
				return false;
			}
		});	
	};
	
	
};

var regeister = new Regeister();