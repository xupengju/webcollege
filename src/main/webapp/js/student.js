
//分页器
$("#page").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
//师资分页器
$("#page2").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
//左侧点击切换
$(".leftNav li").click(function(){
	var $index=$(this).index()+1
	a($(this),$index)
	localStorage.setItem("studentIndex",$index-1)
})
var $ind=localStorage.getItem("studentIndex")
if($ind){
	a($(".leftNav li").eq($ind),parseInt($ind)+1)
}

var studentObj={
	//列表
	signList:function () {
		$.ajax({
			type:"post",
			url:urlT+"/api/signin/list.json",
			data:{
				token:localStorage.getItem("token")
			},
			success:function (data) {
				console.log(data)
				if(data.code==10001){
					alert(data.message)
				}else{
                    for(var i=0;i<data.result.length;i++){
                        $(".ratingForm table").append(studentObj.getSignList(data.result[i]))

                    }
				}


            }
		})
    },
	getSignList:function (data) {
		var list="<tr><td>"
			+data.userName+"</td><td>123467890123446767</td><td>"
			+new Date(data.createTime).toLocaleDateString()+"</td></tr>"
    	return list
	},
	//安全责任书
    manual:function () {
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:typeNumber
            },
            success:function (data) {
                console.log(data)
            }
        })
    }
}
studentObj.signList()
studentObj.manual(23)
studentObj.manual(24)
studentObj.manual(29)