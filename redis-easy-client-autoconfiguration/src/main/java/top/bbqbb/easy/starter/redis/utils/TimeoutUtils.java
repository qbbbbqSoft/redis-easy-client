package top.bbqbb.easy.starter.redis.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-12 上午9:22
 */
public abstract class TimeoutUtils {
    public TimeoutUtils() {
    }

    public static Long toSeconds(long timeout, TimeUnit unit) {
        return roundUpIfNecessary(timeout, unit.toSeconds(timeout));
    }

    public static Long toMillis(long timeout, TimeUnit unit) {
        return roundUpIfNecessary(timeout, unit.toMillis(timeout));
    }

    private static long roundUpIfNecessary(long timeout, long convertedTimeout) {
        return timeout > 0L && convertedTimeout == 0L ? 1L : convertedTimeout;
    }
}
