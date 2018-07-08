

//师资分页器

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
var TeachingResourcesObject={
//企业项目
    eProject:function (pagenum) {
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:8,
                pagenum:pagenum
            },
            success:function (data) {
                console.log(data)
                if(data.code==200){
                    //ceonsol.log(data)
                    var resultA=data.result;
                    for(var i=0;i<resultA.length;i++){
                        //下面的参数待定
                        //console.log(resultA[i].image)
                        $(".ri1 .rBox").append('<div myID="'+resultA[i].id+'" class="newBox"> <img src="'+resultA[i].image+'"><p>'+resultA[i].resourceName+'</p></div>')
                    }
                    $(".ri1 .rBox .newBox").click(function(){
                        var myID=$(this).attr("myID");
                        localStorage.setItem("myID",myID);
                        localStorage.setItem("typeA","enterprise")
                        window.location.href="detaiAbout.vm"
                    })
                    //分页器
                    var p=data.pages
                    $("#page1").paging({
                        pageNo:data.pagenum,
                        totalPage: p,
                        callback: function(num) {
                            $(".ri1 .rBox").html("")
                            TeachingResourcesObject.eProject(num)
                        }
                    })
                }else if(data.code==10001){
                    window.location.href="login.vm"
                }

            }
        })
    },
	//视频库
	videoLibrary:function (typeA,index,pagenum) {
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:typeA,
                pagenum:pagenum
            },
            success:function (data) {
                if(data.code==200){
                    // console.log(data)
                    var re=data.result;
                    for(var i=0;i<re.length;i++){
                        console.log(index)
                        console.log(re[i])
                        $(".twoContent1 .rightBox").eq(index).find(".t").append(TeachingResourcesObject.getVideoList(re[i]))
                    }
                    $(".twoContent1 .rightBox").eq(0).find(".t").find(".gain").click(function(){
                        var myID=$(this).attr("myID");
                        localStorage.setItem("myID",myID);
                        localStorage.setItem("typeA","task")
                        window.location.href="detaiAbout.vm"
                    })
                    $(".twoContent1 .rightBox").eq(1).find(".t").find(".gain").click(function(){
                        var myID=$(this).attr("myID");
                        localStorage.setItem("myID",myID);
                        localStorage.setItem("typeA","project")
                        window.location.href="detaiAbout.vm"
                    })
                    $(".twoContent1 .rightBox").eq(2).find(".t").find(".gain").click(function(){
                        alert()
                        var myID=$(this).attr("myID");
                        localStorage.setItem("myID",myID);
                        localStorage.setItem("typeA","teachingVideo")
                        window.location.href="detaiAbout.vm"
                    })
                    var p=data.pages
                    $("#page"+(index+2)).paging({
                        pageNo:data.pagenum,
                        totalPage: p,
                        callback: function(num) {
                            $(".twoContent1 .rightBox").eq(index).find(".t").html("")
                            TeachingResourcesObject.videoLibrary(typeA,index,num)
                        }
                    })
                }else if(data.code==10001){
                    window.location.href="login.vm"
                }

            }
        })
    },
    //教学视频
    videoLibraryV:function(typeA,index,pagenum){
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:typeA,
                pagenum:pagenum
            },
            success:function (data) {
                if(data.code==200){
                    // console.log(data)
                    var re=data.result;
                    for(var i=0;i<re.length;i++){
                        console.log(index)
                        console.log(re[i])
                        $(".twoContent1 .rightBox").eq(index).find(".t").append(TeachingResourcesObject.getVideoListV(re[i]))
                    }

                   /* $(".twoContent1 .rightBox").eq(2).find(".t").find(".gain").click(function(){
                        alert()
                        var myID=$(this).attr("myID");
                        localStorage.setItem("myID",myID);
                        localStorage.setItem("typeA","teachingVideo")
                        window.location.href="detaiAbout.vm"
                    })*/
                    var p=data.pages
                    $("#page"+(index+2)).paging({
                        pageNo:data.pagenum,
                        totalPage: p,
                        callback: function(num) {
                            $(".twoContent1 .rightBox").eq(index).find(".t").html("")
                            TeachingResourcesObject.videoLibraryV(typeA,index,num)
                        }
                    })
                }else if(data.code==10001){
                    window.location.href="login.vm"
                }

            }
        })
    },
    getVideoListV:function (data) {
        // console.log(data)
        var list=""
        list='<p myID="'+data.id+'" class="gain"><a href="'+data.link+'">'
            +data.resourceName+'</a><span>'
            +new Date(data.createTime).toLocaleDateString()+'</span> </p>'
        return list
    },
    getVideoList:function (data) {
       // console.log(data)
        var list=""
        list='<p myID="'+data.id+'" class="gain"><a href="#">'
			+data.resourceName+'</a><span>'
			+new Date(data.createTime).toLocaleDateString()+'</span> </p>'
        return list
    }

}
TeachingResourcesObject.eProject()
TeachingResourcesObject.videoLibrary(5,0,1)
TeachingResourcesObject.videoLibrary(6,1,1)
TeachingResourcesObject.videoLibraryV(7,2,1)