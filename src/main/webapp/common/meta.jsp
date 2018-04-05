<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/4
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript">
  var ctx = "${pageContext.request.contextPath}";
</script>
<link href="${ctx}/css/basic/emojify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script type="text/javascript">
	jQuery.browser={};(function(){jQuery.browser.msie=false; jQuery.browser.version=0;if(navigator.userAgent.match(/MSIE ([0-9]+)./)){ jQuery.browser.msie=true;jQuery.browser.version=RegExp.$1;}})();
</script>
<script type="text/javascript" src="<c:url value="/js/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/checkForm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jq-custom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/date/WdatePicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.tools.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common/html5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/global.js"/>"></script>
<c:set var="webVersion" value="201804031814"/>