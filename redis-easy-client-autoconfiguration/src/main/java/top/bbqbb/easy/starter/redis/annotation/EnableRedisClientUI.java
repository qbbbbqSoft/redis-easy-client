package top.bbqbb.easy.starter.redis.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * @author fqb
 * @program: redis-easy-client
 * 描述: 开启UI
 * @create 2019-08-07 下午4:43
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RedisClientConfiguration.class})
public @interface EnableRedisClientUI {
}
