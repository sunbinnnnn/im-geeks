$(function (){
   window['dandan']={};
   var printWall = document.getElementById(".my_show");
   var ing_user;//当前用户
   //浏览器
   function liulanqi(){
	  var h=$(window).height();
	  var w=$(window).width();
	  $("#top").width(w);
	  $("#foot").html(h);
	 
	  $(".box").height(h-135);
	 /* $("#mid_con").height(h-255);*/
	  $(".right_box").height(h-134);
	 
	  
	  if($(".box").height()<350){
		$(".box").height(350)
		 }
	 /* if($("#mid_con").height()<230){
		 $("#mid_con").height(230)
		  }*/
	  if($(".right_box").height()<351){
		 $(".right_box").height(351)
		  }
	  /*if($("#mid_say textarea").width()<320){
		  $("#mid_say textarea").width(320)
		  }*/
	 
/*	 if($("#mid_foot").width()<400){
		 $("#mid_foot").width(400)
		 }  */
		  
	  	  
		  
	  if(w<=800){
		  $("#top").width(800);
		  $("#body").width(800)
		   }else{
		  $("#body").css("width","100%")  
		}	  
	  //$("#top").html(b_h);
	  
	  $(".my_show").height($("#mid_con").height()-30);//显示的内容的高度出现滚动条
	  //$(".my_show").scrollTop($(".con_box").height()-$(".my_show").height());//让滚动滚到最底端.在这里不加这句了，没多用，可能还卡
	  
	  //右边的高度
	  r_h=$(".right_box").height()-40*3;
	  $("#right_top").height(r_h*0.25)
	  $("#right_mid").height(r_h*0.45)
	  $("#right_foot").height(r_h*0.3)
	  
   }
   window['dandan']['liulanqi']=liulanqi;
   
 //时间
function mytime(){
   var now=(new Date()).getHours();
    if(now>0&&now<=6){
	  return "午夜好";
    }else if(now>6&&now<=11){
	  return  "早上好";
    }else if(now>11&&now<=14){
	  return "中午好";
    }else if(now>14&&now<=18){
	  return "下午好";
   }else{
	  return "晚上好";
   }
}
window['dandan']['mytime']=mytime;   

//触发浏览器   
$(window).scroll( function() { dandan.liulanqi();  } );//滚动触发
$(window).resize(function(){ dandan.liulanqi(); return false; });//窗口触发
//alert("??????")
dandan.liulanqi();




//ctrl+回车
    $("body").bind('keyup',function(event) {   
         if(event.ctrlKey&&event.keyCode==13){   
            saysay();
        }
		if(!event.ctrlKey&&event.keyCode==13){
			myenter();
			}
    }); 
//发送按钮 
    $("#mid_sand").click(function (){
           	saysay();						   
    })
	 
	

			 
//替换所有的回车换行   
function trim2(content)   
{   
    var string = content;   
    try{   
        string=string.replace(/\r\n/g,"<br />")   
        string=string.replace(/\n/g,"<br />");         
    }catch(e) {   
        alert(e.message);   
    }   
    return string;   
} 	
//替换所有的空格
function trim(content)   
{   
    var string = content;   
    try{   
        string=string.replace(/ /g,"&nbsp;")        
    }catch(e) {   
        alert(e.message);   
    }   
    return string;   
} 	

			 
			 
function myenter(){
    //回车键的时候换行，留以后可以有用
}			 

//显示个数
function user_geshu(){
     var length1=$(".ul_1 > li").length;
     var length2=$(".ul_2 > li").length;
     $(".n_geshu_1").text(length1);
     $(".n_geshu_2").text(length2);	
}
user_geshu()
//alert(length1)

//点击展开会员
$(".h3").click(function (){
	 $(this).toggleClass("click_h3").next(".ul").toggle(600);
});

//鼠标经过会员的时候
function hover_user($this){
  $($this).hover(
    function () {
     $(this).addClass("hover");
    },
    function () {
      $(this).removeClass("hover");
    }
  );
}

//经过输入文本框的时候
$("#texterea").hover(
  function () {
    $(this).addClass("textarea2");
  },
  function () {
    $(this).removeClass("textarea2");
  }
);
//alert($admin_name);
$("#right_foot").html('<p><img src="images/head.jpg" alt="头象" /></p>'+$admin_name);


//过滤所有的空格
function kongge(content)   
{   
    return content.replace(/^\s\s*/, '').replace(/\s\s*$/, '');   
} 
window['dandan']['kongge']=kongge;



/*******************************************************************************************/
//创建新用户
function newuser($this,arr,i,ing){
	var id="user";
	if(ing!=undefined){//创建最近聊天
	   $($this).prepend('<li id="'+id+i+'">'+arr[0]+'</li>');
	   $('#'+id+i).click(function(){title_newuser('title_'+id+ing,arr[0],arr[1],arr[2]); });//给按钮加事件
	}else{//创建好友
	  $($this).append('<li id="'+id+i+'">'+arr[0]+'</li>');
	  $('#'+id+i).click(function(){title_newuser('title_'+id+i,arr[0],arr[1],arr[2]); });//给按钮加事件
	}
	hover_user('#'+id+i);//给经过触发	
	user_geshu();//更新人数
}
window['dandan']['newuser']=newuser;

////更新最近聊天的位置
function ing_my_user($this,arr,i,ing){
	var id="user";
	$("#"+id+i).remove();
	$($this).prepend('<li id="'+id+i+'">'+arr[0]+'</li>');
	$('#'+id+i).click(function(){title_newuser('title_'+id+ing,arr[0],arr[1],arr[2]); });//给按钮加事件
	hover_user('#'+id+i);//给经过触发	
}

//创建标题栏和主控制（原是有一个主控制，忘了，就合在一起了，哈哈）
function title_newuser(id,user,img,roomid){
	  if($("#"+id).length<1){
	  $("#mid_top").append('<div id="'+id+'" class="list"><table border="0" cellspacing="0" cellpadding="0"><tr><td id="zi'+id+'" class="td_user td_user_click">'+user+'</td><td id="zino'+id+'" class="td_hide td_hide_click">X</td></tr></table></div>');
	  //创建完成后给事件
	  $('#'+id).click(function(){title_newuser(id,user,img,roomid); });//给按钮加事件
	  //关闭
	  $("#zino"+id).click(function(){delete_user(id,user,img,roomid); return false });//关闭打开的
	  
	  
	  }else{
	  $("#zino"+id).addClass("td_hide_click");//给自己加样式
	  $("#zi"+id).addClass("td_user_click");//给自己加样式
	  }
	  my_siblings("#"+id);//去掉兄弟样式
	  
	  //创建内容框
	  my_user_con(user,id,roomid);
	  
	  //创建头像
	  my_user_head(user,id,img);
	  
	  ing_user=id;//当前用户
	  
	  $("#right_mid").html("");//清空右边的内容
}

//去掉兄弟的样式
function my_siblings($this){
     $($this).siblings().children().children().children().children().removeClass("td_hide_click td_user_click");
}

//创建右边的头像
function my_user_head(user,id,img){
	if($(".head"+id).length<1){
		if(!img){//头像为空的时候
			img="user_img/0.jpg";
		}
       $("#right_top").append('<div class="head'+id+'"><p><img src="'+img+'" alt="'+user+'" /></p>'+user+'<div>');
	   $(".head"+id).hide();//默认是隐藏，让它有一点效果
	}
	sibli_hide(".head"+id);
}
//隐藏兄弟头像
function sibli_hide($this){
     $($this).show(500,function(){$(".my_show").scrollTop($(".con_box").height()-$(".my_show").height());/*让滚动滚到最底端*/}).siblings().hide(500);//隐藏其他兄弟
}

function init(user,id,roomId) {
		console.info('正在连接服务器，请等待。。。');
	  var val = clientId;
	  if (val) {
	    clientId = val;
	  }
	  if (!firstFlag) {
	    rt.close();
	  }

	  // 创建实时通信实例
	  rt = AV.realtime({
	    appId: appId,
	    clientId: clientId,
	    secure: false
	  });

	  // 监听连接成功事件
	  rt.on('open', function() {
	    firstFlag = false;
	    console.info('服务器连接成功！');

	    // 获得已有房间的实例
	    rt.room(roomId, function(object) {

	      // 判断服务器端是否存在这个 room，如果存在
	      if (object) {
	        room = object;

	        // 当前用户加入这个房间
	        room.join(function() {

	          // 获取成员列表
	          room.list(function(data) {
	        	  console.info('当前 Conversation 的成员列表：'+data);

	            // 获取在线的 client（Ping 方法每次只能获取 20 个用户在线信息）
	            rt.ping(data.slice(0, 20), function(list) {
	            	console.info('当前在线的成员列表：'+list);
	            });

	            var l = data.length;

	            // 如果超过 500 人，就踢掉一个。
	            if (l > 490) {
	              room.remove(data[30], function() {
	            	  console.info('人数过多，踢掉： '+data[30]);
	              });
	            }

	            // 获取聊天历史
//	            getLog(function() {
//	              printWall.scrollTop = printWall.scrollHeight;
//	              alert('已经加入，可以开始聊天。');
//	            });
	            if($("#user_con"+id).length<1){
	            	var tmp='<div id="user_con'+id
	            	+'"><div style="height: 66px;border-bottom: 1px solid #f9f9f9;"><font color="#CCCCCC">请在下面文本框里输入你想要聊天的内容，与用户【'+user+'】聊天</font></div></div>'
	            	   $(".con_box").append(tmp);
	         	   $("#user_con"+id).hide();//默认隐藏，增加效果
	         	}
	         	sibli_hide("#user_con"+id);//隐藏兄弟
	          });

	        });

	        // 房间接受消息
	        room.receive(function(data) {
	          console.info(data);
	          console.info(data.msg.text);
	          $("#user_con"+ing_user).append('<div class="my_say_con"><font color=\"#0000FF\">'+data.fromPeerId+":"+formatTime(data.timestamp)+"</font><p><font color=\"#333333\">"+trim2(trim(data.msg.text))+'</font></p></div>');
			  $("#right_mid").html($("#texterea").val());//右边显示刚发送的文字
			  $("#texterea").val("");
			  $(".my_show").scrollTop($(".con_box").height()-$(".my_show").height());//让滚动滚到最底端
			  var ing_id=ing_user.substring(10,ing_user.length);
			  if($("#usering"+ing_id).length<1){//创建最近聊天人
				  dandan.newuser('.ul_1',$arr_user[ing_id],'ing'+ing_id,ing_id);//创建最近聊天
			  }else{
				  ing_my_user('.ul_1',$arr_user[ing_id],'ing'+ing_id,ing_id);//更新最近聊天的位置   
			  }
	        });
	      }
	    });
	  });

	  // 监听服务情况
	  rt.on('reuse', function() {
		  console.info('服务器正在重连，请耐心等待。。。');
	  });

	  // 监听错误
	  rt.on('error', function() {
		  console.info('连接遇到错误。。。');
	  });
	}

//创建内容框
function my_user_con(user,id,roomid){
	roomId=roomid;
	init(user,id,roomId);
}

//关闭打开的窗口
function delete_user(id,user,img,roomid){
	if(ing_user==id){
		if(confirm('你确定要关闭 '+user+' 聊天窗口吗？\n 注意哦，关闭后你跟 '+user+' 的聊天记录就不见了哦')){
		$("#"+id).remove();//栏目栏目
		$("#user_con"+id).remove();//删除内容
		$(".head"+id).remove();//删除头像
		 if($(".list").length>0){
			 var eq=$(".list").length-1;
			 var old_id=$(".list:eq("+eq+")").attr("id");
			 sibli_hide(".head"+old_id);//显示最后一个的头像
			 sibli_hide("#user_con"+old_id);//显示最后一个的内容
			 $("#zino"+old_id).addClass("td_hide_click");//给最后一个加样式
	         $("#zi"+old_id).addClass("td_user_click");//给最后一个加样式
			 ing_user=old_id;//给聊天框定位
		 }else{
			 $(".dandan_liaotian").show(500);
		 };
		
	    }
	}else{
		title_newuser(id,user,img,roomid);
	}
}

function sendMsg(msg) {
	  var val = msg;

	  // 不让发送空字符
	  if (!String(val).replace(/^\s+/, '').replace(/\s+$/, '')) {
	    alert('请输入点文字！');
	  }
	  // 向这个房间发送消息，这段代码是兼容多终端格式的，包括 iOS、Android、Window Phone
	  room.send({
	    text: val
	  }, {
	    type: 'text'
	  }, function(data) {
		  console.info(data);
	      $("#user_con"+ing_user).append('<div class="my_say_con" style="text-align:right"><font color=\"#0000FF\">'+formatTime(data.t)+":"+$admin_name+"</font><p><font color=\"#333333\">"+trim2(trim(msg))+'</font></p></div>');
		  $("#right_mid").html($("#texterea").val());//右边显示刚发送的文字
		  $("#texterea").val("");
		  $(".my_show").scrollTop($(".con_box").height()-$(".my_show").height());//让滚动滚到最底端
		  var ing_id=ing_user.substring(10,ing_user.length);
		  if($("#usering"+ing_id).length<1){//创建最近聊天人
			  dandan.newuser('.ul_1',$arr_user[ing_id],'ing'+ing_id,ing_id);//创建最近聊天
		  }else{
			  ing_my_user('.ul_1',$arr_user[ing_id],'ing'+ing_id,ing_id);//更新最近聊天的位置   
		  }
	  });
	}

function getLog(callback) {
	  var height = printWall.scrollHeight;
	  if (logFlag) {
	    return;
	  } else {
	    // 标记正在拉取
	    logFlag = true;
	  }
	  room.log({
	    t: msgTime
	  }, function(data) {
	    logFlag = false;
	    // 存储下最早一条的消息时间戳
	    var l = data.length;
	    if (l) {
	      msgTime = data[0].timestamp;
	    }
	    for (var i = l - 1; i >= 0; i--) {
	      showMsg(data[i], true);
	    }
	    if (l) {
	      printWall.scrollTop = printWall.scrollHeight - height;
	    }
	    if (callback) {
	      callback();
	    }
	  });
	}

//发送后调用此方法
 function saysay(){
	 if($(".list").length<1){
		   alert("你还没选中跟哪个聊天，请点左边好友选中一个再聊");
		   return false;
		 }
	 
	  var t=new Date().toLocaleTimeString();//当前时间
	  if($("#texterea").val()){
		  var msg = $("#texterea").val();
		  sendMsg(msg);
      }else{
		alert("你输入的内容为空"); 
	  }
	  $("#texterea").focus();//光标焦点
	}  




//欢迎
$("#top").html('<br />&nbsp;&nbsp;'+dandan.mytime()+','+$admin_name+',欢迎你的到来！！');

//加载用户
$(".ul").html("");//初始清空原来留在那里让w3c通过的
for(i=0;i<$arr_user.length;i++){
    dandan.newuser('.ul_2',$arr_user[i],i);//创建用户
	
}

});

function history(){
	  room.log({
		    t: msgTime
		  }, function(data) {
		    logFlag = false;
		    // 存储下最早一条的消息时间戳
		    var l = data.length;
		    if (l) {
		      msgTime = data[0].timestamp;
		    }
		    for (var i = l - 1; i >= 0; i--) {
		      console.info(data[i]);
		    }

		   
		  });	
}

//====================================================================================================================================================
// 请将 AppId 改为你自己的 AppId，否则无法本地测试
//var appId = 'yCaaVUsIrwqzA4zDb1LtqrvK-gzGzoHsz';

// 请换成你自己的一个房间的 conversation id（这是服务器端生成的）
var roomId = '56ebf62a1532bc00503f86df';

// 每个客户端自定义的 id
//var clientId = 'LeanCloud';

// 用来存储 realtimeObject
var rt;

// 用来存储创建好的 roomObject
var room;

// 监听是否服务器连接成功
var firstFlag = true;

// 用来标记历史消息获取状态
var logFlag = false;

var openBtn = document.getElementById('open-btn');
var sendBtn = document.getElementById('send-btn');
var inputName = document.getElementById('input-name');
var inputSend = document.getElementById('input-send');
var printWall = document.getElementById('print-wall');

// 拉取历史相关
// 最早一条消息的时间戳
var msgTime;

bindEvent(openBtn, 'click', main);
bindEvent(sendBtn, 'click', sendMsg);

bindEvent(document.body, 'keydown', function(e) {
  if (e.keyCode === 13) {
    if (firstFlag) {
      main();
    } else {
      sendMsg();
    }
  }
});

function main() {
  showLog('正在连接服务器，请等待。。。');
  var val = inputName.value;
  if (val) {
    clientId = val;
  }
  if (!firstFlag) {
    rt.close();
  }

  // 创建实时通信实例
  rt = AV.realtime({
    appId: appId,
    clientId: clientId,

    // 请注意，这里关闭 secure 完全是为了 Demo 兼容范围更大些
    // 具体请参考实时通信文档中的「其他兼容问题」部分
    // 如果真正使用在生产环境，建议不要关闭 secure，具体阅读文档
    // secure 设置为 true 是开启
    secure: false
  });

  // 监听连接成功事件
  rt.on('open', function() {
    firstFlag = false;
    showLog('服务器连接成功！');

    // 获得已有房间的实例
    rt.room(roomId, function(object) {

      // 判断服务器端是否存在这个 room，如果存在
      if (object) {
        room = object;

        // 当前用户加入这个房间
        room.join(function() {

          // 获取成员列表
          room.list(function(data) {
            showLog('当前 Conversation 的成员列表：', data);

            // 获取在线的 client（Ping 方法每次只能获取 20 个用户在线信息）
            rt.ping(data.slice(0, 20), function(list) {
              showLog('当前在线的成员列表：', list);
            });

            var l = data.length;

            // 如果超过 500 人，就踢掉一个。
            if (l > 490) {
              room.remove(data[30], function() {
                showLog('人数过多，踢掉： ', data[30]);
              });
            }

            // 获取聊天历史
            getLog(function() {
              printWall.scrollTop = printWall.scrollHeight;
              showLog('已经加入，可以开始聊天。');
            });
          });

        });

        // 房间接受消息
        room.receive(function(data) {
          if (!msgTime) {
            // 存储下最早的一个消息时间戳
            msgTime = data.timestamp;
          }
          showMsg(data);
        });
      } else {
        // 如果服务器端不存在这个 conversation
        showLog('服务器不存在这个 conversation，你需要创建一个。');

        // 创建一个新 room
        rt.room({
          // Room 的默认名字
          name: 'LeanCloud-Room',

          // 默认成员的 clientId
          members: [
            // 当前用户
            clientId
          ],
          // 创建暂态的聊天室（暂态聊天室支持无限人员聊天，但是不支持存储历史）
          // transient: true,
          // 默认的数据，可以放 Conversation 名字等
          attr: {
            test: 'ImGeeks'
          }
        }, function(obj) {

          // 创建成功，后续你可以将 room id 存储起来
          room = obj;
          roomId = room.id;
          showLog('创建一个新 Room 成功，id 是：', roomId);

          // 关闭原连接，重新开启新连接
          rt.close();
          main();
        });
      }
    });
  });

  // 监听服务情况
  rt.on('reuse', function() {
    showLog('服务器正在重连，请耐心等待。。。');
  });

  // 监听错误
  rt.on('error', function() {
    showLog('连接遇到错误。。。');
  });
}

function sendMsg() {

  // 如果没有连接过服务器
  if (firstFlag) {
    alert('请先连接服务器！');
    return;
  }
  var val = inputSend.value;

  // 不让发送空字符
  if (!String(val).replace(/^\s+/, '').replace(/\s+$/, '')) {
    alert('请输入点文字！');
  }

  // 向这个房间发送消息，这段代码是兼容多终端格式的，包括 iOS、Android、Window Phone
  room.send({
    text: val
  }, {
    type: 'text'
  }, function(data) {

    // 发送成功之后的回调
    inputSend.value = '';
    showLog('（' + formatTime(data.t) + '）  自己： ', val);
    printWall.scrollTop = printWall.scrollHeight;
  });

  // 发送多媒体消息，如果想测试图片发送，可以打开注释
  // room.send({
  //     text: '图片测试',
  //     // 自定义的属性
  //     attr: {
  //         a:123
  //     },
  //     url: 'https://leancloud.cn/images/static/press/Logo%20-%20Blue%20Padding.png',
  //     metaData: {
  //         name:'logo',
  //         format:'png',
  //         height: 123,
  //         width: 123,
  //         size: 888
  //     }
  // }, {
  //    type: 'image'
  // }, function(data) {
  //     console.log('图片数据发送成功！');
  // });
}

// 显示接收到的信息
function showMsg(data, isBefore) {
  var text = '';
  var from = data.fromPeerId;
  if (data.msg.type) {
    text = data.msg.text;
  } else {
    text = data.msg;
  }
  if (data.fromPeerId === clientId) {
    from = '自己';
  }
  if (String(text).replace(/^\s+/, '').replace(/\s+$/, '')) {
    showLog('（' + formatTime(data.timestamp) + '）  ' + encodeHTML(from) + '： ', text, isBefore);
  }
}

// 拉取历史
bindEvent(printWall, 'scroll', function(e) {
  if (printWall.scrollTop < 20) {
    getLog();
  }
});

// 获取消息历史
function getLog(callback) {
  var height = printWall.scrollHeight;
  if (logFlag) {
    return;
  } else {
    // 标记正在拉取
    logFlag = true;
  }
  room.log({
    t: msgTime
  }, function(data) {
    logFlag = false;
    // 存储下最早一条的消息时间戳
    var l = data.length;
    if (l) {
      msgTime = data[0].timestamp;
    }
    for (var i = l - 1; i >= 0; i--) {
      showMsg(data[i], true);
    }
    if (l) {
      printWall.scrollTop = printWall.scrollHeight - height;
    }
    if (callback) {
      callback();
    }
  });
}

// demo 中输出代码
function showLog(msg, data, isBefore) {
	alert(data);
 if (data) {
    // console.log(msg, data);
    msg = msg + '<span class="strong">' + encodeHTML(JSON.stringify(data)) + '</span>';
  }
  var p = document.createElement('p');
  p.innerHTML = msg;
  if (isBefore) {
    printWall.insertBefore(p, printWall.childNodes[0]);
  } else {
    printWall.appendChild(p);
  }
}

function encodeHTML(source) {
  return String(source)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;');
  // .replace(/\\/g,'&#92;')
  // .replace(/"/g,'&quot;')
  // .replace(/'/g,'&#39;');
}

function formatTime(time) {
  var date = new Date(time);
  var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
  var currentDate = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
  var hh = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
  var mm = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
  var ss = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
  return date.getFullYear() + '-' + month + '-' + currentDate + ' ' + hh + ':' + mm + ':' + ss;
}

function bindEvent(dom, eventName, fun) {
  if (window.addEventListener) {
    dom.addEventListener(eventName, fun);
  } else {
    dom.attachEvent('on' + eventName, fun);
  }
}
