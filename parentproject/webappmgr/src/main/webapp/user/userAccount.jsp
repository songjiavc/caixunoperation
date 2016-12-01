<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<head>
    
    <title>测试页面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/themes/material/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/themes/icon.css"/>    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default.css"/>
    <link href="<%=request.getContextPath() %>/common/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
   
    
    <%--全局引入的js文件 --%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/locale/easyui-lang-zh_CN.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/user/js/useraccount.js"></script>  
    <script type="text/javascript">
   		
    	var contextPath = '<%=request.getContextPath() %>';
    	
    	var toolbar = [{
	  	    text:'添加',
	  	    iconCls:'icon-add',
	  	    handler:function(){
	  	    	//initProvince('add','privinceA','');//默认选中全部，则全部下是没有市数据的
	  	    	$("#addAccount").dialog('open');
	  	    }
	  	},{
	  	    text:'删除',
	  	    iconCls:'icon-remove',
	  	    handler:function(){
	  	    	deleteAccountByIds();
	  	    }
	  	}];
	</script>
	 <style type="text/css" scoped="scoped">
	  	 .ftitle{
	  			width:100%;
	  			
	  			float : left;
	  			margin-top : 10px;
	  			margin-bottom: 10px;
	  			font-family:'微软雅黑',
	  		}
	  		.ftitle label{
	  			margin-left: 30px;
	  			width:100px;
	  		}
	  		.ftitle .commonInput{
	  			margin-right: 50px;
	  			float : right;
	  			width: 200px;
	  			border-radius : 5px;
	  		}
	  		.ftitle .textbox{
	  			float : left;
	  			width: 200px;
	  			margin-left: 30px;
	  			border-radius : 5px;
	  		}
	  		
	  		
	  		.td_font{
	  			font-weight:bold;
	  		}
	  		
	  		.input_border{
	  			width:150px;
	  			border-radius : 5px;
	  		}
	  		
	  		#main-layout{     min-width:1050px;     min-height:240px;     overflow:hidden; }
	  	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'查询条件',split:true" style="height:100px;">
	        <table style="border: none;margin:10 10 10 20">
		    	<tr >
		    		<td colspan="1" >帐号：</td>
		    		<td colspan="2">
		    			<input id="searchFormNumber" class="input_border"  type="text" name="searchFormNumber"  />  
		    		</td>
		    		<td colspan="1">姓名：</td>
		    		<td colspan="2">
		    			<input id="searchFormName" class="input_border"  type="text" name="searchFormName"  />  
		    		</td>
		    		<td colspan="1">电话：</td>
		    		<td colspan="2">
		    			<input id="searchFormTelephone" class="input_border"  type="text" name="searchFormTelephone"  />  
		    		</td>
		    		<td colspan="1">状态：</td>
		    		<td colspan="2">
			    		<select id="searchFormStatus" class="easyui-combobox" name="searchFormStatus" style="width:100px;">
						    <option value="1">正常</option>
						    <option value="2">锁定</option>
						    <option value="3">注销</option>
						</select>
					</td>
		    		<td  colspan="2">
		    			<a class="easyui-linkbutton" onclick="initDatagrid()" data-options="iconCls:'icon-search'">查询</a>
		    			<a class="easyui-linkbutton" onclick="reset()" data-options="iconCls:'icon-redo'">重置</a>
		    		</td>
	    		</tr>
	    	</table>	
	</div>
	<div data-options="region:'center'" style="background:#eee;">
		<table id="accountDataGrid" class="easyui-datagrid" title="用户列表" 
				data-options="toolbar:toolbar" ></table>
	</div>
	<!-- 添加权限弹框 -->
  	<div id="addAccount" class="easyui-dialog" closed="true" title="添加用户" style="width:400px;height:350px;"
            data-options="
            modal:true,
                iconCls: 'icon-save',
                buttons: [{
                    text:'提交',
                    iconCls:'icon-ok',
                    handler:function(){
                        submitAddAccount();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#addAccount').dialog('close');
                    }
                }]
            ">
		<form id="addAccountForm" method="post" >
	        <div class="ftitle" >
	            <label for="code">用户编码:</label>
	            <input class="easyui-validatebox commonInput" type="text" id="codeA" name="code"  data-options="required:true,validType:['englishOrNum','length[0,20]']""
	              validType="checkCodes['#codeA','']" missingMessage="用户编码不可以为空" invalidMessage="只允许输入英文或数字并且不能超过20个字符!"></input>
	        </div>
	        <div class="ftitle">
	            <label for="name" >用户姓名:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="name"  data-options="required:true,validType:['CHS','length[0,20]']"  missingMessage="用户真实姓名"></input>
	        </div>
	        <div class="ftitle">
	        	<label for="telephone" >用户电话:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="telephone"  data-options="required:true,validType:['mobile']" missingMessage="移动手机号码"></input>
	        </div>
	        <div class="ftitle">
	            <label  for="password">密码:</label>
	            <input class="easyui-validatebox commonInput" type="password" id="password" name="password" data-options="required:true"
	            validType="length[6,20]"   missingMessage="密码不可以为空" ></input>
	        </div>
	        <div class="ftitle">
	            <label for="confirmPassword" >确认密码:</label>
	            <input class="easyui-validatebox commonInput" type="password" name ="confirmPassword"  data-options="required:true" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"></input>
	        </div>
	       
	       	<div class="ftitle">
	            <label for="status">是否启用:</label>
	            <div style="float:right;margin-right: 40%;">
		            <input class="easyui-validatebox" type="radio" name="status"  value="1" checked>是</input>
		            <input class="easyui-validatebox" style="margin-left:10px;" type="radio" name="status" value="0">否</input>
	        	</div>
	        </div>
          </form>
     </div>  
     <!-- 修改权限弹框 -->
    <div id="updateAccount" class="easyui-dialog" closed="true" title="修改用户" style="width:400px;height:300px;"
            data-options=" 
            modal:true,
                iconCls: 'icon-save',
                buttons: [{
                    text:'提交',
                    iconCls:'icon-ok',
                    handler:function(){
                        submitUpdateAccount();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#updateAccount').dialog('close');
                    }
                }]
            ">
		<form id="updateAccountForm" method="post" novalidate>
	       	<input type="hidden" name="id" id="idU" />
	      	<div class="ftitle">
	            <label for="code" >用户帐号:</label>
	          	<input class="easyui-validatebox commonInput" type="text" id="codeU" name="code" data-options="required:true" 
	             	validType="checkCodes['#codeU','idU']" missingMessage="权限编码不可以为空" >
	            </input>
	        </div>
	        <div class="ftitle">
	            <label for="name" >用户姓名:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="name"  data-options="required:true"  missingMessage="用户真实姓名"></input>
	        </div>
	        <div class="ftitle">
	        	<label for="telephone" >用户电话:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="telephone" missingMessage="用户电话"></input>
	        </div>
          	<div class="ftitle">
	            <label for="status">是否启用:</label>
	            <div style="float:right;margin-right: 40%;">
		            <input class="easyui-validatebox" type="radio" name="status"  value="1" checked>是</input>
		            <input class="easyui-validatebox" style="margin-left:10px;" type="radio" name="status" value="0">否</input>
	        	</div>
	        </div>
    		</form>
       </div>
       
       
    <div id="selectRoleDiv" class="easyui-dialog"  closed="true" title="角色选择" style="width:800px;height:500px;padding:0px;border:0;top:40px;"
            data-options=" 
   				 modal:true,
                iconCls: 'icon-search',
                 onBeforeClose:function(){
        			  selectRoleBeforeClose();
   		 		},
                buttons: [{
                    text:'保存',
                    iconCls:'icon-ok',
                    handler:function(){
                        submitSelRoles();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#selectRoleDiv').dialog('close');
                    }
                }]"
               >
    	<div class="easyui-layout" style="overflow-y: hidden;height:100%;padding:0;" >
    	 	<div region="north" style="height:80%;">
    	 		<input type="hidden" id = "userId" ></input>
    	 		<table id="selectRoleGrid" class="easyui-datagrid" style="width:97.5%;height:97%;border:0;" title="待选列表" ></table>
    	 	</div>
    	 	<div region="center" style="height:20%;padding:0;">
    	 		<table id="selectedRoleGrid" class="easyui-datagrid" style="width:97.5%;height:97%;border:0;" title="已选列表" ></table>
    	 	</div>
    	</div>
 	</div>  
</body>
</html>