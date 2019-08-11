package top.bbqbb.easy.starter.redis.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Res extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Res() {
        put("code", 0);
        put("msg", "success");
    }

    public static Res error() {
        return error(500, "未知异常，管理员会及时处理");
    }

    public static Res error(String msg) {
        return error(500, msg);
    }

    public static Res error(int code, String msg) {
        Res r = new Res();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Res ok(String msg) {
        Res r = new Res();
        r.put("msg", msg);
        return r;
    }

    public static Res ok(Map<String, Object> map) {
        Res r = new Res();
        r.putAll(map);
        return r;
    }

    public static Res ok() {
        return new Res();
    }

    @Override
    public Res put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
