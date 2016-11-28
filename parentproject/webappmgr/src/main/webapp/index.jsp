<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>运维管理平台</title>
	<%--全局引入的css文件 --%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/themes/material/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/themes/icon.css"/>    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default.css"/>
    <link href="<%=request.getContextPath() %>/common/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
   
    
    <%--全局引入的js文件 --%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/user/js/index.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath() %>/common/ztree/js/jquery.ztree.core-3.5.js" type="text/javascript"></script>
	  <script type="text/javascript">
	  	var contextPath = '<%=request.getContextPath() %>';
	  //从一个页面获取另一个页面的url
	    function getQueryString(name) {
	    	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    	var r = window.location.search.substr(1).match(reg);
	    	if (r != null) 
	    		return unescape(r[2]); 
	    	return null;
	    }
		
		$.extend($.fn.validatebox.defaults.rules, {
	        CHS: {
	          validator: function (value, param) {
	            return /^[\u0391-\uFFE5]+$/.test(value);
	          },
	          message: '请输入汉字'
	        },
	        english : {// 验证英语
	              validator : function(value) {
	                  return /^[A-Za-z]+$/i.test(value);
	              },
	              message : '请输入英文'
	          },
	          ip : {// 验证IP地址
	              validator : function(value) {
	                  return /\d+\.\d+\.\d+\.\d+/.test(value);
	              },
	              message : 'IP地址格式不正确'
	          },
	        ZIP: {
	          validator: function (value, param) {
	            return /^[0-9]\d{5}$/.test(value);
	          },
	          message: '邮政编码不存在'
	        },
	        QQ: {
	          validator: function (value, param) {
	            return /^[1-9]\d{4,10}$/.test(value);
	          },
	          message: 'QQ号码不正确'
	        },
	        mobile: {
	          validator: function (value, param) {
	            return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
	          },
	          message: '手机号码不正确'
	        },
	        tel:{
	          validator:function(value,param){
	            return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
	          },
	          message:'电话号码不正确'
	        },
	        mobileAndTel: {
	          validator: function (value, param) {
	            return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
	          },
	          message: '请正确输入电话号码'
	        },
	        number: {
	          validator: function (value, param) {
	            return /^[0-9]+.?[0-9]*$/.test(value);
	          },
	          message: '请输入数字'
	        },
	        money:{
	          validator: function (value, param) {
	           	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
	           },
	           message:'请输入正确的金额'

	        },
	        mone:{
	          validator: function (value, param) {
	           	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
	           },
	           message:'请输入整数或小数'

	        },
	        integer:{
	          validator:function(value,param){
	            return /^[+]?[1-9]\d*$/.test(value);
	          },
	          message: '请输入最小为1的整数'
	        },
	        integ:{
	          validator:function(value,param){
	            return /^[+]?[0-9]\d*$/.test(value);
	          },
	          message: '请输入整数'
	        },
	        range:{
	          validator:function(value,param){
	            if(/^[1-9]\d*$/.test(value)){
	              return value >= param[0] && value <= param[1]
	            }else{
	              return false;
	            }
	          },
	          message:'输入的数字在{0}到{1}之间'
	        },
	        minLength:{
	          validator:function(value,param){
	            return value.length >=param[0]
	          },
	          message:'至少输入{0}个字'
	        },
	        maxLength:{
	          validator:function(value,param){
	            return value.length<=param[0]
	          },
	          message:'最多{0}个字'
	        },
	        //select即选择框的验证
	        selectValid:{
	          validator:function(value,param){
	            if(value == param[0]){
	              return false;
	            }else{
	              return true ;
	            }
	          },
	          message:'请选择'
	        },
	        idCode:{
	          validator:function(value,param){
	            return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
	          },
	          message: '请输入正确的身份证号'
	        },
	        loginName: {
	          validator: function (value, param) {
	            return /^[\u0391-\uFFE5\w]+$/.test(value);
	          },
	          message: '登录名称只允许汉字、英文字母、数字及下划线。'
	        },
	        equalTo: {
	          validator: function (value, param) {
	            return value == $(param[0]).val();
	          },
	          message: '两次输入的字符不一至'
	        },
	        englishOrNum : {// 只能输入英文和数字
	              validator : function(value) {
	                  return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
	              },
	              message : '请输入英文、数字、下划线或者空格'
	          },
	         xiaoshu:{ 
	            validator : function(value){ 
	            return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
	            }, 
	            message : '最多保留两位小数！'    
	        	},
	        ddPrice:{
	        validator:function(value,param){
	          if(/^[1-9]\d*$/.test(value)){
	            return value >= param[0] && value <= param[1];
	          }else{
	            return false;
	          }
	        },
	        message:'请输入1到100之间正整数'
	      },
	      jretailUpperLimit:{
	        validator:function(value,param){
	          if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
	            return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
	          }else{
	            return false;
	          }
	        },
	        message:'请输入0到100之间的最多俩位小数的数字'
	      },
	      rateCheck:{
	        validator:function(value,param){
	          if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
	            return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
	          }else{
	            return false;
	          }
	        },
	        message:'请输入0到1000之间的最多俩位小数的数字'
	      },
	      md: { //校验有效结束时间要大于有效开始时间的方法
				validator: function(value, param){ 
				var startTime2 = $(param[0]).datetimebox('getValue'); 
				var d1 = $.fn.datebox.defaults.parser(startTime2); 
				var d2 = $.fn.datebox.defaults.parser(value); 
				varify=d2>=d1; 
				return varify; 
			
				}, 
				message: '结束时间要大于等于开始时间！' 
		  },
		  equalTo: { 
		    	validator: function (value, param) { 
		    		return $(param[0]).val() == value;
	    		}, message: '两次密码输入不一致！' }
	      });
	  
	
    </script>


 
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<!-- message中存放的是登录信息 -->
<input type="hidden" name="message" id="message" value="${message}">

<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="false" border="false" style="overflow: hidden; height: 50px;
        background: url('images/top.jpg')  repeat-x center 50%;
        color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head"> 
       	 <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut" onclick="logout()">安全退出</a>
        </span>
         <span style="float:right; padding-right:20px;" class="head"> 
         		当前登录用户：<span id="loginuser">admin</span>
         </span>
        <span style="padding-left:10px; font-size: 36px; float:left;font-family:隶书;">运维管理平台</span>
    </div>
    <div region="south" split="false" style="height: 30px; background: #D2E0F2; ">
        <div class="footer"><center>2015-2016&nbsp;&nbsp;&nbsp;&nbsp;辽宁移动版权所有</center></div>
    </div>
    <div region="west" hide="true" split="false" title="菜单" style="width:180px;" id="west">
<div id='wnav' class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden;">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="首页" style="padding:20px;overflow:hidden; " id="home">
			<div style="font-size:70px;text-shadow: 0 1px 1px rgba(1,1,1,10);text-align: center;box-shadow: 0px 5px 60px deepskyblue;    border-radius: 30px;color: deepskyblue;font-family: 隶书;width: 55%;margin-left:auto;margin-right:auto;    position: relative;    top: 30%;"></div>

			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save" closed=true style="width: 300px; height: 210px; padding: 5px;
        background: #fafafa;display:none;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <form id="updatePasswordForm" action="<%=request.getContextPath() %>/account/updatePassword.action" method="post">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input class="easyui-validatebox textbox" type="password" id="password" name="password" data-options="required:true" validType="length[6,15]"   ></input></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input class="easyui-validatebox textbox" type="password" id="confirmPassword" name="confirmPassword" data-options="required:true"  validType="equalTo['#password']"   ></input></td>
                    </tr>
                </table>
                </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>