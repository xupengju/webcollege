
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
                        $(".ratingForm table tbody").append(studentObj.getSignList(data.result[i]))

                    }
				}


            }
		})
    },
	getSignList:function (data) {
		var list="<tr><td>"
			+data.realName+"</td><td>"
			+data.idCard+"</td><td>"
			+new Date(data.createTime).toLocaleDateString()+"</td></tr>"
    	return list
	},
	//搜索
	searchStudent:function(){
		// /api/signin/conditionalQuery.json  三个搜索条件:  姓名  realName    身份证 idCard   用户ID userId
       	var realName=$(".realName").val();
        var idCard=$(".idCard").val();
        var userID=$(".userID").val();
        $.ajax({
            type:"post",
            url:urlT+"/api/signin/conditionalQuery.json ",
            data:{
                token:localStorage.getItem("token"),
                realName:realName,
                idCard:idCard,
                userId:userID
            },
            success:function (data) {

                console.log(data)
                $(".ratingForm table tbody").html("")
				for(var i=0;i<data.result.length;i++){
					$(".ratingForm table tbody").append(studentObj.getSignList(data.result[i]))

				}

            }
        })
	},
	//安全责任书
    manual:function (typeNumber,index) {
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:typeNumber
            },
            success:function (dataA) {
                //console.log(dataA)
                var da=dataA.data
                $(".ri"+index).find(".word").html(da.content);
            }
        })
    }
}
studentObj.signList()
//安全责任书
studentObj.manual(23,2)
studentObj.manual(24,3)
