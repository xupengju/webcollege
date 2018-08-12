var personObj={
    //签到列表
    showList:function () {
        $.ajax({
            type:"post",
            url:"/api/signin/list.json",
            data:{
                token:localStorage.getItem("token")
            },
            success:function(data){
                //console.log(data)
                if(data.code==10001){
                    alert(data.message)
                    window.location.href="../login.vm"
                }else{
                    $(".personBox").show()
                    var list=data.result;
                    for(var i=0;i<list.length;i++){
                        var isSign="";
                        if(list[i].status){
                            isSign="已签到"
                        }else{
                            isSign="未签到"
                        }
                        $(".signTable").append(`<tr><td>${list[i].id}</td><td>${getTime(list[i].createTime)}</td><td>${isSign}</td></tr>`)
                    }
                    //个人信息展示
                    personObj.messageShow(data)
                }
            }
        })
    },
    //个人信息展示
    messageShow:function(data){
        console.log(data)
        $(".personBox p").eq(0).find("b").html(data.user.realName);
       // $(".personBox p").eq(1).find("b").html(data.user.idCard);//身份证
        if(data.user.sex==0){
            $(".personBox p").eq(1).find("b").html("女");//性别
        }else if(data.user.sex==1){
            $(".personBox p").eq(1).find("b").html("男");//性别
        }else if(data.user.sex==2){
            $(".personBox p").eq(1).find("b").html("未填写");//性别
        }
        $(".personBox p").eq(2).find("b").html(data.user.classNo);//学号
        $(".personBox p").eq(3).find("b").html(data.user.school+" "+data.user.className);//学校
        $(".personBox p").eq(4).find("b").html(data.user.phone);//联系方式
        $(".personBox p").eq(5).find("b").html(data.user.email);//邮箱
        //未完
    },
    //判断是否已签到
    isSign:function () {
        if(localStorage.getItem("sign")=="signTrue"){
            $(".btn").html("已签到").css("background","#ccc")
        }
    },
    //签到
    sign:function () {
        $.ajax({
            type:"post",
            url:urlT+"/api/signin/add.json",
            data:{
                token:localStorage.getItem("token"),
            },
            success:function(data){
               // console.log(data)
                if(data.code==200){
                    //alert("签到成功")
                    $(".btn").html("已签到").css("background","#ccc")
                    localStorage.setItem("sign","signTrue")
                    //
                    //更新签到列表
                    $.ajax({
                        type:"post",
                        url:urlT+"/api/signin/update.json",
                        data:{
                            token:localStorage.getItem("token"),
                        },
                        success:function(data){
                            if(data.code==200){
                                personObj.showList()
                            }

                        }
                    })
                }
            }
        })
    }
}


personObj.showList();
//判断是否已签到
personObj.isSign();

//毫秒转时间
function getTime(time) {
	return new  Date(time).toLocaleDateString()
}
