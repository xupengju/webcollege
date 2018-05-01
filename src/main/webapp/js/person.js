//签到列表
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
			$(".signTable").append(`<tr><td>${list[i].id}</td><td>${list[i].createTime}</td><td>${isSign}</td></tr>`)
		}
	}
})

