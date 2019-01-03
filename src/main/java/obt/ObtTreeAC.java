package obt;

/**
 * @author zhzz
 * @Data create in 15:01 2019/1/23
 */
public class ObtTreeAC extends ObtTree {
    @Override


    public Bnode createNode() {
        Bnode node = new ObtNodeAC(this);
        node.setLeaf(true);
        return node;
    }

    public void finInsert(CmpItem item){
        //acc更新需要对话


    }

    public void finDelete(CmpItem item){

    }
}
