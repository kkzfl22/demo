package com.liujun.pattern.observe;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.log4j.Logger;

/**
 * 缓存监听公共父类操作
 * 
 * @author liujun
 * 
 * @date 2015年2月4日
 * @vsersion 0.0.1
 */
public class MemListen {

    /**
     * 日志
     */
    private Logger log = Logger.getLogger(MemListen.class);

    /**
     * 所有更新缓存操作的集合
     */
    private static final Map<String, IcacheNotiflyService> LISTEN_CACHE = new ConcurrentSkipListMap<String, IcacheNotiflyService>();

    /**
     * 添加缓存更新操作
     * 
     * @param key
     * @param cacheNotiflySercie
     */
    public void addListen(String key, IcacheNotiflyService cacheNotiflySercie) {
        LISTEN_CACHE.put(key, cacheNotiflySercie);
    }

    /**
     * 进行缓存更新通知
     * 
     * @param key
     *            缓存模块的key
     * @return true 当前缓存模块数据更新成功，false，当前缓存数据更新失败
     */
    public boolean notifly(String key) {
        boolean result = false;

        if (null != key && !"".equals(key)) {
            IcacheNotiflyService cacheService = LISTEN_CACHE.get(key);

            if (null != cacheService) {
                try {
                    result = cacheService.cacheNotifly();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("MemListen notifly key :" + key + " error:Exception info:", e);
                }
            }
        }

        return result;
    }

    /**
     * 进行通知所有缓存进行更新操作
     */
    public void notiflyAll() {

        Iterator<Entry<String, IcacheNotiflyService>> notiflyIter = LISTEN_CACHE.entrySet().iterator();

        Entry<String, IcacheNotiflyService> item = null;

        while (notiflyIter.hasNext()) {
            item = notiflyIter.next();

            // 进行缓存更新通知操作
            if (null != item.getValue()) {
                try {
                    item.getValue().cacheNotifly();
                } catch (Exception e) {
                    log.error("MemListen notiflyAll key :" + item.getKey() + ";value " + item.getValue()
                            + ";error:Exception info:", e);
                }
            }
        }
    }

}
