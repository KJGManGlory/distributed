package com.lizza.lock.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    /** 用来绑定当前线程和锁 **/
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /** 用来记录当前线程的重入次数 **/
    private ThreadLocal<Integer> threadCount = new ThreadLocal<>();

    public boolean lock(String key) {
        Boolean isLock = false;

        // 通过判断当前线程是否绑定了 uuid 来实现可重入锁
        if (threadLocal.get() == null) {
            String uuid = UUID.randomUUID().toString();

            // 将锁与线程进行绑定
            threadLocal.set(uuid);

            // 判断是否获取到锁, 若获取失败, 则进行自旋等待
            while (!isLock) {
                isLock = redisTemplate.opsForValue().setIfAbsent(key, uuid, 3, TimeUnit.MINUTES);
            }
        } else {
            isLock = false;
        }

        // 如果加锁成功, 则对 threadCount 进行 +1 操作
        if (isLock) {
            Integer count = threadCount.get() == null ? 0 : threadCount.get();
            threadCount.set(count++);
        }

        return isLock;
    }

    public void unlock(String key) {

        // 如果是同一个线程加的锁, 则可以释放
        if (threadLocal.get().equals(redisTemplate.opsForValue().get(key))) {

            // 判断当前线程加锁的次数是否为 0, 为 0 方可释放锁
            Integer count = threadCount.get();
            if (count == null || --count <= 0) {
                redisTemplate.delete(key);
                threadLocal.remove();
                threadCount.remove();
            }
        }
    }
}
