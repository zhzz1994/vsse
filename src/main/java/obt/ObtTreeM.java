package obt;

/**
 * @author zhzz
 * @Date create in 21:33 2019/1/21
 */
public class ObtTreeM extends ObtTree {
    @Override
    public ObtNode createNode() {
        ObtNode node = new ObtNodeM(this);
        node.setLeaf(true);
        return node;

    }
}
