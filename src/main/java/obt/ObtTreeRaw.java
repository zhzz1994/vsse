package obt;

/**
 * @author zhzz
 * @Data create in 15:54 2019/1/21
 */
public class ObtTreeRaw extends ObtTree {
    @Override
    public ObtNode createNode() {
        ObtNode node = new ObtNodeRaw(this);
        node.setLeaf(true);
        return node;
    }

}
