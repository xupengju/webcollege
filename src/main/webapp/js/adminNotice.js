var AdminNoticeObject={
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();

    },
    //保存
    saveAll:function(typeA,index,v){
        var tittle=$(".newsBox"+index+" .tit").val();
        var createTime=$(".newsBox"+index+" .timeA").val();
        createTime=createTime.split("T").join(" ")+":00"
        console.log(createTime)
        var content=eval("ue"+index).getContent();
        var image=$(".newsBox"+index+" img").attr("src")
        if(content=="" || tittle=="" || createTime==""){
            alert("请全部填写")
        }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
            alert("请上传图片")
        }else{
            AdminNoticeObject.ajaxA(typeA,tittle,createTime,content,image)
        }

    },
    ajaxA:function(typeA,tittle,createTime,content,image){
        console.log(createTime)
        $.ajax({
            type:"post",
            url:urlT+ "/api/notice/add.json",
            data:{
                token:localStorage.getItem("token"),
                title:tittle,
                createTime:createTime,
                link:"http://www.baidu.com",
                type:typeA,
                content:content,
                image:image
            },
            success:function (data) {
                console.log(data)
                if(data.code==200){//成功
                    alert("上传成功")
                }else if(data.code==10001){
                    window.location.href="login.vm"

                }else{
                    alert(data.message)
                }
            }
        })
    },
    //单图上传  待调试
    imgUpLoad:function (fileId, ImgId) {
        console.log(fileId,ImgId)
        $.ajaxFileUpload({
            url :  urlT+ "/api/file/upload.json",
            type : 'post',
            secureuri : false, // 一般设置为false
            fileElementId : fileId, // 上传文件的id、name属性名
            dataType : 'application/json',
            async : true,
            success : function(data, status) {
                /*console.log(typeof data)
                console.log(data,status)*/
                if(status=="success"){
                    var srcUrl=data.substring(59,data.length-6);
                    $("#" + ImgId).attr("src", srcUrl);
                }
            },

        });
    },
    imgSave:function () {

    },
}