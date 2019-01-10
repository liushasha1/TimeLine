//获取当前时间
function getTime(){  
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
	 return t;
 }
	
	//计算与当前时间的相对时间
	function setTime(tTime){  
		
		var arr1 = tTime.split(" "); 
	    var sdate = arr1[0].split('-');
		var tdate = arr1[1].split(':') 
		var date = new Date(sdate[0], sdate[1]-1, sdate[2],tdate[0],tdate[1]); 
		
		var currentDate = new Date();
		var dv_day = Math.abs(currentDate.getTime()-date.getTime())/(1000*24*60*60);
	
		if(dv_day>=2)
			{
			  return tTime;
			}
		else if(dv_day>=1){
			var tmp_h = date.getHours();
			if(tmp_h<10)
				tmp_h = "0"+tmp_h;
			var tmp_m = date.getMinutes();
			if(tmp_m<10)
				tmp_m = "0"+tmp_m;
			var tmp_time="昨天 "+tmp_h+":"+tmp_m;
			return tmp_time;
		}
		else{
			var dv_hours = Math.floor(Math.abs(currentDate.getTime()-date.getTime())/(1000*60*60));
			if(dv_hours >=1)
				{
				var tmp_time = dv_hours+"小时前";
				return tmp_time;
				}
			else
				{
				var dv_mins = Math.floor(Math.abs(currentDate.getTime()-date.getTime())/(1000*60));
				var tmp_time = dv_mins+"分钟前";
				return tmp_time;
				}
		}
	}
	