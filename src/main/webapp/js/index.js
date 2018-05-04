function swi() {
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 1000,//可选选项，自动滑动
        slidesPerView: 5,
        loop: true
    })
}

//新闻列表 上
$.ajax({
    type:"post",
    url:"http://localhost:8080/api/notice/list.json",
    data:{
        token:localStorage.getItem("token"),
        type:1
    },
    success:function (data) {
        console.log(data)
        var result=data.result;
        $(".rightBox").eq(0).find(".figure").attr("myID",result[0].id)
        $(".rightBox").eq(0).find(".figure img").attr("src",result[0].image);
        $(".rightBox").eq(0).find(".figure h3").html(result[0].title);
        $(".rightBox").eq(0).find(".figure p").html(eclipseA(result[0].content,90));
        for(var i=1;i<result.length;i++){
            $(".rightBox").eq(0).find("ul").append(" <li myID='"
                +result[i].id+"'><p>"
                +result[i].title+"</p><span>"
                +new Date(result[i].createTime).toLocaleDateString()+"</span></li>")
        }
        $(".rightBox").eq(0).find(".figure").click(function () {
            var myID=$(this).attr("myID");
            localStorage.setItem("myID",myID);
            localStorage.setItem("typeA","notice")
            window.location.href="detaiAbout.vm"
        })
        $(".rightBox").eq(0).find("ul li").click(function () {
            var myID=$(this).attr("myID");
            localStorage.setItem("myID",myID);
            localStorage.setItem("typeA","notice")
            window.location.href="detaiAbout.vm"
        })

    }
})
//新闻列表 下
$.ajax({
    type:"post",
    url:"http://localhost:8080/api/notice/list.json",
    data:{
        token:localStorage.getItem("token"),
        type:2
    },
    success:function (data) {
        console.log(data)
        var result=data.result;
        console.log(result[0])
        $(".rightBox").eq(1).find(".figure").attr("myID",result[0].id)
        $(".rightBox").eq(1).find(".figure img").attr("src",result[0].image);
        $(".rightBox").eq(1).find(".figure h3").html(result[0].title);
        $(".rightBox").eq(1).find(".figure p").html(eclipseA(result[0].content,90));

        for(var i=1;i<result.length;i++){
            $(".rightBox").eq(1).find("ul").append(" <li><p>"
                +result[i].title+"</p><span>"
                +new Date(result[i].createTime).toLocaleDateString()+"</span></li>")
        }
        $(".rightBox").eq(1).find(".figure").click(function () {
            var myID=$(this).attr("myID");
            localStorage.setItem("myID",myID);
            localStorage.setItem("typeA","notice")
            window.location.href="detaiAbout.vm"
        })
        $(".rightBox").eq(1).find("ul li").click(function () {
            var myID=$(this).attr("myID");
            localStorage.setItem("myID",myID);
            localStorage.setItem("typeA","notice")
            window.location.href="detaiAbout.vm"
        })
    }
})
//图文热点
$.ajax({
    type:"post",
    url:"http://localhost:8080/api/notice/list.json",
    data:{
        token:localStorage.getItem("token"),
        type:3
    },
    success:function (data) {
        console.log(data)
        var result=data.result;
        for(var i=1;i<result.length;i++){
            $(".footer1 ul").append('<li class="swiper-slide"> <img src="'
                +result[i].image+'"/><p>'
                +eclipseA(result[i].title,50)+'</p></li>')
        }
        swi()
    }
})
//多行显示省略号
function eclipseA(obj,len){
    var list="";
    for (var i=0;i<obj.length;i++){
        if(list.length>=len){
            list+="..."
            break;
        }else {
            list+=obj[i]
        }
    }
    return list;
}