

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

	//视频库
	videoLibrary:function (typeA,pagenum) {
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:typeA,
                pagenum:pagenum
            },
            success:function (data) {
               //console.log(data)
                if(data.code==10001){
                   alert(data.message)
                    return;
                }
                var re=data.result;
                for(var i=0;i<re.length;i++){

                    //console.log(re[i])
                    $(".detailAboutContent ul").append(TeachingResourcesObject.getVideoList(re[i]))
                }
               var p=data.pages
                $("#page").paging({
                    pageNo:data.pagenum,
                    totalPage: p,
                    callback: function(num) {
                        $(".detailAboutContent ul").html("")
                        TeachingResourcesObject.videoLibrary(typeA,num)
                    }
                })
            }
        })
    },
    getVideoList:function (data) {
        var list=""
        list='<li><img src="'+data.image+'"/><a href="'+data.link+'">'+data.resourceName+'</a> </li>'
        return list
    }
}
