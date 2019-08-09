package top.bbqbb.easy.starter.redis.result;

import lombok.Data;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 下午3:04
 */
@Data
public class RedisTR<T> {
    private String key;
    private T r;

    public RedisTR() {

    }

    public RedisTR(String key, T r) {
        this.key = key;
        this.r = r;
    }
}
