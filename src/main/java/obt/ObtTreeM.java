package obt;

/**
 * @author zhzz
 * @Date create in 21:33 2019/1/21
 */
public class ObtTreeM extends ObtTree {
    @Override
    public Bnode createNode() {
        Bnode node = new ObtNodeM(this);
        node.setLeaf(true);
        return node;
    }
}
