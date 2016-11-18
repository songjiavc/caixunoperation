package com.richinfo.manager.user.service;

import com.richinfo.manager.common.enums.BizExceptionEnum;

/** 
  * @ClassName: BizException 
  * @Description: 设定自定义异常类，抛出业务异常
  * @author songj@sdfcp.com
  * @date 2014年11月24日 下午3:01:31 
  *  
  */
public class BizException extends Exception {

    /** 
	  * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	  */ 
	private static final long serialVersionUID = -921794451272760780L;

	public BizException(int index)
    {  
        //绑定枚举业务异常类型
        super(BizExceptionEnum.getName(index));
    }

    public BizException(BizExceptionEnum echartNoData) {
        super(echartNoData.toString());
    }
}
