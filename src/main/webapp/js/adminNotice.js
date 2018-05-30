var AdminNoticeObject={
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();

    },
    //上传新闻：
    noticeLoad:function(typeA){
        var tittle=$(".newsBox1 .tit").val();
        var linkT=$(".newsBox1 .linkT").val();
        var content=eval("ue"+index).getContent();
        $.ajax({
            type:"post",
            url:urlT+ "/api/notice/add.json",
            data:{
                token:localStorage.getItem("token"),
                tittle:tittle,
                resume:,
                type:typeA
            },
            success:function (data) {
                console.log(data)
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