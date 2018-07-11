<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/7/1/001
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>导入Excel</title>
    <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript">
        //ajax 方式上传文件操作
        $(document).ready(function(){
            $('#btn').click(function(){
                if(checkData()){
                    $('#form1').ajaxSubmit({
                        url:'api/file/ajaxUpload.json',
                        dataType: 'text',
                        success: resutlMsg,
                        error: errorMsg
                    });
                    function resutlMsg(msg){
                     //   alert(msg);
                        $("#upfile").val("");
                    }
                    function errorMsg(){
                        alert("导入excel出错!");
                    }
                }
            });
        });

        //JS校验form表单信息
        function checkData(){
            var fileDir = $("#upfile").val();
       //     alert(fileDir);
            var suffix = fileDir.substr(fileDir.lastIndexOf("."));
            if("" == fileDir){
                alert("选择需要导入的Excel文件!");
                return false;
            }
            if(".xls" != suffix && ".xlsx" != suffix ){
                alert("选择Excel格式的文件导入!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form id="form1" name="form1" method="POST" enctype="multipart/form-data" action="api/file/importExcel.json">
<table>
    <tbody>
    <tr>
        <td>上传文件: </td>
        <td> <input type="file" name="upfile" id="upfile" /></td>
    </tr>
    <tr>
        <td><input type="submit" value="提交" onclick="return checkData()" /></td>
        <td><input type="button" value="ajax方式提交" name="btn" id="btn" /></td>
    </tr>
    </tbody>
</table>
</form>
</body>
</html>
