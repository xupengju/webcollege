
var indexObj={

    //轮播
    swi:function () {
        var mySwiper = new Swiper('.swiper-container', {
            autoplay: 1000,//可选选项，自动滑动
            slidesPerView: 5,
            loop: true,
            autoplayDisableOnInteraction:false,
        })
    },
    //新闻列表 上
    newsTop:function () {
        $.ajax({
            type:"post",
            url:urlT+"/api/notice/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:1
            },
            success:function (data) {
                console.log(data)
                var result=data.result;
                indexObj.showMessage(0,result)

            }
        })
    },
    //新闻列表 下
    newsBottom:function () {
        $.ajax({
            type:"post",
            url:urlT+"/api/notice/list.json",
            data:{
                token:localStorage.getItem("token"),
                type:2
            },
            success:function (data) {
                console.log(data)
                var result=data.result;
                indexObj.showMessage(1,result)
            }
        })
    },
    showMessage:function(index,result){
        //新闻封面
        $(".news").eq(index).find(".img1").attr("src",result[0].image)
        //第一条
        $(".rightBox").eq(index).find(".figure").attr("myID",result[1].id)
        $(".rightBox").eq(index).find(".figure img").attr("src",result[1].image);
        $(".rightBox").eq(index).find(".figure h3").html(result[1].title);
        $(".rightBox").eq(index).find(".figure p").html(indexObj.eclipseA(result[1].content,90));
        for(var i=2;i<result.length;i++){
            $(".rightBox").eq(index).find("ul").append(" <li myID='"
                +result[i].id+"'><p>"
                +result[i].title+"</p><span>"
                +new Date(result[i].createTime).toLocaleDateString()+"</span></li>")
        }
        $(".rightBox").eq(index).find(".figure").click(function () {
            var myID=$(this).attr("myID");
            localStorage.setItem("myID",myID);
            localStorage.setItem("typeA","notice")
            window.location.href="detaiAbout.vm"
        })
        $(".rightBox").eq(index).find("ul li").click(function () {
            var myID=$(this).attr("myID");
            localStorage.setItem("myID",myID);
            localStorage.setItem("typeA","notice")
            window.location.href="detaiAbout.vm"
        })
    },
    //图文热点
    hotWordImg:function () {
        $.ajax({
            type:"post",
            url:urlT+"/api/notice/list.json",
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
                        +indexObj.eclipseA(result[i].title,50)+'</p></li>')
                }
                indexObj.swi()
                $('.swiper-container img').on('mousedown',function (e) {
                    e.preventDefault()
                })
            }
        })
    },
    //多行显示省略号
    eclipseA:function (obj,len) {
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
}
indexObj.newsTop();
indexObj.newsBottom();
indexObj.hotWordImg();



