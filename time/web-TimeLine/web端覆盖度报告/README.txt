1.qunit-2.8.0.js为测试使用，不是测试的js文件。
2.其中time.js的一些方法的分支情况不能全部覆盖，如getTime()方法只获取当前时间,所以分支覆盖只能达到百分之五十。
3.QUnit无法对JSP文件进行测试，为了测试内含有JSP页面元素的方法如：
//更新当前时间
function updateTime(){	
			document.getElementById('currentTime').innerHTML = getTime(); 
			var list = document.getElementsByName('time');
			var hiddenList = document.getElementsByName('hiddenTime');
			for(var i = 0, l = list.length; i < l; i++) {
				var objTemp = list[i];
				objTemp.innerHTML = setTime(hiddenList[i].innerHTML);
				}
	   }
编写一个html文件为进行单独测试。利用mock思想，在HTML中测试的方法无法使用JSCover进行覆盖度检测，所以我们只利用QUnit进行测试这部分代码的正确性。


