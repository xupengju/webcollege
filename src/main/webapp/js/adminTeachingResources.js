

var AdminTeachingR={
    contentType:1,
    ////关于我们界面下拉
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();

    },
    //保存
    saveAll:function(ind,index,v,type){
        var resourceName=$(".newsBox"+ind+" .tit").val();
        //var createTime=;
        //var link=;
        if(index==null){//有标题和连接
            var link=$(".newsBox"+ind+" .linkT").val();
            if(resourceName=="" || link==""){
                alert("请全部填写")
            }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                alert("请上传图片")
            }else{
                AdminTeachingR.ajaxA(type,resourceName,link)
            }
        }else if(index==8){//标题、时间、连接 教学视频
            var createTime=$(".newsBox"+ind+" .createTime").val();
            var link=$(".newsBox"+ind+" .linkT").val();
            var content=eval("ue"+index).getContent();
            if(content=="" || resourceName=="" || link=="" || createTime==""){
                alert("请全部填写")
            }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                alert("请上传图片")
            }else {
                AdminTeachingR.ajaxB(type, resourceName, link, createTime, content)
            }
        }else{
            var createTime=$(".newsBox"+ind+" .createTime").val();
            var content=eval("ue"+index).getContent();
            if(content=="" || resourceName=="" || createTime==""){
                alert("请全部填写")
            }else if($("#avatar"+v).attr("src")=="img/auditImgDefault.png"){
                alert("请上传图片")
            }else {
                AdminTeachingR.ajaxB(type, resourceName, createTime, content)
            }
        }

    },


    ajaxA:function(type,resourceName,link){
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/add.json",
            data:{
                token:localStorage.getItem("token"),
                resourceName:resourceName,
                type:type,
                link:link,

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
    ajaxB:function(type,resourceName,content,link){
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/add.json",
            data:{
                token:localStorage.getItem("token"),
                resourceName:resourceName,
                type:type,
                link:link
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
    ajaxC:function(type,resourceName,createTime,content){
        $.ajax({
            type:"post",
            url:urlT+"/api/resource/add.json",
            data:{
                token:localStorage.getItem("token"),
                resourceName:resourceName,
                type:type,
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







