//验证码
var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','0']
var colorArr=["red","blue","#cd00ce","orange","green","#0fb3ad","gray","#09a2b4","#290e8b"]
var rotateArr=["4deg","5deg","6deg","7deg","8deg","9deg","10deg","11deg","12deg","-4deg","-5deg","-6deg","-7deg","-8deg","-9deg","-10deg","-11deg","-12deg"]
var translateArr=["0","1px","2px","3px","3.5px","4px","-1px","-2px","-2.5px"]

var loginObj={
    codeB:document.getElementById("code").getElementsByTagName("b"),
    //随机验证码
    getRandomCode:function () {
        var str=''
        while(str.length<4){
            var index=parseInt(Math.random()*100);
            while(index>36){
                index=parseInt(Math.random()*100);
            }
            str+=arr[index]
            //console.log(str)
        }
        for(var i=0;i<this.codeB.length;i++){
            this.codeB[i].innerHTML=str[i].toUpperCase();
            this.codeB[i].style.color=colorArr[parseInt(Math.random()*colorArr.length)];
            this.codeB[i].style.webkitTransform="rotateZ("+rotateArr[parseInt(Math.random()*rotateArr.length)]+") translateY("+translateArr[parseInt(Math.random()*translateArr.length)]+")"
        }
    },
    //点击登录按钮
    loginBtn:function () {
        var userName=$(".userName").val();
        var pwd=$(".pwd").val();
        var code1=$(".code").val();
        var codeVal=$("b").eq(0).html()+$("b").eq(1).html()+$("b").eq(2).html()+$("b").eq(3).html();
        var reg=new RegExp(codeVal,"ig");
        //验证码判断
        if(code1==""){
            alert("验证码不能为空")
        }else if(!reg.test(code1)){
            alert("验证码错误")
            loginObj.getRandomCode();
        }else{
            if(userName=="" || pwd==""){
                alert("用户名或密码不能为空")
            }else{
                $.ajax({
                    url:"/api/user/login.json",
                    type:"post",
                    data:{
                        userName:userName,
                        password:pwd,
                    },
                    success:function(dataP){
                        console.log(dataP)
                        if(dataP.code==900){//服务器错误
                            alert(dataP.message)
                        }else if(dataP.code==200){//成功
                            //alert("登陆成功")
                            localStorage.setItem("token",dataP.data.token)
                            localStorage.setItem("userName",userName)
                            window.location.href="person.vm"
                        }else if(dataP.code==10002){//用户名或密码错误
                            alert(dataP.message);
                        }else  if(dataP.code==10005){//密码错误
                            alert(dataP.message);
                        }
                    }
                })
            }

        }
    }
}


loginObj.getRandomCode();



