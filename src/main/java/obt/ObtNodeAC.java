package obt;

/**
 * @author zhzz
 * @Date create in 15:02 2019/1/23
 */
public class ObtNodeAC extends ObtNodeM {
    private AccEnc accEnc;

    ObtNodeAC(ObtTree tree) {
        super(tree);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public ObtNodeAC copy() {
        ObtNodeAC node = new ObtNodeAC(tree);
        node.mHash = getMHash();
        node.accEnc = accEnc;
        return node;
    }
}
