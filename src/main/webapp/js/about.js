


//左侧点击切换
$(".leftNav li").click(function(){
	
	var $index=$(this).index()+1
	b($(this),$index)
	localStorage.setItem("index",$index-1)
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
var $ind=localStorage.getItem("index")
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
var aboutObj={
	//学校介绍和基地介绍
	schoolSummary:function(index,contentType){

		$.ajax({
			type:"post",
			url:urlT+"/api/baseInfo/searchOne.json",
			data:{
				token:localStorage.getItem("token"),
            	contentType:contentType
			},
			success:function(data1) {
				//console.log(data1)
				var result=data1.data
				$(".ri"+index+" img").eq(0).attr("src",result.image);
				$(".ri"+index+" .word").html(result.content)
			}
		})
	},
	//成果展示
    achievementShow:function(pagenum){
        $.ajax({
            type:"post",
            url:urlT+"/api/achievement/list.json",
            data:{
                token:localStorage.getItem("token"),
				pagenum:pagenum
            },
            success:function(data) {
                //console.log(data)
				var resultA=data.result;
                for(var i=0;i<resultA.length;i++){
					$(".ri3 .achev").append(aboutObj.getAchievementList(resultA[i]))
				}
                //分页器
                var p=data.pages
                $("#page").paging({
                    pageNo:data.pagenum,
                    totalPage: p,
                    callback: function(num) {
                        $(".ri3 .achev").html("")
                        aboutObj.achievementShow(num)
                    }
                })
            }
        })
	},
	//获取成果展示列表
	getAchievementList:function(data){
		var list=""
		list='<p class="gain"><a href="views/detaiAbout.vm">'
			+data.title+'</a><span>'
			+new Date(data.createTime).toLocaleDateString()+'</span></p>'
		return list
	},
	//教师列表
    teacherList:function (typeNumber,index,pagenum) {
        $.ajax({
            type:"post",
            url:urlT+"/api/teacher/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:typeNumber,
				pagenum:pagenum
            },
            success:function(data){
                for(var i=0;i<data.result.length;i++){
                    $(".twoContent3 .rightBox").eq(index).find(".teacher").append(aboutObj.getTeacher1(data.result[i]))
                }
                //分页器
                var p=data.pages
                $("#page"+(index+2)).paging({
                    pageNo:data.pagenum,
                    totalPage: p,
                    callback: function(num) {
                        $(".twoContent3 .rightBox").eq(index).find(".teacher").html("")
                        aboutObj.teacherList(typeNumber,index,num)
                    }
                })
            }
        })
    },
    //企业教师列表函数
    getTeacher1:function (data) {
        var list="";
        return list+='<li><img src="' +
            ''+data.image+'"><div class="word1"><h3>'
            +data.teacherName+' '+data.academicTitle+'</h3><p>'
            +data.resume+'</p></div></li>'
    }
}
aboutObj.schoolSummary(1,1)
aboutObj.schoolSummary(2,2)
aboutObj.schoolSummary(4,3)
aboutObj.achievementShow()
//企业教师
aboutObj.teacherList(1,0)
//学校教师
aboutObj.teacherList(2,1)
$(".ruleWord p").click(function () {
	window.location.href="detaiAbout.vm"
})


