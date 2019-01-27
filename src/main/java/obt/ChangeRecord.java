package obt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhzz
 * @Data create in 15:51 2019/1/24
 */
public class ChangeRecord {
    //Obt树修改记录
    List<ChangeStep> steps;
    NodePair before;  //临时存储，缓存

    private ChangeRecord(){
        steps = new ArrayList<>();
    }

    public static ChangeRecord createRecord(){
        return new ChangeRecord();
    }

    public void updateRecords(){
        for(ChangeStep step : steps){
            step.after.updateNP();
        }
    }

    public void setBefore(NodePair b){
        this.before = b;
    }

    public void setBeforeAppendBro(ObtNode node){
        before.bro = node.copy();
    }

    public void devide(NodePair after){
        steps.add(new Devide(before,after));

    }

    public void normalInsert(NodePair after){
        steps.add(new NormalInsert(before,after));
        ObtNode saved = after.main.parent;
        while (saved != null){
            onlyChangeSons(saved);
            saved = saved.parent;
        }
    }

    private void onlyChangeSons(ObtNode node){
        NodePair copy = NodePair.createCopyNodePair(node);
        NodePair nodePair = NodePair.createNodePair(node);
        steps.add(new OnlyChangeSons(copy,nodePair));
    }

    public void normalDelete(NodePair after){
        steps.add(new NormalDelete(before,after));
        ObtNode saved = after.main.parent;
        while (saved != null){
            onlyChangeSons(saved);
            saved = saved.parent;
        }
    }

    public void borrowLeft(NodePair after){
        steps.add(new BorrowLeft(before,after));
    }

    public void borrowRight(NodePair after){
        steps.add(new BorrowRight(before,after));
    }

    public void mergeLeft(NodePair after){
        steps.add(new MergeLeft(before,after));
    }

    public void mergeRight(NodePair after){
        steps.add(new MergeRight(before,after));
    }

    class ChangeStep{
        NodePair before;
        NodePair after;

        ChangeStep(NodePair b, NodePair a){
            before = b;
            after = a;
        }
    }

    class Devide extends ChangeStep{

        Devide(NodePair b, NodePair a) {
            super(b, a);
        }
    }

    class NormalInsert extends ChangeStep{

        NormalInsert(NodePair b, NodePair a) {
            super(b, a);
        }
    }

    class NormalDelete extends ChangeStep{

        NormalDelete(NodePair b, NodePair a) {
            super(b, a);
        }
    }

    class BorrowLeft extends ChangeStep{

        BorrowLeft(NodePair b, NodePair a) {
            super(b, a);
        }
    }

    class BorrowRight extends ChangeStep{
        BorrowRight(NodePair b, NodePair a) {
            super(b, a);
        }
    }

    class MergeLeft extends ChangeStep{

        MergeLeft(NodePair b, NodePair a) {
            super(b, a);
        }
    }

    class MergeRight extends ChangeStep{

        MergeRight(NodePair b, NodePair a) {
            super(b, a);
        }
    }

    class OnlyChangeSons extends ChangeStep{
        OnlyChangeSons(NodePair b, NodePair a) {
            super(b, a);
        }
    }
}
