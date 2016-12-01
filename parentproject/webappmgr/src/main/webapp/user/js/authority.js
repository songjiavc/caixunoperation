var zNodes ;//放置树节点的全局变量
$(document).ready(
		function()
		{
			initDatagrid('');
			closeDialog();
			initZnodes();
		}
);


function initDatagrid(parentCode)
{
	var params = new Object();
	params.parentCode = parentCode;//父级id
	
	$('#datagrid').datagrid({
		singleSelect:false,
		queryParams: params,
		url:contextPath + '/authority/getAuthList.action',//'datagrid_data1.json',
		method:'get',
		border:false,
		fit:true,//datagrid自适应
		fitColumns:true,
//		pagination:true,
		pageSize:10,
//		striped:true,
		loadMsg:'全力加载中...',
		columns:[[
				{field:'ck',checkbox:true},
				{field:'id',hidden:true},
		        {field:'authName',width:120,title:'权限名称'},
				{field:'authCode',title:'权限编码',width:120,align:'center'},
				{field:'menuUrl',title:'权限url',width:120,align:'center'},
				{field:'authImg',title:'权限图片',width:80,align:'center'},
				{field:'status',title:'是否启用',width:80,align:'center',
					formatter:function(value,row,index){
							var showStatus = "";
							if("1" == row.status)
								{
									showStatus = "是";
								}
							else
								{
									showStatus = "否";
								}
							return showStatus;
						}
					},
				{field:'opt',title:'操作',width:160,align:'center',  
		            formatter:function(value,row,index){  
		                var btn = '<a class="editcls" onclick="updateAuth(&quot;'+row.id+'&quot;,&quot;'+row.isSystem+'&quot;)" href="javascript:void(0)">编辑</a>'
		                	+'<a class="auth" onclick="deleteAuth(&quot;'+row.id+'&quot;)" href="javascript:void(0)">删除</a>';
		                return btn;  
		            }  
		        }  
		    ]],  
	    onLoadSuccess:function(data){ 
	        $('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
	        $('.auth').linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});  
	        
	    	if(data.rows.length==0){
				var body = $(this).data().datagrid.dc.body2;
				body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="8">没有数据</td></tr>');
				}
	        
	    }  
	});
}


//关闭弹框
function closeDialog()
{
	$("#addAuth").dialog('close');//初始化添加权限弹框关闭
	$("#updateAuth").dialog('close');//初始化修改权限弹框关闭
}

/**
 * 权限修改
 */
function updateAuth(code)
{
	var url = contextPath + '/authority/getDetailAuth.action';
	var data1 = new Object();
	data1.code=code;//权限的id
	$.ajax({
		async: false,   //设置为同步获取数据形式
        type: "get",
        url: url,
        data:data1,
        dataType: "json",
        success: function (data) {
			$('#ffupdate').form('load',{
				id:data.id,
				code:data.authCode,
				authName:data.authName,
				url:data.menuUrl,
				status:data.status,
				authImg:data.authImg
			});
			$('#idU').val(data.id);
			$('#parentAuthNameU').val(selectAuthTreeNode.name);
	   		$('#parentAuthIdU').val(selectAuthTreeNode.id);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            window.parent.location.href = contextPath + "/menu/error.action";
        }
	});
	$("#updateAuth").dialog("open");//打开修改权限弹框
}

/**
 * 批量删除权限数据
 * @param code
 */
function deleteAuthList()
{
	var url = contextPath + '/authority/deleteAuth.action';
	var data1 = new Object();
	
	var codearr = new Array();
	var rows = $('#datagrid').datagrid('getSelections');
	
	var deleteFlag = true;
	
	for(var i=0; i<rows.length; i++)
	{
		codearr.push(rows[i].id);//code
		//判断当前权限是否有子级权限
		var isCheck = checkHaveChildAuth(rows[i].id);//判断当前待删除权限是否拥有子级权限，若拥有子级权限则不可以删除
		if(!isCheck){
			break;
		}
		//undo 判断待删除权限是否存在有效的角色关联，否则不允许删除
		//var connectRole = rows[i].connectRole;//当前数据是否和有效的角色数据有关联
			//undo 跟角色的关联暂时不做
			/*if("1"==connectRole){
				$.messager.alert('提示', "当前待删除权限:'"+rows[i].authName+"'和'"+rows[i].bindRoles+"'的角色数据有关联,不可以进行删除操作!");
				deleteFlag = false;
				break;
			}*/
	}
	
	if(isCheck)//选中的待删除权限中没有拥有子级权限的权限时可以进行删除操作
		{
			if(codearr.length>0)
			{
				data1.codes=codearr.toString();//将id数组转换为String传递到后台
				
				$.messager.confirm("提示", "您确认删除选中数据？", function (r) {  
			        if (r) {  
			        	
				        	$.ajax({
				        		async: false,   //设置为同步获取数据形式
				                type: "post",
				                url: url,
				                data:data1,
				                dataType: "json",
				                success: function (data) {
				                	initDatagrid(selectAuthTreeNode.id);
				                	//批量删除权限后重新加载树
				                	initZnodes();
				                	$.fn.zTree.getZTreeObj("authorityTree").selectNode(selectAuthTreeNode);
				                	$.messager.alert('提示', data.message);
				                	
				                },
				                error: function (XMLHttpRequest, textStatus, errorThrown) {
				                    window.parent.location.href = contextPath + "/menu/error.action";
				                }
				           });
				        	
			        }  
			    });  
				
			}
			else
			{
				$.messager.alert('提示', "请选择数据后操作!");
			}
		}
	
}

/**
 * 删除权限
 */
function deleteAuth(id)
{
	var url = contextPath + '/authority/deleteAuth.action';
	var data1 = new Object();
	var deleteFlag = true;//是否可以进行删除标志位
	var isHave = checkHaveChildAuth(id);//判断当前待删除权限是否拥有子级权限，若拥有子级权限则不可以删除
	/*
	if("1"==connectRole)//当connectRole=1时则表示当前权限和有效的角色数据有关联
	{
		$.messager.alert('提示', "当前权限和有效的角色数据有关联,不可以进行删除操作!");
		deleteFlag = false;
	}
	*/
	debugger;
	if(!isHave){
		var ids = [];
		ids.push(id);
		$.messager.confirm("提示", "您确认删除选中数据？", function (r) {  
	        if (r) {  
		        	$.ajax({
		        		async: false,   //设置为同步获取数据形式
		                type: "post",
		                url: url,
		                data:{
		                	ids : ids.toString()
		                },
		                dataType: "json",
		                success: function (data) {
		                	initDatagrid(selectAuthTreeNode.id);
		                	//删除权限后重新加载树
		                	initZnodes();
		                	$.fn.zTree.getZTreeObj("authorityTree").selectNode(selectAuthTreeNode);
		                	$.messager.alert('提示', data.message);
		                },
		                error: function (XMLHttpRequest, textStatus, errorThrown) {
		                    window.parent.location.href = contextPath + "/menu/error.action";
		                }
		           });
		        	
	        }  
	    }); 
	}
}


//提交添加权限form表单
function submitAddauth()
{
	$('#ff').form('submit',{
		url:contextPath+'/authority/saveOrUpdate.action',
		onSubmit:function(param){
			return $('#ff').form('enableValidation').form('validate');
		},
	    success:function(data){
	    	//提交表单后，从后台返回的data类型为String，要获取信息需要将其转换为json类型，使用eval("(" + data + ")")方法转换
	    	$.messager.alert('提示', eval("(" + data + ")").message);
	    	$("#addAuth").dialog('close');//初始化修改权限弹框关闭
	    	//在添加权限后刷新权限数据列表
	    	initDatagrid(selectAuthTreeNode.id);
	    	initZnodes();
	    	//在添加权限后点击父节点
	    	$.fn.zTree.getZTreeObj("authorityTree").selectNode(selectAuthTreeNode);
	    	$('#ff').form('clear');
	    }
	});
}

//提交修改权限form表单
function submitUpdateauth()
{
	$('#ffupdate').form('submit',{
		url:contextPath+'/authority/saveOrUpdate.action',
		onSubmit:function(param){
			return $('#ffupdate').form('enableValidation').form('validate');
		},
	    success:function(data){
	    	//data从后台返回后的类型为String，要获取信息需要将其转换为json类型，使用eval("(" + data + ")")方法转换
	    	$.messager.alert('提示', eval("(" + data + ")").message);
	    	$("#updateAuth").dialog('close');//初始化修改权限弹框关闭
	    	initDatagrid(selectAuthTreeNode.id);
	    	initZnodes();
	    	//在添加权限后点击父节点
	    	$.fn.zTree.getZTreeObj("authorityTree").selectNode(selectAuthTreeNode);
	    	$('#ffupdate').form('clear');
	    }
	});
}

/**
 * 校验code,authname唯一性
 */
function checkCode(id,code,authname)
{
	var flag = false;//当前值可用，不存在
	var data = new Object();
	
	data.id = id;
	data.code = code;
	data.authname = authname;
	
	$.ajax({
		async: false,   //设置为同步获取数据形式
        type: "post",
        url: contextPath+'/authority/checkValue.action',
        data:data,
        dataType: "json",
        success: function (data) {
        	if(data.exist)//若data.isExist==true,则当前校验值已存在，则不可用使用
        		{
        			flag = true;
        		}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
//            window.parwent.location.href = contextPath + "/menu/error.action";
        }
   });
	
	return flag;
}

/**
 * 判断是否有子级权限
 * @param id
 */
function checkHaveChildAuth(id)
{
	var flag = false;//当前值可用，不存在
	$.ajax({
		async: false,   //设置为同步获取数据形式
        type: "post",
        url: contextPath+'/authority/checkChildNum.action',
        data:{
        	parentAuthId : id
        },
        dataType: "json",
        success: function (data) {
        	if(data.entity > 0){
        		$.messager.alert('提示', "当前待删除权限:'拥有"+data.entity+"'个子级权限,不可以进行删除操作!");
        		return true;
        	}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            window.parent.location.href = contextPath + "/menu/error.action";
        }
	});
	return flag;
}


/**
 * 自定义校验authname
 */
$.extend($.fn.validatebox.defaults.rules, {
    checkAname: {//自定义校验authname
        validator: function(value,param){
        	var rules = $.fn.validatebox.defaults.rules;  
        	if(value.length==0||value.length>10){  
        		rules.checkAname.message = "当前权限名称不可为空且长度不可以超过10个字符";  
                return false;  
            }
        	else
    		{
        		rules.checkAname.message = "当前权限名称已存在"; 
                return !checkCode($("#"+param[1]).val(),'',value);
    		}
        	
        }
    }
});

/**
 * 自定义校验code
 */
$.extend($.fn.validatebox.defaults.rules, {
    checkCodes: {//自定义校验code
        validator: function(value,param){
        	var rules = $.fn.validatebox.defaults.rules;  
        	if(value.length==0||value.length>10){  
        		rules.checkCodes.message = "当前权限编码不可为空且长度不可以超过10个字符";  
                return false;  
            }
        	else
    		{
        		rules.checkCodes.message = "当前权限编码已存在";
                return !checkCode($("#"+param[1]).val(),value,'');
    		}
        }
    }
});




/***********树配置************/

var setting ;
/**
 * 初始化树节点数据
 */
function initZnodes()
{
	$.ajax({
		async: false,   //设置为同步获取数据形式
        type: "post",
        url: contextPath+'/authority/getTreedata.action',
        dataType: "json",
        success: function (data) {
        	setting = {
        			view: {
        				showLine: false
        			},
        			data: {
        				simpleData: {
        					enable: true
        				}
        			},
        			callback: {
        				onClick: zTreeOnClick
        			}
        		};
        		zNodes = data;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
//            window.parent.location.href = contextPath + "/menu/error.action";
        }
   });
	
	$.fn.zTree.init($("#authorityTree"), setting, zNodes);
}

/**
 * 树节点点击事件
 * @param event
 * @param treeId
 * @param treeNode
 */
function zTreeOnClick(event, treeId, treeNode) 
{
	selectAuthTreeNode = treeNode;
	initDatagrid(treeNode.id);
}
 

