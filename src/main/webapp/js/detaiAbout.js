
console.log(window.location.href)
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
}else if(typeA=="about"){//成果展示
    $.ajax({
        type:"post",
        url:urlT+"/api/achievement/get.json",
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
}else if(typeA=="enterprise"){//企业项目
    getC()
}else if(typeA=="task"){//任务库
    getC()
}else if(typeA=="project"){//项目库
    getC()
}else if(typeA=="cooperation"){//校企合作
    getC()
}
function getC(){
    $.ajax({
        type:"post",
        url:urlT+"/api/resource/get.json",
        data:{
            token:localStorage.getItem("token"),
            id:myID
        },
        success:function (dataA) {
            if(dataA.code==200){
                console.log(dataA)
                var result=dataA.data;
                if(dataA.code==200){
                    $(".detailAboutContent h2").html(result.resourceName+'<div class="back1">返回</div>')
                    $(".detailAboutContent p").eq(0).html(new Date(result.createTime).toLocaleString())
                    $(".detailAboutContent p").eq(1).html(result.content)
                    $(".detailAboutContent img").attr("src",result.image)
                }
            }else if(dataA.code==10001){
                window.location.href="login.vm"
            }


        }
    })
}
