package obt;

import trees.VPath;
import trees.VTreeNode;

/**
 * @author zhzz
 * @Data create in 15:40 2019/1/11
 */
public class NodePath implements VPath {
    //不用验证e（g，gx）的Path，单项搜索

    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public VTreeNode getRoot() {
        return null;
    }

    @Override
    public void setRoot() {

    }
}
