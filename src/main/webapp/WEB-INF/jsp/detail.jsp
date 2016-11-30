<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<title>详情页</title>
</head>
<body>
	<div class="container">
		<div class = "panel panel-default">
			<div class="panel panel-heading text-center">${stock.name}</div>
			<div class="panel panel-body text-center">
				<h2 class="text-danger">
					<span class="glyphicon glyphicon-time"></span>
					<span class="glyphicon" id="stock_box"></span>
				</h2>
			</div>
		</div>
	</div>
	<!-- 登录窗口弹出层 -->
	<div id="login_modal" class="modal fade">
		<div class="modal-dialog">
			<div class ="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone">秒杀电话</span>
					</h3>
				</div>
				<div class="modal-body">
					<div class = "row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" id="user_phone" 
							placeholder="填手机号" class="form-control">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<span class="glyphicon" id="phone_message"></span>
					<button id="submit_phone" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						提交
					</button>
				</div>
			</div>
		</div>
	</div>
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script> 
<script type="text/javascript" src="/panicbuy/resources/js/panicbuy.js"></script>
<script type="text/javascript">
$(function(){
	panicbuy.detail.init({
		stockId:${stock.stockId},
		startTime:${stock.startTime.time},
		endTime:${stock.endTime.time}
	})
});
</script>
</body>
</html>