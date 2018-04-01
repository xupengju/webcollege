//验证码
var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','0']
var colorArr=["red","blue","#cd00ce","orange","green","#0fb3ad","gray","#09a2b4","#290e8b"]
var rotateArr=["4deg","5deg","6deg","7deg","8deg","9deg","10deg","11deg","12deg","-4deg","-5deg","-6deg","-7deg","-8deg","-9deg","-10deg","-11deg","-12deg"]
var translateArr=["0","1px","2px","3px","3.5px","4px","-1px","-2px","-2.5px"]
var code=document.getElementById("code");
var codeB=code.getElementsByTagName("b")
code.onclick=function(){
	 getRandomCode()
}
function getRandomCode(){
	var str=''
	while(str.length<4){
		var index=parseInt(Math.random()*100);	
		while(index>36){
			index=parseInt(Math.random()*100);
		}
		str+=arr[index]
		console.log(str)
	}	
	for(var i=0;i<codeB.length;i++){
		codeB[i].innerHTML=str[i].toUpperCase();
		codeB[i].style.color=colorArr[parseInt(Math.random()*colorArr.length)];
		codeB[i].style.webkitTransform="rotateZ("+rotateArr[parseInt(Math.random()*rotateArr.length)]+") translateY("+translateArr[parseInt(Math.random()*translateArr.length)]+")"
	}
	//code.innerHTML=str.toUpperCase();
}
getRandomCode()
