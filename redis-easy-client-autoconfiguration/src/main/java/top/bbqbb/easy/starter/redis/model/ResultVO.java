package top.bbqbb.easy.starter.redis.model;

import lombok.Data;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-09 上午8:26
 */
@Data
public class ResultVO {
    private String key;
    private String res;
    /**
     * 过期时间
     */
    private Long ttl;

    public ResultVO(String key, String res, Long ttl) {
        this.key = key;
        this.res = res;
        this.ttl = ttl;
    }
}
