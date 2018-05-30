

var AdminAboutObject={
    contentType:1,
    ////关于我们界面下拉
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();
        AdminAboutObject.contentType=$(".se").find("option:selected").attr("contentType");
        if(AdminAboutObject.contentType==undefined){
            return;
        }

    },
    //保存
    saveAll:function(index,v){
       /* var title=$(".newsBox"+index).find(".tit").val();
        var link=$(".newsBox"+index).find(".linkT").val();*/
        var content=eval("ue"+index).getContent();
        console.log(content)

        if(content==""){
            alert("请全部填写")
        }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
            alert("请上传图片")
        }else{
            AdminAboutObject.ajaxC()
        }

    },
    ajaxC:function(content){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/add.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:AdminAboutObject.contentType,
                /*title:title,
                link:link,*/
                content:content
            },
            success:function (data) {
                console.log(data)
                if(data.code==200){//成功

                }else{
                    alert(data.message)
                }
            }

        })
    },
    //

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







