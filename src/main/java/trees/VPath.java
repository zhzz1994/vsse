package trees;

/**
 * @author zhzz
 * @Data create in 19:40 2019/1/10
 */
public interface VPath {
    boolean verify();
    VTreeNode getRoot();
    void setRoot();
}
