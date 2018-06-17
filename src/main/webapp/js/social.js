
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
	b($(this),$index)
	localStorage.setItem("socialIndex",$index-1)
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
var $ind=localStorage.getItem("socialIndex")
if($ind){
	b($(".leftNav li").eq($ind),parseInt($ind)+1)
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
/* 去掉
$(".schoolList li").click(function(){
	window.location.href="socialDetail.vm"
})
*/
$(".back1").click(function(){
	window.history.back()
})
var SocialObject={

    appraisal:function (typeNumber,index1,index2) {
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:typeNumber
            },
            success:function (dataA) {
            	console.log(dataA)
            	var da=dataA.data
               $(".twoContent"+index1+" .rightBox").eq(index2).find("img").attr("src",da.image);
               $(".twoContent"+index1+" .rightBox").eq(index2).find(".word").html(da.content);
            }
        })
    },
    //职业鉴定
    appraisalA:function (typeNumber,index) {
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:typeNumber
            },
            success:function (dataA) {
                console.log(dataA)

				var da=dataA.data
				$(".ri"+index).find("img").attr("src",da.image);
				$(".ri"+index).find(".word").html(da.content);


            }
        })
    },
	//学生实习
	student:function (pagenum) {
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:10,
                pagenum:pagenum
            },
            success:function (data) {
                console.log(data)
                var re=data.result;
                for(var i=0;i<re.length;i++){
                    $(".ri3 .schoolList").append(SocialObject.getList(re[i]))
                }
                //分页器
                var p=data.pages
                $("#page").paging({
                    pageNo:data.pagenum,
                    totalPage: p,
                    callback: function(num) {
                        $(".ri3 .schoolList").html("")
                        SocialObject.student(num)
                    }
                })
            }
        })
    },
    getList:function (data) {
        // console.log(data)
        var list=""
        list= '<li><img src="'
			+data.image+'"><p>'
			+data.resourceName+'</p></li>'
        return list
    }

}
//电子设备装接工
SocialObject.appraisal(14,0,0)

SocialObject.appraisal(15,0,1)
SocialObject.appraisal(16,0,2)
SocialObject.appraisal(17,0,3)
//新入职员工培训
SocialObject.appraisal(18,1,0)
SocialObject.appraisal(19,1,1)
SocialObject.appraisal(20,2,0)
SocialObject.appraisal(21,2,1)
SocialObject.appraisal(22,2,2)

SocialObject.appraisalA(26,1)
SocialObject.appraisalA(27,2)
SocialObject.student()
