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
                console.log(data)
                if(data.code==10001){
                    alert(data.message)
                    window.location.href="../login.vm"
                }else{
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
        $(".personBox p").eq(0).find("b").html(data.user.userName);
        //未完
    },
    //判断是否已签到
    isSign:function () {
        $.ajax({
            type:"post",
            url:urlT+"/api/signin/add.json",
            data:{
                token:localStorage.getItem("token"),
            },
            success:function(data){
                if(data.code==10011){
                    $(".btn").html("已签到").css("background","#ccc")
                }
            }
        })
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
