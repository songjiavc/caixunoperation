package com.richinfo.manager.user.service.cache;

/** 
  * @ClassName: GlobalCacheService 
  * @Description: 全局缓存内容,目前缓存map 
  * @author songj@sdfcp.com
  * @date 2014年12月9日 下午3:26:10 
  *  
  */
public interface GlobalCacheService{
    
    /** 
      * @Description: TODO(这里用一句话描述这个类的作用) 
      * @author songj@sdfcp.com
      * @date 2014年12月9日 下午3:19:11 
      * 
      * @return 
      */
    public String[] getCacheMap(String key);
    
    /**
     * @param key
     * @return
     */
    public String getCpzlMap(String key);
    
    /** 
      * @Description: 增加内存map
      * @author songj@sdfcp.com
      * @date 2014年12月9日 下午3:33:28 
      *  
      */
    public void setCacheMap(String key,String value[]);
   
    
    /**
     * @param key
     * @param value
     */
    public void setCpzlMap(String key,String value) ;
} 

