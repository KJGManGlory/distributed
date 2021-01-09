package com.lizza;

import com.lizza.util.RedisHolder;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-05
 */
public class JedisTest {

    public static void main(String[] args) throws IOException {
        Jedis jedis = RedisHolder.INSTANCE;
        System.out.println(jedis.ping());
        jedis.close();
    }
}
