package top.bbqbb.easy.starter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.bbqbb.easy.starter.redis.annotation.EnableRedisClient;
import top.bbqbb.easy.starter.redis.annotation.RedisClientConfiguration;
import top.bbqbb.easy.starter.redis.controller.HtmlController;
import top.bbqbb.easy.starter.redis.service.impl.RedisServiceTool;

import static top.bbqbb.easy.starter.redis.RedisProperties.REDIS_EASY_CLIENT_PREFIX;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 上午10:55
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
//@ConditionalOnClass(OSSClient.class)
@ConditionalOnProperty(prefix = REDIS_EASY_CLIENT_PREFIX,name = {"host"})
public class RedisAutoConfiguration {
    @Autowired
    RedisProperties redisProperties;

    @Bean
    @ConditionalOnMissingBean
    RedisServiceTool redisServiceTool() {
        RedisServiceTool redisServiceTool = new RedisServiceTool(redisProperties.getHost(),redisProperties.getPort(),redisProperties.getPassword(),redisProperties.getDatabase());
        redisServiceTool.Init();
        return redisServiceTool;
    }

    @Bean
    @ConditionalOnMissingBean
    HtmlController htmlController() {
        return new HtmlController();
    }


}
