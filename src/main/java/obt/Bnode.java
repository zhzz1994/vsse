package obt;

/**
 * @author zhzz
 * @Data create in 15:47 2019/1/21
 */
public interface Bnode {
    Bnode find(CmpItem item);

    void insert(CmpItem item);

    void delete(CmpItem item);

    void setLeaf(boolean leaf);

    boolean contains(CmpItem item);
}
