

var AdminStudentObject={
    selectChange:function () {
        var index=$(".se").find("option:selected").index()+1;
        $(".newsBox"+index).show().siblings("div").hide();
        AdminStudentObject.contentType=$(".se").find("option:selected").attr("contentType");


    },
    //保存
    saveAll:function(index){
        var content=eval("ue"+index).getContent();
        if(content==""){
            alert("请全部填写")
        }else {
            AdminStudentObject.ajaxA(content)
        }
    },
    //
    ajaxA:function(content){
        $.ajax({
            type:"post",
            url:urlT+"",
            data:{
                token:localStorage.getItem("token"),
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
    //上传文件
    fileUpLoad:function (fileId) {
        console.log(fileId)
        $.ajaxFileUpload({
            url :  urlT+ "/api/file/uploadfile1.json",
            type : 'post',
            secureuri : false, // 一般设置为false
            fileElementId : fileId, // 上传文件的id、name属性名
            dataType : 'application/json',
            async : true,
            success : function(data, status) {
                console.log(data,status)
                if(status=="success"){
                    //上传
                    alert("上传成功");
                }
            },

        });
    },



}







