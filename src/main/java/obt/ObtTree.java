package obt;

import ore.CmpOre;

import java.util.Arrays;

/**
 * @author zhzz
 * @Data create in 14:25 2019/1/11
 */
public abstract class ObtTree {
    //B+树
    private ObtNode root;
    private int n = 5;    // 结点容量
    private int hight = 0;

    public ChangeProof insert(CmpItem item){
        Bnode find;
        if(getRoot() == null){
            find = createNode();
        }else {
            find = getRoot().find(item);
        }
        find.insert(item);
        return null;
    }

    public ChangeProof delete(CmpItem item){
        Bnode find;
        if(getRoot() != null){
            find = getRoot().find(item);
            //pkeys();
            if(find.contains(item)){
                find.delete(item);
            }else {
                System.out.println("can`t find it");
            }
        }
        return null;
    }

    public SearchProof search(CmpItem item){
        Bnode find = getRoot().find(item);
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
    public abstract Bnode createNode();

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
}
