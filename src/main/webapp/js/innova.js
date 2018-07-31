
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
    space:function (typeNumber,index) {
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/searchOne.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:typeNumber
            },
            success:function (dataA) {
              //  if(dataA.code==200){
                    //console.log(dataA)
                    var da=dataA.data;
                    $(".ri"+index).find("img").attr("src",da.image);
                    $(".ri"+index).find(".word").html(da.content);
               /* }else if(dataA.code==10001){
                    window.location.href="login.vm"
                }
*/
            }
        })
    },
	//校企合作项目
    schoolEnterprise:function (pagenum) {
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/lists.json",
            data:{
                token:localStorage.getItem("token"),
                type:9,
                pagenum:pagenum
            },
            success:function (data) {
             //   if(data.code==200){
                    console.log(data)
                    var re=data.result;
                    for(var i=0;i<re.length;i++){
                        $(".ri3").find(".t").append(InnovationObject.getList(re[i]))
                    }
                    $(".ri3 .t .gain").click(function(){
                        var myID=$(this).attr("myID");
                        localStorage.setItem("myID",myID);
                        localStorage.setItem("typeA","cooperation")
                        window.location.href="detaiAbout.vm"
                    })
                    //分页器
                    var p=data.pages
                    $("#page").paging({
                        pageNo:data.pagenum,
                        totalPage: p,
                        callback: function(num) {
                            $(".ri3").find(".t").html("")
                            InnovationObject.schoolEnterprise(num)
                        }
                    })
               /* }else if(data.code==10001){
                    window.location.href="login.vm"
                }*/

            }
        })
    },
    getList:function (data) {
        // console.log(data)
        var list=""
        list='<p myID="'
            +data.id+'" class="gain"><a href="detaiAbout.vm">'
            +data.resourceName+'</a><span>'
            +new Date(data.createTime).toLocaleDateString()+'</span> </p>'
        return list
    }
}

//type??????????
//众创空间
InnovationObject.space(10,1)
//学生创新
InnovationObject.space(11,2)
//技术服务
InnovationObject.space(13,4)
//校企合作项目
InnovationObject.schoolEnterprise()
