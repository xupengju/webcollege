

var AdminPractiveObject={
    contentType:4,
    ////实习管理界面下拉
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();
        AdminPractiveObject.contentType=$(".se").find("option:selected").attr("contentType");

    },
    //上传内容
    saveAll:function(index,v,b){


        if(AdminPractiveObject.contentType==4){//规章制度
            var title=$(".newsBox1").find(".tit").val();
            var content=eval("ue"+index).getContent();
            if(title=="" || content==""){
                alert("请全部填写")
            }else{
                AdminPractiveObject.ajaxT(title,content)
            }
        }else{
            if(index!=null){
                if(index==4 || index==6){
                    var content=eval("ue"+index).getContent();
                    if(content==""){
                        alert("请全部填写")
                    }else{
                        AdminPractiveObject.ajaxC(content)
                    }
                }else {
                    var content = eval("ue" + index).getContent();
                    var image=$(".newsBox"+index+" img").attr("src")
                    if (content == "") {
                        alert("请全部填写")
                    } else {
                        if ($("#avatar" + v)) {
                            if ($("#avatar" + v).attr("src") == "img/auditImgDefault.png") {
                                alert("请上传图片")
                            } else {
                                AdminPractiveObject.ajaxD(content,image)
                            }
                        }
                    }
                }
            }else{
                var image=$(".newsBox"+b+" img").attr("src")
                if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                    alert("请上传图片")
                }else{
                    AdminPractiveObject.ajaxA(image)
                }
            }
        }


        console.log(content,AdminPractiveObject.contentType)

    },
    ajaxT:function(title,content){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/add.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:AdminPractiveObject.contentType,
                title:title,
                content:content
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
    ajaxA:function(image){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/add.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:AdminPractiveObject.contentType,
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
    ajaxC:function(content){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/add.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:AdminPractiveObject.contentType,
                content:content
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
    ajaxD:function(content,image){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/add.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:AdminPractiveObject.contentType,
                image:image,
                content:content
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
    //


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







