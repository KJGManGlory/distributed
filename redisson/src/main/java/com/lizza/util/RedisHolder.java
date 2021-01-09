package com.lizza.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-07
 */
public class RedisHolder {

    public static RedissonClient INSTANCE;
    static {
        Config config = new Config();
        config.useSingleServer().setAddress(
                PropUtil.getStringValue("redis.host")
                + ":" + PropUtil.getIntValue("redis.port"));
        config.useSingleServer().setPassword(PropUtil.getStringValue("redis.password"));
        config.useSingleServer().setDatabase(PropUtil.getIntValue("redis.database"));
        INSTANCE = Redisson.create(config);
    }

}
