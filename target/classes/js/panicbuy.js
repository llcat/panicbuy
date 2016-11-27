/**
 * 存放主要交互逻辑代码
 * 使用模块化的方式来编写，通过Json格式的形式来写
 * 不同的交互逻辑
 */
var panicbuy={
	URL:{
		//存放url相关的操作
	},
	validatePhone:function(phone){
		if(phone && phone.length==11 && !isNaN(phone)){
			return true;
		}else{
			return false;
		}
	},
	detail:{
		//存放详情页面逻辑
		init:function(params){
			var userPhone = $.cookie("userphone");
			var stockId = params['stockId'];
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			if(!panicbuy.validatePhone(userPhone)){
				//如果cookie中没有取得手机号为空，那么就显示弹出层
				var login_modal = $("#login_modal");
				login_modal.modal({
					show:true,
					backdrop:static,
					keyboard:false
				});
				//监控提交按钮，验证填写内容，成功写入cookie,刷新页面
				//否则提示用户输入错误
				$("#submit_phone").click(function(){
					var inputContent = $("#user_phone");
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
		}
	}
}