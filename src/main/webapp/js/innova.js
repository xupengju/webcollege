
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
	localStorage.setItem("innovaIndex",$index-1)
})

var $ind=localStorage.getItem("innovaIndex")
if($ind){
	a($(".leftNav li").eq($ind),parseInt($ind)+1)
}
var InnovationObject={
    //众创空间
    space:function (typeNumber) {
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
    },
	//校企合作项目
    schoolEnterprise:function () {
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/list.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:10
            },
            success:function (data) {
                console.log(data)
            }
        })
    }
}
//type??????????
InnovationObject.space(6)
InnovationObject.space(10)
InnovationObject.space(13)

