

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
                //console.log(data)
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
    //上传文件
    fileUpLoad:function (fileId,word) {
        //console.log(fileId)
        $.ajaxFileUpload({
            url :  urlT+ "/api/file/uploadfile1.json",
            type : 'post',
            secureuri : false, // 一般设置为false
            fileElementId : fileId, // 上传文件的id、name属性名
            dataType : 'application/json',
            async : true,
            success : function(data, status) {
                //console.log(data,status)
                if(status=="success"){
                    //上传
                    var w=data.substring(81,data.length-10)
                    //console.log(w)
                    if(w!==word){
                        alert("请上传文件名为'"+word+"'的文件")
                    }else{
                        alert("上传成功");
                    }

                }
            }
        });
    },
    //导入学生列表
    studentUpload:function(fileId){
     
        var fileDir = $("#"+fileId).val();
        var suffix = fileDir.substr(fileDir.lastIndexOf("."));

        if(fileDir == ""){
            alert("选择需要导入的Excel文件!");

        }else if(".xls" != suffix && ".xlsx" != suffix ){
            alert("选择Excel格式的文件导入!");
        }else{
            $.ajaxFileUpload({
                url :  urlT+ "/api/file/importExcel.json",
                type: 'post',
                secureuri: false,
                data: {},
                fileElementId: "file4",
                dataType: 'json',
                success: function (rel, status) {
                    //console.log(rel)
                    if (rel.code == 200) {
                       alert("导入成功")
                    } else{
                        alert(rel.message)
                    }
                }
            })
        }
    }


}







