package top.bbqbb.easy.starter.redis.annotation;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-09 下午3:43
 */
public class RedisClientConfiguration extends WebMvcConfigurationSupport {
    private static final String[] RL= new String[]{"classpath:/META-INF/", "classpath:/resources/", "classpath:/static/","classpath:META-INF/resources/webjars/redis-easy-client-webjar-ui/"};
    private static final String WEBJARSRL = "classpath:/META-INF/resources/webjars/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置映射关系
        registry.addResourceHandler("/**").addResourceLocations(RL);
        registry.addResourceHandler("/webjars/**").addResourceLocations(WEBJARSRL);
        super.addResourceHandlers(registry);
    }

}
