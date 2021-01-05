package com.lizza;

import redis.clients.jedis.Jedis;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-05
 */
public class JedisTest {

    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("root");
        jedis.select(0);
        jedis.set("name", "lizza");
        System.out.println(jedis.get("name"));
    }
}
