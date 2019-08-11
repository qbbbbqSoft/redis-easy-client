package top.bbqbb.easy.starter.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bbqbb.easy.starter.redis.model.Res;
import top.bbqbb.easy.starter.redis.model.ResultVO;
import top.bbqbb.easy.starter.redis.param.RedisAjaxParam;
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
    public Res redisDatas(@RequestBody RedisAjaxParam param) {
        List<ResultVO> datas = new ArrayList<>();
        RedisTR<Set<String>> tr = tool.keysListByPattern(param.getKey());
        ResultVO data;
        Set<String> keys = tr.getR();
        Integer start = param.getStart();
        Integer end = param.getEnd();
        ArrayList<String> keyList = new ArrayList<>(keys);
        if (keyList.size() < (end - start)) {
            end = start + keyList.size();
        }
        for (int i = start; i < end; i++) {
            String key = keyList.get(i);
            RedisTR<String> r = tool.get(key);
            RedisTR<Long> ttlR = tool.ttl(key);
            String res = r.getR();
            Long ttl = ttlR.getR();
            data = new ResultVO(key,res,ttl);
            datas.add(data);
        }
        return Res.ok().put("data",datas);
    }
}

