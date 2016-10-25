package com.liujun.pattern.observe;

/**
 * 缓存通过接口
 * @author liujun
 *
 * @date 2015年2月4日
 * @vsersion 0.0.1
 */
public interface IcacheNotiflyService {

    /**
     * 进行缓存通知接口
     * @throws Exception 异常操作
     * @return true 缓存更新成功, false缓存更新失败
     */
    public boolean cacheNotifly() throws Exception;

}
