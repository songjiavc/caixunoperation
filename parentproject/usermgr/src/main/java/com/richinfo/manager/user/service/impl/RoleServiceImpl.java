package com.richinfo.manager.user.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.user.bean.RoleBean;
import com.richinfo.manager.user.dao.RoleMapper;
import com.richinfo.manager.user.dao.RoleRelaAuthMapper;
import com.richinfo.manager.user.model.Role;
import com.richinfo.manager.user.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	RoleRelaAuthMapper roleRelaAuthMapper;
	
	@Override
	public RoleBean getRoleById(String id) {
		Role role = roleMapper.getRoleById(id);
		RoleBean roleBean = new RoleBean();
		roleBean.setId(role.getId());
		roleBean.setRoleCode(role.getRoleCode());
		roleBean.setRoleName(role.getRoleName());
		return roleBean;
	}

	@Override
	public List<Role> getRoleListByWhere(RoleBean roleBean){
		Role role = new Role();
		role.setRoleCode(roleBean.getRoleCode());
		role.setRoleName(roleBean.getRoleName());
		return roleMapper.getRoleListByWhere(role);
	}

	@SuppressWarnings("finally")
	@Override
	public ResultBean insertOrUpdateRole(RoleBean roleBean) {
		//判断是删除还是修改
		ResultBean resultBean = new ResultBean();
		Role role = new Role();
		try{
			String id = roleBean.getId();
			if(StringUtils.isEmpty(id)){
				//id为空位添加
				//初始化创建人和创建按时间
				id = UUID.randomUUID().toString();
				role.setId(id);
				role.setRoleCode(roleBean.getRoleCode());
				role.setRoleName(roleBean.getRoleName());
				role.setCreater(roleBean.getCreater());
				role.setCreateTime(new Timestamp(System.currentTimeMillis()));
				role.setStatus('1');
				roleMapper.insertRole(role);
				resultBean.setStatus("success");
				resultBean.setMessage("角色添加成功！");
			}else{
				//id不为空位修改
				role.setId(id);
				role.setRoleCode(roleBean.getRoleCode());
				role.setRoleName(roleBean.getRoleName());
				role.setUpdater(roleBean.getUpdater());
				role.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				role.setStatus('1');
				//如果是开启
				roleMapper.updateRole(role);
				resultBean.setStatus("success");
				resultBean.setMessage("角色修改成功！");
			}
		}catch(Exception ex){
			resultBean.setStatus("failure");
			resultBean.setMessage("角色保存失败！");
			logger.error(resultBean.getMessage() + ex.getMessage());
		}finally{
			return resultBean;
		}
	}

	@Override
	public ResultBean deleteRoleByIds(String ids) {
		//删除角色则将角色关联的权限内容一并删除
		ResultBean resultBean = new ResultBean();
		
		String[] idsArr = ids.split(",");
		List<String> idList = Arrays.asList(idsArr);
		//先删除对应关系
		roleRelaAuthMapper.batchRemoveByRoleIds(idList);
		//删除实体内容
		roleMapper.batchRemoveByIds(idList);
		resultBean.setStatus("success");
		resultBean.setMessage("删除成功！");
		return resultBean;
	}

	@Override
	public Role getRoleRelaAuthByRoleId(String id) {
		return roleMapper.getRoleRelaAuthByRoleId(id);
	}

	
}
