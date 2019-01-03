package obt;

/**
 * @author zhzz
 * @Data create in 16:06 2019/1/21
 */
public class ObtNodeRaw extends ObtNode {

    ObtNodeRaw(ObtTree tree) {
        super(tree);
    }

    @Override
    public ObtNode createNode() {
        return new ObtNodeRaw(tree);
    }

    @Override
    public void update() {
        //无需更新
    }
}
