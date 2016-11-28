<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>权限管理</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <%--全局引入的css文件 --%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/themes/material/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/themes/icon.css"/>    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default.css"/>
    <link href="<%=request.getContextPath() %>/common/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
   
    
    <%--全局引入的js文件 --%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath() %>/common/ztree/js/jquery.ztree.core-3.5.js" type="text/javascript"></script>
    
    <script src="<%=request.getContextPath() %>/user/js/authority.js" type="text/javascript"></script>
    
    <script type="text/javascript">
	var contextPath = '<%=request.getContextPath() %>';
	var selectAuthTreeNode;
	var toolbar = [{
  	    text:'添加',
  	    iconCls:'icon-add',
  	    handler:function(){
  	   		//初始化上级权限下拉框值
  	   		//获取选中树节点
  	   		if(selectAuthTreeNode == undefined){
  	   			$.messager.alert('提示', '请在左侧菜单中选则父节点!');
  	   			return false;
  	   		}else{
  	   			//如果存在选中节点，则将选中节点code name 赋给添加权限窗口
  	   			$('#parentAuthNameA').val(selectAuthTreeNode.name);
  	   			$('#parentAuthIdA').val(selectAuthTreeNode.id);
  	   		}
  	   		
  	    	//initParentAuthList('add','','');
  	    	$("#addAuth").dialog('open');
  	    	
  	    }
  	},{
  	    text:'批量删除',
  	    iconCls:'icon-remove',
  	    handler:function(){
  	    	deleteAuthList();
  	    }
  	}];
  	  
  		
	</script>
		<style type="text/css">
			.ztree li button.switch {visibility:hidden; width:1px;}
			.ztree li button.switch.roots_docu {visibility:visible; width:16px;}
			.ztree li button.switch.center_docu {visibility:visible; width:16px;}
			.ztree li button.switch.bottom_docu {visibility:visible; width:16px;}
			
     		 .ftitle{
	  			width:100%;
	  			margin-bottom: 20px;
	  			font-family:'微软雅黑',
	  		}
	  		.ftitle label{
	  			margin-left: 30px;
	  		}
	  		.ftitle .commonInput{
	  			margin-right: 50px;
	  			float : right;
	  			width: 200px;
	  			border-radius : 5px;
	  		}
    </style> 
</head>
<body class="easyui-layout">
	<div class="zTreeDemoBackground left" style="float:left;width: 15%;" data-options="region:'west'">
		<ul id="authorityTree" class="ztree"></ul>
	</div>
    <div style="float:left;width: 85%;" data-options="region:'east'">
    	 <table id="datagrid"  title="权限列表" 
			data-options="rownumbers:false,singleSelect:false,pagination:true,
			collapsible:false,toolbar:toolbar" class="easyui-datagrid">
		 </table>
     </div>
    
     
       <!-- 添加权限弹框 -->
  <div id="addAuth" class="easyui-dialog" title="添加权限" style="width:400px;height:310px;padding:10px"
            data-options="
            	modal:true,
                iconCls: 'icon-save',
                buttons: [{
                    text:'添加',
                    iconCls:'icon-ok',
                    handler:function(){
                        submitAddauth();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#addAuth').dialog('close');
                    }
                }]
            ">
            
		<form id="ff" method="post" novalidate>
	        <div class="ftitle">
	    	    <input type="hidden" name="id" id="idA"/>
	            <label for="code">权限编码:</label>
	            <input class="easyui-validatebox commonInput" type="text" id="codeA" name="code"  
	          	  data-options="required:true"
	             validType="checkCodes['#codeA','idA']" missingMessage="权限编码不可以为空" />
	        </div>
	        <div class="ftitle">
	            <label for="authName">权限名称:</label>
	            <input class="easyui-validatebox commonInput" type="text" id="authNameA" name="authName" data-options="required:true"
	              missingMessage="权限名称不可以为空" ></input>
	        </div>	
	        <div class="ftitle">
	            <input type="hidden" name="parentAuthId" id="parentAuthIdA"/>
	            <label for="parentAuth">上级权限:</label>
	            <input class="easyui-validatebox commonInput" type="text" readonly="true" id="parentAuthNameA" name="parentAuthName" data-options="readonly:true"
	              ></input>
	        </div>
	        <div class="ftitle">
	            <label for="url">权限 url:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="url" ></input>
	        </div>
	        <div class="ftitle">
	            <label for="authImg">权限图片:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="authImg" data-options="required:true"></input>
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
    <div id="updateAuth" class="easyui-dialog"  title="修改权限" style="width:400px;height:310px;padding:10px"
            data-options="
           		modal:true,
                iconCls: 'icon-save',
                buttons: [{
                    text:'修改',
                    iconCls:'icon-ok',
                    handler:function(){
                        submitUpdateauth();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#updateAuth').dialog('close');
                    }
                }]
            ">
		<form id="ffupdate" method="post" novalidate>
	        <div class="ftitle">
	        	<input type="hidden" name="id" id="idU"/>
	            <label for="code">权限编码:</label>
	            <input class="easyui-validatebox commonInput" type="text" id="codeU" name="code" data-options="required:true" readonly="readonly"
	             validType="checkCodes['#codeU','idU']" missingMessage="权限编码不可以为空" invalidMessage="权限编码长度不可以超过20个字符"></input>
	        </div>
	        <div class="ftitle">
	            <label for="authName">权限名称:</label>
	            <input class="easyui-validatebox commonInput" type="text" id="authNameU" name="authName" data-options="required:true"
	             missingMessage="权限名称不可以为空"></input>
	        </div>
	        <div class="ftitle">
	            <input type="hidden" name="parentAuthId" id="parentAuthIdU"/>
	            <label for="parentAuth">上级权限:</label>
	            <input class="easyui-validatebox commonInput" type="text" readonly="true" id="parentAuthNameU" name="parentAuthName" data-options="readonly:true"
	              ></input>
	     
	        </div>
	        <div class="ftitle">
	            <label for="url">权限 url:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="url" ></input>
	        </div>
	        <div class="ftitle">
	            <label for="authImg">权限图片:</label>
	            <input class="easyui-validatebox commonInput" type="text" name="authImg" data-options="required:true"></input>
	        </div>
	         <div class="ftitle">
	            <label for="status">是否启用:</label>
	            <div style="float:right;margin-right: 40%;">
		            <input  type="radio" name="status"  value="1" checked>是</input>
		            <input  type="radio" style="margin-left:10px;" name="status" value="0">否</input>
		     	</div>
  			</div>
	      </form>
    </div> 
     
	</body>
</html>