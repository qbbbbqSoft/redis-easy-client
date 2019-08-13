package top.bbqbb.easy.starter.redis.model;

import lombok.Data;

import java.util.List;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-12 下午7:37
 */
@Data
public class TreeNode {
    private String text;
    private List<TreeNode> nodes;
    private String parentValue;
    private Integer cLevel;
    private String href;
    private String[] tags = new String[]{"@"};
}
