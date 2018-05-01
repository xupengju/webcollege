//签到列表
function showList(){
    $.ajax({
        type:"post",
        url:"/api/signin/list.json",
        data:{
            token:localStorage.getItem("token")
        },
        success:function(data){
           console.log(data)
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
        }
    })
}
showList()

//添加签到接口
$(".btn").click(function () {
    $.ajax({
        type:"post",
        url:"/api/signin/add.json",
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
					url:"/api/signin/update.json",
					data:{
                        token:localStorage.getItem("token"),
					},
					success:function(data){
						if(data.code==200){
                            showList()
						}

					}
				})
			}
        }
    })
})
//毫秒转时间
function getTime(time) {
	return new  Date(time).toLocaleDateString()
}
