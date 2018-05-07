
//分页器
$("#page").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
//师资分页器
$("#page2").paging({
	pageNo:1,
	totalPage: 9,
	//totalSize: 300,
	callback: function(num) {
		alert(num)
	}
})
//左侧点击切换
$(".leftNav li").click(function(){
	var $index=$(this).index()+1
	a($(this),$index)
	localStorage.setItem("studentIndex",$index-1)
})
var $ind=localStorage.getItem("studentIndex")
if($ind){
	a($(".leftNav li").eq($ind),parseInt($ind)+1)
}
//提交问卷
function check() {
    var kvName = {}, allSelected = true;
    $('input:radio').each(function () {
        if (kvName[this.name]) return true;
        if ($('[name="' + this.name + '"]:checked').length == 0) {
            alert('有未选择项');
            return allSelected = false;
        }
        kvName[this.name] = true//标志此组已经检查过，剩余的不需要遍历了，上面的第一句直接继续检查下一组
    });

    if (allSelected) {
    	alert('提交成功')
    }
}
