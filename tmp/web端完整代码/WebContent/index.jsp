<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.*"%>
<%@page import="util.DbUtil"%>

<%@page import="dao.DynamicDAO"%>
<%@page import="model.Dynamic"%>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
 src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
 src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/src-js/time.js"></script>
</head>
<script type="text/javascript">
function updateTime(){	
	document.getElementById('currentTime').innerHTML = getTime(); 
	var list = document.getElementsByName('time');
	var hiddenList = document.getElementsByName('hiddenTime');
	for(var i = 0, l = list.length; i < l; i++) {
		var objTemp = list[i];
		objTemp.innerHTML = setTime(hiddenList[i].innerHTML);
		}
}

var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	websocket = new WebSocket("ws://127.0.0.1:8080/TimeLine/websocket");
} else {
	alert('不支持WebSocket!')
}

//连接发生错误的回调方法
websocket.onerror = function() {
	setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function(event) {
	setMessageInnerHTML("开始");
	var show = document.getElementById('Time');
	var show1 = document.getElementById('currentTime');
	var t = getTime();
	show.innerHTML = t;
	show1.innerHTML = t;
}

//接收到消息的回调方法
websocket.onmessage = function() {
	setMessageInnerHTML(event.data);
}

//连接关闭的回调方法
websocket.onclose = function() {
	setMessageInnerHTML("关闭");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function() {
	websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
	document.getElementById('message').innerHTML = innerHTML + "更新";
	document.getElementById('update').innerHTML = innerHTML;

}

//关闭连接
function closeWebSocket() {
	websocket.close();
}

//发送消息
function send() {
	var msg = document.getElementById('Time').innerHTML;
	websocket.send(msg);
}
         //加载更多
function loadmore() {
	var length = document.getElementsByName('dynamic').length;
	var list = document.getElementsByName('id');
	//获取列表最后一个的ID
	var more_pos = list[length - 1].innerHTML;
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if (xmlhttp.responseText == 0) {
				alert("已加载完所有动态");
			} else {
				//向列表尾部加之前的动态，每次加十条
				$('.container').append(xmlhttp.responseText);
			}
		}
	}
	xmlhttp.open("Get", "LoadMoreServlet?more_pos=" + more_pos,true);
	xmlhttp.send();
}
        //更新动态
function update() {
	//更新当前时间
	document.getElementById('Time').innerHTML = getTime();
	var update_pos = document.getElementById('update').innerHTML;

	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//向列表头部插入新动态
			$('.container').prepend(xmlhttp.responseText);
		}
	}
	xmlhttp.open("GET", "UpdateDynamicsServlet?update_pos=" + update_pos, false);
	xmlhttp.send();
}

//动态更新信息
function wait() {
	setInterval("send()", 3000);
	setInterval("updateTime()", 1000);
}
</script>

<body onload="wait()">

	<div class="row fixed-top ">
		<div class="col-md-4 column text-left">
			<h3>Timeline</h3>
			<div id="Time" style="width: 160px; height: 50px;"></div>
			<div id="currentTime" style="width: 160px; height: 50px;"></div>
		</div>
		<div class="col-md-4 column"></div>
		<div class="col-md-4 column">
			<button type="button" class="btn btn-default float-right "
				id="message" onclick="update()"></button>
			<button id="update" style="visibility: hidden"></button>
		</div>
	</div>
	 
	<div class="container" style="margin-top: 30px">
		<%
			int i = 0;
			String userName = null, descript = null, time = null, image = null;
			int id = 0;
			DynamicDAO dynamicDao = new DynamicDAO();
			List<Dynamic> dynamics = dynamicDao.getDynamicList();
			while (i < dynamics.size()) {
				descript = dynamics.get(i).getContent();
				userName = dynamics.get(i).getUserName();
				time = dynamics.get(i).getTime();
				image = dynamics.get(i).getImage();
				id = dynamics.get(i).getId();
		%>

		<div class="row" name="dynamic">
			<div class="col-md-12">
				<p name="id" style="visibility: hidden"><%=id%></p>
				<ul class="timeline">
					<li><span style="color: blue"><%=userName%></span>
						<p style="color: blue" class="float-right" name="time"><%=time%></p>
						<p name="hiddenTime" style="visibility: hidden"><%=time%></p>

						<p><%=descript%></p> <%
 	if (dynamics.get(i).getImage()!= null && !dynamics.get(i).getImage().equals("")) {
 %> <img
						src="<%=dynamics.get(i).getImage()%>" class="img-thumbnail">
						<%
							}
						%></li>
				</ul>
			</div>
		</div>
		<%
			i++;
			}
		%>
	</div>
	                  
	<button type="button" class="btn btn-default btn-block" id="moreButton"
		onclick="loadmore()">更多</button>
</body>
</html>