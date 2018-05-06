var ue1 = UE.getEditor('editor1',{
    toolbars: [
        ['source', 'undo', 'redo', 'bold','italic','underline','strikethrough',
            'forecolor','lineheight','paragraph','fontfamily','fontsize','justifyleft','justifyright',
            'justifycenter','justifyjustify','simpleupload','unlink','link']
    ],
    initialFrameWidth:600,  //初始化编辑器宽度,默认1000
    initialFrameHeight:300,  //初始化编辑器高度,默认320

});
var ue2= UE.getEditor('editor2',{
    toolbars: [
        ['source', 'undo', 'redo', 'bold','italic','underline','strikethrough',
            'forecolor','lineheight','paragraph','fontfamily','fontsize','justifyleft','justifyright',
            'justifycenter','justifyjustify','simpleupload','unlink','link']
    ],
    initialFrameWidth:600,  //初始化编辑器宽度,默认1000
    initialFrameHeight:300,  //初始化编辑器高度,默认320

});
var ue3= UE.getEditor('editor3',{
    toolbars: [
        ['source', 'undo', 'redo', 'bold','italic','underline','strikethrough',
            'forecolor','lineheight','paragraph','fontfamily','fontsize','justifyleft','justifyright',
            'justifycenter','justifyjustify','simpleupload','unlink','link']
    ],
    initialFrameWidth:600,  //初始化编辑器宽度,默认1000
    initialFrameHeight:300,  //初始化编辑器高度,默认320

});
//alert()
$(".se").change(function () {
    var index=$(this).find("option:selected").index()+1
    $(".newsBox"+index).show().siblings("div").hide()
})