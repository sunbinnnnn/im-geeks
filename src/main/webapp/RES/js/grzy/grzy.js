Grzy = function() {

	var thisObj = this;

	this.constant = {
		resContext : "",
		context : ""
	};

	this.setOverallVeriable = function(resContext, context) {
		thisObj.constant.resContext = resContext;
		thisObj.constant.context = context;
	};

	this.init = function(resContext, context) {
		thisObj.setOverallVeriable(resContext, context);
		// 绑定事件
		$("#savegrxx").bind("click", function() {
			thisObj.saveGrxx();
		});
		
		$("#requireUser").bind("click",function(){
			thisObj.requireUser();
		});
		
		$("#saveskill").bind("click",function(){
			thisObj.saveskill();
		});
		
		$("#edituserskill").bind("click",function(){
			thisObj.edituserskill();
		});
		
		$("#saveworkhistory").bind("click",function(){
			thisObj.saveworkhistory();
		});
		
		$("#showlabel").bind("click",function(){
			thisObj.showLabel();
		});
		$("#saveeditworkhistory").bind("click",function(){
			thisObj.saveeditworkhistory();
		});
		
		$("#saveEdu").bind("click",function(){
			thisObj.saveEdu();
		});
		
		$("#saveeditEdu").bind("click",function(){
			thisObj.saveeditEdu();
		});

	};
	
	this.saveeditEdu=function(){
		var edu_id = $("#edu_id").val();
		var edu_name = $("#edu_name_edit").val();
		var edu_college = $("#edu_college_edit").val();
		var edu_begindate = $("#edu_begindate_edit").val();
		var edu_enddate = $("#edu_enddate_edit").val();
		var edu_describle = $("#edu_describle_edit").val();
		var data = {id:edu_id,institution:edu_name,major:edu_college,begintime:edu_begindate,endtime:edu_enddate,introduce:edu_describle};
		$.ajax({
			url:thisObj.constant.context + '/user/updatetUserEdu.htm',
			type:'post',
			data:data,
			dataType: "json",
			success:function(data){
				if(data.flag){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			}
		});
		
	}
	
	this.saveEdu=function(){
		var edu_name = $("#edu_name").val();
		var edu_college = $("#edu_college").val();
		var edu_begindate = $("#edu_begindate").val();
		var edu_enddate = $("#edu_enddate").val();
		var edu_describle = $("#edu_describle").val();
		var data = {institution:edu_name,major:edu_college,begintime:edu_begindate,endtime:edu_enddate,introduce:edu_describle};
		$.ajax({
			url:thisObj.constant.context + '/user/insertuseredu.htm',
			type:'post',
			data:data,
			dataType: "json",
			success:function(data){
				if(data.flag){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			}
		});
		
	};
	
	this.saveworkhistory = function(){
		var company = $("#work_company").val();
		var position = $("#work_position").val();
		var begindate = $("#begindate").val();
		var enddate = $("#enddate").val();
		var describle = $("#work_describle").val();
		if (sdxUtil.isEmpty(company) || $.trim(company) == '') {
			alert("请输入公司名称！");
			return false;
		}
		if (sdxUtil.isEmpty(position) || $.trim(position) == '') {
			alert("请输入岗位！");
			return false;
		}
		if (sdxUtil.isEmpty(begindate) || $.trim(begindate) == '') {
			alert("请输入开始日期！");
			return false;
		}
		if (sdxUtil.isEmpty(enddate) || $.trim(enddate) == '') {
			alert("请输入结束日期！");
			return false;
		}
		if (sdxUtil.isEmpty(describle) || $.trim(describle) == '') {
			alert("请输入项目描述！");
			return false;
		}
		var data ={companyname:company,worktitle:position,begintime:begindate,endtime:enddate,workhistory:describle};
		$.ajax({
			url : thisObj.constant.context + '/user/insertworkhistory.htm',
			type : "post",
			data : data,
			async: false,
			dataType: "json",
			success : function(data) {
				if(data.flag){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			}
		});
	};
	
	this.edituserskill = function(){
		var id = $("#edit_id").val();
		var skillname = $("#edit_skillname").val();
		var scorename = $("#edit_scorename").val();
		if (sdxUtil.isEmpty(skillname) || $.trim(skillname) == '') {
			alert("请输入技能！");
			return false;
		}
		if (sdxUtil.isEmpty(scorename) || $.trim(scorename) == '') {
			alert("请输入熟练度！");
			return false;
		}
		var data ={id:id,userskill:skillname,proficiency:scorename};
		$.ajax({
			url : thisObj.constant.context + '/user/edituserskill.htm',
			type : "post",
			data : data,
			async: false,
			dataType: "json",
			success : function(data) {
				if(data.flag){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			}
		});
		
		
	};
	
	this.saveskill = function(){
		var skillname = $("#iskillname").val();
		var scorename = $("#iscorename").val();
		if (sdxUtil.isEmpty(skillname) || $.trim(skillname) == '') {
			alert("请输入技能！");
			return false;
		}
		if (sdxUtil.isEmpty(scorename) || $.trim(scorename) == '') {
			alert("请输入熟练度！");
			return false;
		}
		var data ={userskill:skillname,proficiency:scorename};
		$.ajax({
			url : thisObj.constant.context + '/user/insertuserskill.htm',
			type : "post",
			data : data,
			async: false,
			dataType: "json",
			success : function(data) {
				if(data.flag){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			}
		});
	};

	this.requireUser = function(){
		$.ajax({
			url : thisObj.constant.context + '/user/requireuser.htm',
			type : "post",
			data : null,
			async: false,
			dataType: "json",
			success : function(data) {
				if(data.flag){
					var obj =data.other;
					$("#nickname").val(obj.user.username);
					$("#realname").val(obj.user.realname);
					$("#tele").val(obj.user.phone);
					$("#qq").val(obj.user.qq);
					$("#addr").val(obj.user.address);
					$("#github").val(obj.user.url);
					$("#github").val(obj.user.url);
					$("#work").val(obj.user.job);
					$("#grjj").val(obj.profile);
				}else{
					alert(data.message);
				}
				
			}
		});
	};
	
	this.saveGrxx = function() {
		var nickname = $("#nickname").val();
		var realname = $("#realname").val();
		var tele = $("#tele").val();
		var addr= $("#addr").val();
		var qq = $("#qq").val();
		var github = $("#github").val();
		var work = $("#work").val();
		var grjj = $("#grjj").val();

		if (sdxUtil.isEmpty(nickname) || $.trim(nickname) == '') {
			alert("请输入昵称！");
			return false;
		}

		if (sdxUtil.isEmpty(realname) || $.trim(realname) == '') {
			alert("请输入真实姓名！");
			return false;
		}
		
		if (sdxUtil.isEmpty(tele) || !sdxUtil.isNumeric(tele)) {
			alert("请输入手机号");
			return false;
		}

		if (!sdxUtil.isNumeric(qq)) {
			alert("请输入QQ号");
			return false;
		}

		if (sdxUtil.isEmpty(addr) || $.trim(addr) == '') {
			alert("请输入居住地址！");
			return false;
		}
		
		if (sdxUtil.isEmpty(github)) {
			alert("请输入github");
			return false;
		}

		if (sdxUtil.isEmpty(work)) {
			alert("请输入职位！");
			return false;
		}
		if (sdxUtil.isEmpty(grjj) || $.trim(grjj) == '') {
			alert("请输入个人简历");
			return false;
		}
		$.ajax({
			url : thisObj.constant.context + '/user/updateuser.htm',
			type : "post",
			data : {"username":nickname,"realname":realname,"phone":tele,"qq":qq,"address":addr,"url":github,"job":work,"grjj":grjj},
			async: false,
			dataType: "json",
			success : function(data) {
				if(data.flag){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			}
		});

	};
	
	this.saveeditworkhistory= function(){
		var id = $("#work_id_edit").val();
		var worktitle = $("#work_position_edit").val();
		var companyname = $("#work_company_edit").val();
		var begindate = $("#begindate_edit").val();
		var enddate = $("#enddate_edit").val();
		var workdescrible = $("#work_describle_edit").val();
		$.ajax({
			url : thisObj.constant.context + '/user/updatetworkhistory.htm',
			type : "post",
			data : {"id":id,"worktitle":worktitle,"companyname":companyname,"begintime":begindate,"endtime":enddate,"workhistory":workdescrible},
			async: false,
			dataType: "json",
			success : function(data) {
				if(data.flag){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			}
		});
	};

};

var grzy = new Grzy();