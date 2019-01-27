package obt;

import java.util.List;

/**
 * @author zhzz
 * @Data create in 15:01 2019/1/23
 */
public class ObtTreeAC extends ObtTree {


    @Override
    public ObtNode createNode() {
        ObtNode node = new ObtNodeAC(this);
        node.setLeaf(true);
        return node;
    }

    public void finalInsert(CmpItem item){
        //acc更新需要对话


    }

    public void finalDelete(CmpItem item){

    }
}
