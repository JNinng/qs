package top.ninng.qs.consumer.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author OhmLaw
 * @Date 2022/9/19 20:50
 * @Version 1.0
 */
@SpringBootTest
@Component
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void init01Test() {
        redisTemplate.opsForValue().set("键1", "值1");
        redisTemplate.opsForValue().set("键2", "值2");
    }
}
