

var AdminInnovationObject={
    contentType:10,
    ////实习管理界面下拉
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();
        AdminInnovationObject.contentType=$(".se").find("option:selected").attr("contentType");

    },
    //上传内容
    saveAll:function(index,v){
        var image=$(".newsBox"+index+" img").attr("src")
        if(AdminInnovationObject.contentType==12){//企业合作项目
            var resourceName=$(".newsBox3").find(".tit").val();
            var createTime=$(".newsBox3 .timeA").val();
            createTime=createTime.split("T").join(" ")+":00"
            var content=eval("ue"+index).getContent();

            if(resourceName=="" || createTime=="" || content==""){
                alert("请全部填写")
            }else{
                if($("#avatar"+v)){
                    if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                        alert("请上传图片")
                    }else{
                        AdminInnovationObject.ajaxA(resourceName,createTime,content,image)
                    }
                }

            }
        }else{
            if(index!=null){

                var content=eval("ue"+index).getContent();
                if(content==""){
                    alert("请全部填写")
                }else{
                    if($("#avatar"+v)){
                        if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                            alert("请上传图片")
                        }else{
                            AdminInnovationObject.ajaxC(content,image)
                        }
                    }
                }
            }
        }


        //console.log(content,AdminInnovationObject.contentType)

    },
    //校企合作项目   报错。。。。。。。。。。。。。。。
    ajaxA:function(resourceName,createTime,content,image){
        console.log(resourceName)
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/add.json",
            data:{
                token:localStorage.getItem("token"),
                resourceName:resourceName,
                type:9,
                createTime:createTime,
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

    ajaxC:function(content,image){
        $.ajax({
            type:"post",
            url:urlT+"/api/baseInfo/add.json",
            data:{
                token:localStorage.getItem("token"),
                contentType:AdminInnovationObject.contentType,
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







