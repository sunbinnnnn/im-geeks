Login = function(){
	
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
		// 绑定事件
		$("#loginbutton").bind("click",function(){
			thisObj.login();
		});
		
		$("#logoutlink").bind("click",function(){
			thisObj.logout();
		});
	};
	
	this.login = function(){
		var email = $("#email").val();
		var password = $("#password").val();
		if(!sdxUtil.isEmail(email) || $.trim(email) == '邮箱'){
			$.globalMessenger().post("请输入邮箱!"); 
			return false;
		}
		
		if(sdxUtil.isEmpty(password) || $.trim(password) == '密码'){
			$.globalMessenger().post("请输入密码!"); 
			return false;
		}
		
		$.ajax({
			url: thisObj.constant.context + '/login.htm',
			type: 'POST',
			async: false,
			dataType: "json",
			data: {"email" : email,"password" : password},
			success: function(data){
				if(data.flag){
					/**sdxUtil.ajaxLoadPage(thisObj.constant.context + '/loadLoginInfo.htm', "", "loginPanel", function(){
						// 绑定事件
						$("#logoutlink").bind("click",function(){
							thisObj.logout();
						});
					});*/
					var rurl = data.message;
					if(rurl){
						window.location.href=rurl;
					}else{
						window.location.href=thisObj.constant.context+"/index.htm";
					}
				} else {
					alert(data.message);
					return false;
				}
			},
			error: function(){
				$.globalMessenger().post("对不起操作失败"); 
				return false;
			}
		});	
	};
};


var login = new Login();