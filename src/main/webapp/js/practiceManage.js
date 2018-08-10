
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
            url:urlT+"/api/resource/lists.json",
			data:{
                token:localStorage.getItem("token"),
                type:13
			},
			success:function (da) {
				//if(da.code==200){
                console.log(da)
                var re=da.result;
                for(var i=0;i<re.length;i++){
                    $(".ri1 .ruleWord").append("<p myID='"+re[i].id+"'>"+re[i].resourceName+"</p>")
                }
                $(".ri1 .ruleWord p").click(function(){
                    var myID=$(this).attr("myID");
                    localStorage.setItem("myID",myID);
                    localStorage.setItem("typeA","rule")
                    window.location.href="detaiAbout.vm"
                })

            }
		})
    },
	//实习流程/岗位目标与要求
    practiceProcess:function(type,index){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:type
            },
            success:function (da) {
            	//if(da.code==200){
                    console.log(da)
                    var data1=da.data
                    if(index==2 || index==3){
                        $(".ri"+index+" img").attr("src",data1.image);
                    }else if(index==4 || index==6){
                        $(".ri"+index+" .word").html(data1.content);

                    }else if(index==5){
                        $(".ri"+index+" img").attr("src",data1.image);
                        $(".ri"+index+" .word").html(data1.content);
                    }
				/*}else if(da.code==10001){
                    window.location.href="login.vm"
                }*/


            }
        })
	},


}
//规章制度
PracticeManageObject.ruleList(4)
//实习流程
PracticeManageObject.practiceProcess(5,2)
PracticeManageObject.practiceProcess(6,3)


PracticeManageObject.practiceProcess(7,4)
PracticeManageObject.practiceProcess(8,5)
PracticeManageObject.practiceProcess(9,6)