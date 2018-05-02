//详情
$.ajax({
    type:"post",
    url:"http://localhost:8080/api/notice/get.json",
    data:{
       token:localStorage.getItem("token"),
        id:1
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