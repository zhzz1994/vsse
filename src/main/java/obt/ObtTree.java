package obt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhzz
 * @Data create in 14:25 2019/1/11
 */
public abstract class ObtTree {
    //B+树
    private ObtNode root;
    private int n;    // 结点容量
    private int hight;

    ChangeRecord changeRecord;

    ObtTree(){
        n = 5;
        hight = 0;
        changeRecord = ChangeRecord.createRecord();
    }

    public ChangeProof insert(CmpItem item){
        ObtNode find;
        if(getRoot() == null){
            find = createNode();
        }else {
            find = getRoot().find(item);
        }
        find.insert(item);
        changeRecord.updateRecords();
        changeRecord = ChangeRecord.createRecord();
        return null;
    }

    public ChangeProof delete(CmpItem item){
        ObtNode find;
        if(getRoot() != null){
            find = getRoot().find(item);
            //pkeys();
            if(find.contains(item)){
                find.delete(item);
            }else {
                System.out.println("can`t find it");
            }
        }
        changeRecord.updateRecords();
        changeRecord = ChangeRecord.createRecord();
        return null;
    }

    public SearchProof search(CmpItem item){
        ObtNode find = getRoot().find(item);
        return null;
    }

    public SearchProof rangeSearch(CmpItem start , CmpItem end){
        return null;
    }

    public ObtNode find(CmpItem item){
        return (ObtNode) getRoot().find(item);
    }

    public void printTree(int layer){
        int l = layer < hight ?layer : hight;
        root.printKeys(l);
    }

    //工厂方法模式
    public abstract ObtNode createNode();

    public ObtNode getRoot(){
        return root;
    }

    public void setRoot(ObtNode node){
        root = node;
    }

    public int getN() {
        return n;
    }

    public int getNodeMinSize(){
        return ( getN() - 1 ) / 2 ;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getHight() {
        return hight;
    }

    void incHight(){
        this.hight ++;
    }

    void decHight(){
        this.hight --;
    }

    public ChangeRecord getChangeRecord() {
        return changeRecord;
    }

    public void setChangeRecord(ChangeRecord changeRecord) {
        this.changeRecord = changeRecord;
    }
}
