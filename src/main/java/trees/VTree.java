package trees;

/**
 * @author zhzz
 * @Data create in 19:23 2019/1/10
 */
public interface VTree {
    VPath insert(VTreeNode node);

    VPath delect(VTreeNode node);

    VPath search(VTreeNode node);
}
