	
QUnit.test("setTimeTest", function(assert) {
	 var time1 = "2019-01-04 13:00:00";
	 var time2 = "2019-01-05 13:00:00";
	 var time3 = "2019-01-06 12:00:00";
	 var time4 = "2019-01-06 23:50:00";
	 var resultTime1 = setTime(time1);
	 var resultTime2 = setTime(time2);
	 var resultTime3 = setTime(time3);
	 var resultTime4 = setTime(time4);
   assert.equal(resultTime1,"2019-01-04 13:00:00","test setTime(time1)");
   assert.equal(resultTime2,"昨天 13:00","test setTime(time2)"); 	   
   assert.equal(resultTime3,"12小时前","test setTime(time3)"); 	
   assert.equal(resultTime4,"13分钟前","test setTime(time4)"); 
});
QUnit.test("getTimeTest", function(assert) {
	 var time = new Date(); 
	 var mon = time.getMonth() + 1;
	 if(mon<10)
		mon = '0'+mon;
	 var d = time.getDate();
	 if(d<10)
		d = '0'+d;
	 var h = time.getHours();
	 if(h<10)
		h = '0'+h;
	 var min = time.getMinutes();
	 if(min<10)
		min = '0'+min;
	 var s = time.getSeconds();
	 if(s<10)
		s = '0'+s;
	 var t = time.getFullYear() + "-" + mon + "-" + d + " "+ h + ":" + min + ":"+ s;
	 var resultTime = getTime();
    assert.equal(t,resultTime,"test getTime()"); 	   
});