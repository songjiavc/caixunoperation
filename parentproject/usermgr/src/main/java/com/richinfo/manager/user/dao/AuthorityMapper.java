package com.richinfo.manager.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.richinfo.manager.user.bean.AuthorityBean;
import com.richinfo.manager.user.model.Authority;

public interface AuthorityMapper {
	
	/** 
	  * @Description: 根据父节点获取所有权限列表 
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午8:59:27 
	  * 
	  * @param authParentId
	  * @return 
	  */
	@Autowired
	List<Authority> getAuthListByParentId(String authParentId);
	
	/** 
	  * @Description: 根据id删除对应权限
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午8:59:43 
	  * 
	  * @param paramMap 
	  */
	@Autowired
	void deleteAuthorityById(Map<String,String> paramMap);
	
	/** 
	  * @Description: 插入权限内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午8:59:56 
	  * 
	  * @param authority 
	  */
	@Autowired
	void insertAuthority(Authority authority);
	
	/** 
	  * @Description: 更新权限内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:00:04 
	  * 
	  * @param authority 
	  */
	@Autowired
	void updateAuthority(Authority authority);
	
	/** 
	  * @Description: 判断code是否重复
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:00:14 
	  * 
	  * @param authority
	  * @return 
	  */
	@Autowired
	Integer checkValue(Authority authority);
	
	/** 
	  * @Description: 获取有效子权限个数
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 下午1:48:36 
	  * 
	  * @param authority
	  * @return 
	  */
	@Autowired
	Integer checkChildNum(Authority authority);
	/** 
	  * @Description: 获取所有有效权限列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:00:47 
	  * 
	  * @return 
	  */
	@Autowired
	List<Authority> getIsStatusAuthList();
	
	/** 
	  * @Description: 根据id获取权限对应实体内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:34:25 
	  * 
	  * @param authId
	  * @return 
	  */
	@Autowired
	Authority getAuthorityByid(String authId);
	
}
