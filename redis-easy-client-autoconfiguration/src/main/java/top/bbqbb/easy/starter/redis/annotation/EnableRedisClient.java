package top.bbqbb.easy.starter.redis.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RedisClientConfiguration.class})
public @interface EnableRedisClient {
}
