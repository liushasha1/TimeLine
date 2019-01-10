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