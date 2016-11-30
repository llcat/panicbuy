/**
 * 存放主要交互逻辑代码
 * 使用模块化的方式来编写，通过Json格式的形式来写
 * 不同的交互逻辑
 */
var panicbuy={
	URL:{
		//存放url相关的操作
		nowTime:function(){
			return 'http://localhost:8080/panicbuy/time/now';
		},
		exposer:function(stockId){
			return 'http://localhost:8080/panicbuy/'+stockId+'/exposer';
		},
		execute:function(stockId,md5){
			return 'http://localhost:8080/panicbuy/'+stockId+"/"+md5+"/execute";
		}
	},
	validatePhone:function(phone){
		if(phone && phone.length==11 && !isNaN(phone)){
			return true;
		}else{
			return false;
		}
	},
	//封装执行秒杀相关的交互
	executeKill:function(stockId,node){
		node.hide().html('<button class="btn btn-primary btn-log" id="executekill">开始抢购</button>');
		$.post(panicbuy.URL.exposer(stockId),{},function(result){
			if(result && result['success']){
				var exposer = result['data'];
				if(exposer['exposed']){
					//暴露秒杀接口
					var md5=exposer['md5'];
					var killurl = panicbuy.URL.execute(stockId, md5);
					$("#executekill").one("click",function(){
						$("#executekill").addClass("disabled");
						$.post(killurl,{},function(result){
						if(result['success']){
							var executeInfo = result['data'];
							var stateInfo = executeInfo['stateInfo'];
							node.html('<span class="label label-success">'+stateInfo+'</span>');
						}
					});
					});
				}else{
					//不暴露秒杀接口，从新回到计时交互
					nowTime = exposer['now'];
					startTime = exposer['start'];
					endTime = exposer['end'];
					panicbuy.countdown(stockId, nowTime, startTime, endTime)
				}
			}
		});
		node.show();
	},
	//封装计时相关的操作
	countdown:function(stockId,nowTime,startTime,endTime){
		var stockBox = $("#stock_box");
		if(nowTime<startTime){
			var leftTime = new Date(startTime+1000);
			stockBox.countdown(leftTime,function(event){
				var fmtTime = event.strftime("秒杀倒计时：%D天 %H时 %M分 %S秒");
				stockBox.hide().html(fmtTime).show();
				/*on('finish.countdown',function(){
					panicbuy.executeKill(stockId,stockBox);
				})*/
			});
		}else if(nowTime>endTime){
			stockBox.hide().html('秒杀结束');
		}else{
			panicbuy.executeKill(stockId,stockBox);
		}
		stockBox.show();
	},
	detail:{
		//存放详情页面逻辑
		init:function(params){
			var userPhone = $.cookie("userphone");
			if(!panicbuy.validatePhone(userPhone)){
				//如果cookie中没有取得手机号为空，那么就显示弹出层
				var login_modal = $("#login_modal");
				login_modal.modal({
					show:true,
					backdrop:"static",
					keyboard:false
				});
				//监控提交按钮，验证填写内容，成功写入cookie,刷新页面
				//否则提示用户输入错误
				$("#submit_phone").click(function(){
					var inputContent = $("#user_phone").val();
					if(panicbuy.validatePhone(inputContent)){
						//将内容写入cookie中
						$.cookie("userphone",inputContent,{expires:7,path:"/panicbuy"})
						window.location.reload();
					}else{
						$("#phone_message")
						.hide()
						.html('<label class="label label-danger">手机号错误</label>')
						.show(300);
					}
				});
			}
			//模拟登录做完了，接下来是计时交互
			var stockId = params['stockId'];
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			$.get(panicbuy.URL.nowTime(),{},function(result){
				if(result && result['success']){
					nowTime = result['data'];
					panicbuy.countdown(stockId, nowTime, startTime, endTime);
				}else{
					console.log("result:"+result);
				}
			})
			
		}//---->init
	}//---->detail
}