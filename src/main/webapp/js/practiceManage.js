
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
//左侧点击切换
$(".leftNav li").click(function(){
	var $index=$(this).index()+1
	a($(this),$index)
	localStorage.setItem("Manageindex",$index-1)
})
function a(t,index){
	if(t.find("p").length>0){
		t.find("p").stop(true).slideDown().parent().siblings().find("p").stop(true).slideUp()
		//console.log($(this).find("a").parent().siblings())
	}else{
		t.siblings().find("p").slideUp()
	}
	t.addClass("btnBlue").siblings().removeClass("btnBlue")
	$(".ri"+index).stop(true).show().siblings(".rightBox").stop(true).hide()
}
var $ind=localStorage.getItem("Manageindex")
if($ind){
	a($(".leftNav li").eq($ind),parseInt($ind)+1)
}
var PracticeManageObject={
	//规章制度
    ruleList:function (typeNumber) {
		$.ajax({
			type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
			data:{
                token:localStorage.getItem("token"),
                contentType:4
			},
			success:function (data) {
				console.log(data)
            }
		})
    },
	//实习流程/岗位目标与要求/企业产品标准
    practiceProcess:function(type){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:type
            },
            success:function (data) {
            	console.log(data)
               $(".ri2 img").attr("src","")
            }
        })
	},


}
//规章制度
PracticeManageObject.ruleList(4)
//实习流程
PracticeManageObject.practiceProcess(5)
PracticeManageObject.practiceProcess(6)
PracticeManageObject.practiceProcess(7)
PracticeManageObject.practiceProcess(8)
PracticeManageObject.practiceProcess(9)