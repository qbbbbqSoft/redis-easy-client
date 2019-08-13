package top.bbqbb.easy.starter.redis.utils;

import top.bbqbb.easy.starter.redis.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-12 下午7:39
 */
public class TreeUtils {

    public static List<TreeNode> build(List<TreeNode> list) {
        List<TreeNode> trees = new ArrayList<>();
        for (TreeNode var:list) {
            Boolean f = true;
            if (var.getCLevel() == 0) {
                for(TreeNode it: trees) {
                    if (it.getHref().equals(var.getHref())) {
                        f = false;
                    }
                }
                if (f) {
                    trees.add(var);
                }
            }
            for (TreeNode it : list) {
                if (it.getParentValue().equals(var.getHref())) {
                    Boolean flag = true;
                    List<TreeNode> children = var.getNodes();
                    if (children == null) {
                        var.setNodes(new ArrayList<TreeNode>());
                    } else {
                        for (TreeNode c:children) {
                            if (c.getHref().contains(it.getHref())) {
                                flag = false;
                            }
                        }
                    }
                    if (flag) {
                        var.getNodes().add(it);
                        var.setTags(new String[]{String.valueOf(var.getNodes().size())});
                    }

                }
            }
        }
        return trees;
    }
}
