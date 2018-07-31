

var AdminSocialObject={
    contentType:26,
    ////实习管理界面下拉
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();
        AdminSocialObject.contentType=$(".se").find("option:selected").attr("contentType");

    },
    //上传内容
    saveAll:function(index,v){
        var image=$(".newsBox"+index+" img").attr("src")
        if(index==null){//学生实习
            var resourceName=$(".newsBox9").find(".tit").val();
            if(resourceName==""){
                alert("请全部填写")
            }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                alert("请上传图片")
            }else{
                AdminSocialObject.ajaxA(resourceName,image)
            }


        }else{
            var content=eval("ue"+index).getContent();
            if(content==""){
                alert("请全部填写")
            }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                alert("请上传图片")
            }else{
                AdminSocialObject.ajaxC(content,image)
            }
        }


        //console.log(content,AdminSocialObject.contentType)

    },
    //学生实习
    ajaxA:function(resourceName,image){
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/add.json",
            data:{
                token:localStorage.getItem("token"),
                resourceName:resourceName,
                type:10,
                image:image
            },
            success:function (data) {
                console.log(data)
                if(data.code==10001){
                    alert("用户未登录")
                    window.location.href="login.vm"
                }else if(data.code==10004){
                    alert("用户未授权")
                    window.location.href="login.vm"
                }else{
                    alert("上传成功")
                }
            }

        })
    },

    ajaxC:function(content,image){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/add.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:AdminSocialObject.contentType,
                content:content,
                image:image
            },
            success:function (data) {
                console.log(data)
                if(data.code==10001){
                    alert("用户未登录")
                    window.location.href="login.vm"
                }else if(data.code==10004){
                    alert("用户未授权")
                    window.location.href="login.vm"
                }else{
                    alert("上传成功")
                }
            }

        })
    },

    //单图上传  待调试
    imgUpLoad:function (fileId, ImgId) {
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

    }


}







