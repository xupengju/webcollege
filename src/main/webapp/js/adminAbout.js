

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
            AdminAboutObject.ajaxC(content)
        }

    },
    //企业教师保存
    saveAllT:function(index,v,type){

        var content=eval("ue"+index).getContent();
        var teacherName=$(".newsBox"+index+" .teacherName").val();
        console.log(content)
        if(content=="" || teacherName==""){
            alert("请全部填写")
        }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
            alert("请上传图片")
        }else{
            AdminAboutObject.ajaxA(type,teacherName,content)
        }

    },
    //保存资源
    saveAllR:function(index,v){
        var content=eval("ue"+index).getContent();
        console.log(content)
        var title=$(".newsBox"+index+" .titT").val();
        var createTime=$(".newsBox"+index+" .createTime").val();
        console.log(createTime)
        //时间。。。。。。。。。。。。。。。
        if(content=="" || title=="" || createTime==""){
            alert("请全部填写")
        }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
            alert("请上传图片")
        }else{
            AdminAboutObject.ajaxR(content,title,createTime)
        }
    },
    //资源
    ajaxR:function(content,title,createTime){
        $.ajax({
            type:"post",
            url:urlT+"/api/achievement/add.json",
            data:{
                token:localStorage.getItem("token"),
                title:title,
                createTime:createTime,
                content:content
            },
            success:function (data) {
                console.log(data)
                if(data.code==200){//成功
                    alert("上传成功")
                }else{
                    alert(data.message)
                }
            }

        })
    },
    //企业教师和学校教师
    ajaxA:function(type,teacherName,content){
        $.ajax({
            type:"post",
            url:urlT+"/api/teacher/add.json",
            data:{
                token:localStorage.getItem("token"),
                type:type,
                teacherName:teacherName,
                /*title:title,
                link:link,*/
                content:content
            },
            success:function (data) {
                console.log(data)
                if(data.code==200){//成功
                    alert("上传成功")
                }else{
                    alert(data.message)
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
                contentType:AdminAboutObject.contentType,
                /*title:title,
                link:link,*/
                content:content
            },
            success:function (data) {
                console.log(data)
                if(data.code==200){//成功
                    alert("上传成功")
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







