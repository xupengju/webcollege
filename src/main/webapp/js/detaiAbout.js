
var myID=localStorage.getItem("myID")
//类型
var typeA=localStorage.getItem("typeA")
//新闻详情
if(typeA=="notice"){
    $.ajax({
        type:"post",
        url:urlT+"/api/notice/get.json",
        data:{
            token:localStorage.getItem("token"),
            id:myID
        },
        success:function (dataA) {
            console.log(dataA)
            var result=dataA.data;
            if(dataA.code==200){
                $(".detailAboutContent h2").html(result.title+'<div class="back1">返回</div>')
                $(".detailAboutContent p").eq(0).html(new Date(result.createTime).toLocaleString())
                $(".detailAboutContent p").eq(1).html(result.content)
                $(".detailAboutContent img").attr("src",result.image)
            }
        }
    })
}