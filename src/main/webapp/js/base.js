$("nav a").has(".navTwo").hover(function(){
	$(this).find(".navTwo").slideDown(300).parent().siblings().find(".navTwo").stop(true).slideUp(300)
},function(){
	$(this).find(".navTwo").stop(true).slideUp(300)
})

$(".aboutBtn").click(function(){
	window.location.href="about.vm"
	localStorage.setItem("index",0)
	
})
$(".manageBtn").click(function(){
	window.location.href="practiceManage.vm"
	localStorage.setItem("Manageindex",0)
})
$(".teachBtn").click(function(){
	window.location.href="teachingResources.vm"
	localStorage.setItem("teachIndex",0)
})
$(".innovaBtn").click(function(){
	window.location.href="innovation.vm"
	localStorage.setItem("innovaIndex",0)
})
$(".socialBtn").click(function(){
	window.location.href="shehui.vm"
	localStorage.setItem("socialIndex",0)
})
$(".studentBtn").click(function(){
	window.location.href="student.vm"
	localStorage.setItem("studentIndex",0)
})
var h=window.location.href;
	h=h.substring(h.length-10,h.length-5)
$(".aboutA div").click(function(){
	var $index=$(this).index()
	showaa("about",$index,"about.vm","index")
	
})
//实习管理
$(".manageA div").click(function(){
	var $index=$(this).index()
	showaa("anage",$index,"practiceManage.vm","Manageindex")
})
//教学资源
$(".teachA div").click(function(){
	var $index=$(this).index()
	showaa("urces",$index,"teachingResources.vm","teachIndex")
	
})
//创新创业
$(".innovaA div").click(function(){
	var $index=$(this).index()
	showaa("ation",$index,"innovation.vm","innovaIndex")
})
//社会培训
$(".socialA div").click(function(){
	var $index=$(this).index()
	showaa("hehui",$index,"shehui.vm","socialIndex")
})
//学生管理
$(".studentA div").click(function(){
	var $index=$(this).index()
	showaa("udent",$index,"student.vm","studentIndex")
})
function showaa(word,index,ht,ind){
	if(h==word){
		if($(".leftNav li").eq(index).find("p").length>0){
			$(".leftNav li").eq(index).find("p").stop(true).slideDown().parent().siblings().find("p").stop(true).slideUp()
		}else{
			$(".leftNav li").eq(index).siblings().find("p").slideUp()
		}
		$(".leftNav li").eq(index).addClass("btnBlue").siblings().removeClass("btnBlue")
		$(".ri"+(index+1)).stop(true).show().siblings(".rightBox").stop(true).hide()
		
	}else{//其他页面
		window.location.href=ht
		
	}	
	localStorage.setItem(ind,index)
}

function a(t,index){
	if(t.find("p").length>0){
		t.find("p").stop(true).slideDown().parent().siblings().find("p").stop(true).slideUp()
	}else{
		t.siblings().find("p").slideUp()
	}
	t.addClass("btnBlue").siblings().removeClass("btnBlue")
	$(".ri"+index).stop(true).show().siblings(".rightBox").stop(true).hide()
}
/*function a(t,index){
	if(t.find("p").length>0){
		t.find("p").stop(true).slideDown().parent().siblings().find("p").stop(true).slideUp()
		//console.log($(this).find("a").parent().siblings())
	}else{
		t.siblings().find("p").slideUp()
	}
	t.addClass("btnBlue").siblings().removeClass("btnBlue")
	//console.log(index)
	$(".ri"+index).stop(true).show().siblings(".rightBox").stop(true).hide()
}*/