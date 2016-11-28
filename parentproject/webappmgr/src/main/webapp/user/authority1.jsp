<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
   	<script type="text/javascript">
   		//设置parentAuthid值
   		var authParentId = $('#authParentId').val();
   		$('#auth-table').datagrid({
	   	    local : 'remote',
   			dataUrl : 'authority/getChildrenAuthorityList.action',
   			editUrl : 'authority/saveOrUpdateAuthority.action',        //编辑url
   			editCallback : saveCallBack,  //保存返回回掉函数
	   	 	showToolbar : true,
	   	 	columnShowhide : false,
	   	 	columnMenu : false,
	   	 	columnResize : false,
	   	 	filterThead : false,
	   	 	toolbarItem : 'add,del,edit',
	   	 	editMode : 'dialog',
	   	 	editDialogOp : {
	   	 		width:500, 
	   	 		height:300, 
	   	 		mask:false
	   	 	},
	   	 	paging : {
	   	 		pageSize:30, 
	   	 		selectPageSize:'10,20,30',
	   	 		pageCurrent:1, 
	   	 		showPagenum:5, 
	   	 		total:0
	   	 	},
	   	 	pagingAlign : 'right',
 	   	    data : {
	   	    	authParentId : authParentId
	   	    },
	   	    columns: [
	   	    	{name:'id',hide:true,add:false,edit:false},
	   	    	{name:'code',label:'编号',align:'center',width:150},
	   	    	{name:'authName',label:'权限名称',align:'center',width:150},
	   	    	{name:'parentAuthId',add:false,edit:false,hide:true,align:'center',width:150},
	   	    	{name:'menuUrl',label:'链接地址',align:'center',width:150}
	   	    ]
	   	});
   		
   		
   		function saveCallBack(json){
   			alert(json);
   		} 
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
			    	<table id="auth-table" class="table table-bordered" data-toggle="datagrid" >
					</table>
			    </div>
  			</div>
    	</div>
	</body>
</html>