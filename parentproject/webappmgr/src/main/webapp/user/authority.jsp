<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
   	<script type="text/javascript">
   		//设置parentAuthid值
   		var authParentId = $('#authParentId').val();
   		
   		$('#auth-table').datagrid({
	   	    dataUrl: 'authority/getChildrenAuthorityList.action',
	   	 	showToolbar : true,
	   	 	toolbarItem : [add,edit,del],
	   	    data : {
	   	    	authParentId : authParentId
	   	    },
	   	    columns: [
	   	    	{name:'id',label:'id',align:'center',width:70},
	   	    	{name:'code',label:'编号',align:'center',width:70},
	   	    	{name:'authName',label:'权限名称',align:'center',width:70},
	   	    	{name:'parentAuthId',label:'上级id',align:'center',width:70},
	   	    	{name:'menuUrl',label:'链接地址',align:'center',width:70}
	   	    ]
	   	});
   	</script>
	</head>
	<body>
		 <input type = "hidden" name="authParentId" value="value"></intput>
    	 <div class="container-fluid">
    	 	<div class="row-fluid">
    			<div class="col-md-2">
      				<div id="auth-ztree" class="ztree"></div>
   			 	</div>
			    <div class="col-md-10">
			    	<table id="auth-table" class="table table-bordered" fieldSortable="false" filterThead="false" data-toggle="datagrid" >
					</table>
			    </div>
  			</div>
    	</div>
	</body>
</html>