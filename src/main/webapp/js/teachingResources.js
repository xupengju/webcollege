
//分页器
$("#page").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
//师资分页器
$("#page2").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
$("#page3").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
$("#page4").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
//左侧点击切换
$(".leftNav li").click(function(){
	var $index=$(this).index()+1
	b($(this),$index)
	localStorage.setItem("teachIndex",$index-1)
})
function b(t,index){
	if(t.find("p").length>0){
		t.find("p").stop(true).slideDown().parent().siblings().find("p").stop(true).slideUp()
		//console.log($(this).find("a").parent().siblings())
	}else{
		t.siblings().find("p").slideUp()
	}
	t.find("p").css("background","#adbad1")
	t.addClass("btnBlue").siblings().removeClass("btnBlue")
	$(".ri"+index).stop(true).show().siblings().not("ul").stop(true).hide()
}
var $ind=localStorage.getItem("teachIndex")
if($ind){
	
	a($(".leftNav li").eq($ind),parseInt($ind)+1)
}
$(".leftNav li p").click(function(event){
	event.stopPropagation()
	$(".content .rightBox").hide()
	var $in=$(this).index()+1;
	$(this).css("background","#7a90b6").siblings().css("background","#adbad1")
	var parentIndex=$(this).parent().index()
	$(".twoContent"+parentIndex).show().siblings(".co").hide()
	$(".twoContent"+parentIndex).find(".rightBox").eq($in-1).show()
})
$(".ri2 .newBox").eq(0).click(function(){
	window.location.href="TeachingResourcesVideo.vm"
})
$(".ri2 .newBox").eq(1).click(function(){
	window.location.href="TeachingResourcesImg.vm"
})
$(".ri2 .newBox").eq(2).click(function(){
	window.location.href="TeachingResourcesTeach.vm"
})
$(".ri2 .newBox").eq(3).click(function(){
	window.location.href="TeachingResourcesLink.vm"
})
//返回
$(".back1").click(function(){
	window.history.back()
})
//
$(".ri1 .newBox").click(function () {
	window.location.href="detaiAbout.vm"
})