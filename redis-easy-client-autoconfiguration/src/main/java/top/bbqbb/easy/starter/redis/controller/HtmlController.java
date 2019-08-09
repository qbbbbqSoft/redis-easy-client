package top.bbqbb.easy.starter.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bbqbb.easy.starter.redis.model.ResultVO;
import top.bbqbb.easy.starter.redis.result.RedisTR;
import top.bbqbb.easy.starter.redis.service.impl.RedisServiceTool;

import java.util.*;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 下午4:43
 */
@RestController
public class HtmlController {
    @Autowired
    RedisServiceTool tool;
    @RequestMapping("/redis-datas")
    public List<ResultVO> redisDatas() {
        List<ResultVO> datas = new ArrayList<>();
        RedisTR<Set<String>> tr = tool.keysListByPattern("*");
        ResultVO data;
        Set<String> keys = tr.getR();
        for (String key: keys) {
            data = new ResultVO();
            RedisTR<String> r = tool.get(key);
            String res = r.getR();
            data.setKey(key);
            data.setRes(res);
            datas.add(data);
        }
        return datas;
    }
}

