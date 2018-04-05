var btn = document.getElementsByClassName("btn")[0]
var sUserAgent = navigator.userAgent.toLowerCase();
var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
var bIsMidp = sUserAgent.match(/midp/i) == "midp";
var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
var bIsAndroid = sUserAgent.match(/android/i) == "android";
var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
if(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
	alert("移动");
	getLocation();
} else {
	alert("pc");
	pcAction()
}

function pcAction() {
	var map, geolocation;
	//加载地图，调用浏览器定位服务
	map = new AMap.Map('container', {
		resizeEnable: true
	});
	map.plugin('AMap.Geolocation', function() {
		geolocation = new AMap.Geolocation({
			enableHighAccuracy: true, //是否使用高精度定位，默认:true
			timeout: 10000, //超过10秒后停止定位，默认：无穷大
			buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
			zoomToAccuracy: true, //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
			buttonPosition: 'RB'
		});
		map.addControl(geolocation);
		geolocation.getCurrentPosition();
		AMap.event.addListener(geolocation, 'complete', onComplete); //返回定位信息
		AMap.event.addListener(geolocation, 'error', onError); //返回定位出错信息
	});

}
//解析定位结果
function onComplete(data) {
	$(".btn").one("click", function() {
		//alert(data.position.getLng() + "," + data.position.getLat())
		if(data.position.getLng() > 114.52654 && data.position.getLng() < 114.527313 && data.position.getLat() > 37.99641 && data.position.getLat() < 37.997048) {
			//alert("签到成功")
			$(".btn").html("已签到")
			$(".btn").css({
				"background": "#ccc"
			})

		} else {
			alert("签到失败")
		}
	})

}
//解析定位错误信息
function onError(data) {
	alert('定位失败');
}

function getLocation() {
	if(navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
	} else {
		alert("浏览器不支持地理定位。");
	}
}

function showError(error) {
	alert("error")
	switch(error.code) {
		case error.PERMISSION_DENIED:
			alert("定位失败,用户拒绝请求地理定位");
			break;
		case error.POSITION_UNAVAILABLE:
			alert("定位失败,位置信息是不可用");
			break;
		case error.TIMEOUT:
			alert("定位失败,请求获取用户位置超时");
			break;
		case error.UNKNOWN_ERROR:
			alert("定位失败,定位系统失效");
			break;
	}
};

function showPosition(position) {
	alert("进入")
	var latlon = position.coords.latitude + ',' + position.coords.longitude;
	console.log(latlon);
	console.log("原始位置精度：" + position.coords.accuracy);
	//baidu   
	var url = "http://api.map.baidu.com/geocoder/v2/?ak=hCBcm8H8opRLdC0f6OibbGavC0pne1uc&callback=renderReverse&location=" + latlon + "&output=json&pois=0";
	$.ajax({
		type: "GET",
		dataType: "jsonp",
		url: url,
		beforeSend: function() {
			alert('正在定位...');
		},
		success: function(json) {
			/*	$("#baidu_geo").html(json.result.formatted_address);*/
			var ggPoint = new BMap.Point(json.result.location.lng, json.result.location.lat); //创建标点
			//	$("#word1").html("坐标" + json.result.location.lng + "," + json.result.location.lat)
			//地图初始化
			var bm = new BMap.Map("allmap");
			bm.centerAndZoom(ggPoint, 15);
			bm.addControl(new BMap.NavigationControl()); //控件
			//坐标转换完之后的回调函数
			translateCallback = function(data) {
				alert(data.status)
				if(data.status === 0) {
					var marker = new BMap.Marker(data.points[0]);
					bm.addOverlay(marker);
					var label = new BMap.Label("", {
						offset: new BMap.Size(20, -10)
					});
					marker.setLabel(label); //添加百度label
					//$("#word2").html("坐标"+data.points[0].lng+","+data.points[0].lat)

					$(".btn").one("click", function() {
						if((data.points[0].lng > 114.533076 && data.points[0].lng < 114.533822) && (data.points[0].lat > 38.002196 && data.points[0].lat < 38.002868)) {

							alert("签到成功")

						} else {
							alert("请在实习基地签到")
						}
					})

					bm.setCenter(data.points[0]);
				}
			}
			var convertor = new BMap.Convertor(); //这个类就是转换的对象
			var pointArr = [];
			pointArr.push(ggPoint);
			convertor.translate(pointArr, 1, 5, translateCallback) //通过调用回调函数来进行转换。 
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("地理位置获取失败")
		}
	});
};


