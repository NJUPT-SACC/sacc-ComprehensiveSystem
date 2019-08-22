package com.sacc.comprehensivesystem.admin.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;

public class CacheUtils {
    static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    //@Autowired
    private static CacheManager cacheManager = SpringContextUtil.getBean(EhCacheCacheManager.class);

    //private static FrontendUserRedisTemplateService  frontendUserRedisTemplateService = SpringContextUtil.getBean(FrontendUserRedisTemplateService.class);

    public static CacheUtils cacheUtils;

    private static final String SYS_CACHE = "user_info";

//    @PostConstruct
//    public void init(){
//        cacheUtils = this;
//        cacheUtils.cacheManager = this.cacheManager;
//    }

    /**
     * 获取SYS_CACHE缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 获取用户缓存
     * @param key
     * @return
     */
    public static Object getUserCache(String key){
        logger.info("从ehCache获取缓存中...........");
        Object ehCacheObj = get(SYS_CACHE, key);
        if (ehCacheObj != null){
            return ehCacheObj;
        }
        else {
            logger.info("ehCache失效，从redis获取缓存中..........啊呀呀，暂时没有redis缓存");
            /*Object redisObj = frontendUserRedisTemplateService.getValue(RedisKey.CALO_FRONTEND_AUTHKEY + key);
            if (redisObj != null){
                put(SYS_CACHE, key, redisObj);
            }
            else {
                logger.info("redis缓存失效");
            }
            return redisObj;*/
            return null;
        }
    }

    /**
     * 获取SYS_CACHE缓存
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String key, Object defaultValue) {
        Object value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入SYS_CACHE缓存
     * @param key
     * @return
     */
    public static void putUserCache(String key, Object value) {
        put(SYS_CACHE, key, value);
    }


    /**
     * 写入redis用户缓存，并设置无效时间，单位为秒
     * @param key
     * @param value
     */
    public static void putUserCache(String key, Object value, long time) {

    }


    /**
     * 从SYS_CACHE缓存中移除
     * @param key
     * @return
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    public static void removeUserCache(String key) {
        remove(SYS_CACHE, key);
    }


    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        //return getCache(cacheName).get(getKey(key));
        return getCache(cacheName).get(key, Object.class);
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String cacheName, String key, Object defaultValue) {
        Object value = get(cacheName, getKey(key));
        return value != null ? value : defaultValue;
    }

    /**
     * 写入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        logger.debug("放入ehCache，cacheName:{}，key:{}，valie:{}",cacheName, key, value );
        getCache(cacheName).put(getKey(key), value);
    }

    /**
     * 从缓存中移除
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        //getCache(cacheName).remove(getKey(key));
        getCache(cacheName).evict(key);
    }

    /**
     * 从缓存中移除所有
     * @param cacheName
     */
    /*
    public static void removeAll(String cacheName) {
        Cache<String, Object> cache = getCache(cacheName);
        Set<String> keys = cache.keys();
        for (Iterator<String> it = keys.iterator(); it.hasNext();){
            cache.remove(it.next());
        }
        logger.info("清理缓存： {} => {}", cacheName, keys);
    }
    */

    /**
     * 获取缓存键名，多数据源下增加数据源名称前缀
     * @param key
     * @return
     */
    private static String getKey(String key){
//		String dsName = DataSourceHolder.getDataSourceName();
//		if (StringUtils.isNotBlank(dsName)){
//			return dsName + "_" + key;
//		}
        return key;
    }

    /**
     * 获得一个Cache，没有则显示日志。
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName){
        logger.debug("cacheName:{}", cacheName);
        Cache cache = cacheManager.getCache(cacheName);
        logger.debug("cache:{}", cache);
        if (cache == null){
            throw new RuntimeException("当前系统中没有定义“"+cacheName+"”这个缓存。");
        }
        return cache;
    }
}
