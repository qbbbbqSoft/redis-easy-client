package top.bbqbb.easy.starter.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bbqbb.easy.starter.redis.model.Res;
import top.bbqbb.easy.starter.redis.model.ResultVO;
import top.bbqbb.easy.starter.redis.model.TreeNode;
import top.bbqbb.easy.starter.redis.param.RedisAjaxParam;
import top.bbqbb.easy.starter.redis.param.RedisParam;
import top.bbqbb.easy.starter.redis.result.RedisTR;
import top.bbqbb.easy.starter.redis.service.impl.RedisServiceTool;
import top.bbqbb.easy.starter.redis.utils.TreeUtils;

import java.util.*;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 下午4:43
 */
@RestController
public class UIDataController {
    @Autowired
    RedisServiceTool tool;
    @RequestMapping("/redis-datas")
    public Res redisDatas(@RequestBody RedisAjaxParam param) {
        RedisTR<Set<String>> tr = tool.keysListByPattern(param.getKey());
        Set<String> keys = tr.getR();
        List<TreeNode> res = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>(keys);
        for (int i = 0; i < list.size(); i++) {
            List<String> l1 = Arrays.asList(list.get(i).split(":"));
            for (int j = 0; j < l1.size(); j++) {
                TreeNode type= new TreeNode();
                type.setText(l1.get(j));
                type.setCLevel(j);
                String parentValue = "";
                String value = "";
                for (int k = 0; k < j; k++) {
                    parentValue = parentValue + ":" + l1.get(k);
                }
                for (int k = 0; k <= j ; k++) {
                    value = value + ":" + l1.get(k);
                }
                type.setHref(value.substring(value.length() > 0 ? 1 : value.length()));
                type.setParentValue(parentValue.substring(parentValue.length() > 0 ? 1 : parentValue.length()));
                res.add(type);
            }
        }
        List<TreeNode> build = TreeUtils.build(res);
        return Res.ok().put("data",build).put("info","共取出" + list.size() + "条数据");
    }

    @RequestMapping("/add-data")
    public Res add(@RequestBody RedisAjaxParam param) {
        RedisTR<Boolean> tr = tool.set(new RedisParam(param.getKey(), param.getValue(), param.getTimeout(), param.getTimeUnit()));
        if (tr != null && tr.getR()) {
            return Res.ok("添加成功");
        }
        return Res.error("添加失败");
    }

    @RequestMapping("/del-data-by-key")
    public Res del(@RequestBody RedisAjaxParam param) {
        RedisTR<Boolean> tr = tool.del(param.getKey());
        if (tr != null && tr.getR()) {
            return Res.ok("删除成功");
        }
        return Res.error("添加失败");
    }

    @RequestMapping("/get-data-by-key")
    public Res get(@RequestBody RedisAjaxParam param) {
        RedisTR<String> stringRedisTR = tool.get(param.getKey());
        RedisTR<Long> ttl = tool.ttl(param.getKey());
        if (stringRedisTR != null && ttl != null) {
            ResultVO vo = new ResultVO(param.getKey(),stringRedisTR.getR(),ttl.getR());
            return Res.ok().put("data",vo);
        }
        return Res.error("获取数据失败");
    }
}

