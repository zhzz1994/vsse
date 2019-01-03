package obt;

import ore.CmpOre;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zhzz
 * @Data create in 14:57 2019/1/10
 */
public abstract class ObtNode implements Bnode {
    protected LinkedList<CmpItem> keys;
    // key 指键值对中 键，即为 标志 ，并非密钥含义
    //sons[0]  < keys [0]
    protected LinkedList<ObtNode> sons;
    protected ObtNode parent;
    protected ObtNode Orderleft;
    protected ObtNode Orderright;
    protected boolean isLeaf;
    protected ObtTree tree;

    ObtNode(ObtTree tree){
        keys = new LinkedList<CmpItem>();
        sons = new LinkedList<ObtNode>();
        this.tree = tree;
    }

    @Override
    public Bnode find(CmpItem item){
        if(isLeaf) return this;
        for (int i = 0; i < keys.size(); i++) {
            if(item.isSmallerThan(keys.get(i))){
                return sons.get(i).find(item);
            }
        }
        return sons.get(sons.size() - 1).find(item);
    }

    @Override
    public void insert(CmpItem item){
        int keySize = keys.size();
        if(keySize != 0){
            for (int i = 0; i < keySize; i++) {
                if(item.isSmallerThan(keys.get(i))){
                    insertKeyAndSon(i ,item, null , null);
                    break;
                }
                if(i == keySize - 1){
                    insertKeyAndSon(i + 1 ,item, null , null);
                }
            }
        }else {
            insertKeyAndSon(0 ,item,null ,null);
        }
    }

    @Override
    public void delete(CmpItem item) {
        int keySize = keys.size();
        for (int i = 0; i < keySize; i++) {
            if(item.isEqualWith(keys.get(i))){
                keys.remove(i);
                break;
            }
        }
        if(keys.size() < tree.getNodeMinSize()){
            borrowOrMerge();
        }else {
            ObtNode saved = this;
            while (saved != null){
                saved.update();
                saved = saved.parent;
            }
        }
    }

    //合并结点，先尝试向兄弟结点借一个子节点，若其无法借出，则尝试与兄弟结点合并
    //可以看做devide方法的逆过程
    private void borrowOrMerge(){
        if(isBrother(Orderleft) && Orderleft.keys.size() > tree.getNodeMinSize()){
            borrowFromLeft();
        }else if(isBrother(Orderright) && Orderright.keys.size() > tree.getNodeMinSize()){
            borrowFromRight();
        }else if(isBrother(Orderleft)){
            merge(Orderleft,this);
        }else if(isBrother(Orderright)){
            merge(this,Orderright);
        }
        if(this == tree.getRoot() && keys.size() == 0){
            ObtNode root = this.sons.size() == 0 ? null : this.sons.getFirst();
            tree.setRoot(root);
            tree.decHight();
        }else if(this != tree.getRoot() && parent.keys.size() < tree.getNodeMinSize()){
            parent.borrowOrMerge();
        }else {
            ObtNode saved = this;
            while (saved != null){
                saved.update();
                saved = saved.parent;
            }
        }
    }

    private void merge(ObtNode left , ObtNode right){
        int index = left.parent.sons.indexOf(left);
        if(!left.isLeaf){
            left.keys.add(left.parent.keys.get(index));
            for (ObtNode node : right.sons) {
                node.parent = left;
                left.sons.add(node);
            }
        }
        left.keys.addAll(right.keys);
        left.parent.keys.remove(index);
        setOrder(left,right.Orderright);
        left.parent.sons.remove(right);
        left.update();
    }

    private void borrowFromLeft() {
        int index = parent.sons.indexOf(Orderleft);
        CmpItem moved = Orderleft.keys.getLast();
        Orderleft.keys.remove(moved);
        if(!isLeaf){
            this.keys.add(0,parent.keys.get(index));
            ObtNode movedNode = Orderleft.sons.getLast();
            Orderleft.sons.remove(movedNode);
            this.sons.add(0,movedNode);
            movedNode.parent = this;
            parent.keys.set(index,moved);
        }else {
            this.keys.add(0,moved);
            parent.keys.set(index,keys.getFirst());
        }
        this.update();
        Orderleft.update();
    }

    private void borrowFromRight() {
        int index = parent.sons.indexOf(this);
        CmpItem moved = Orderright.keys.getFirst();
        Orderright.keys.remove(Orderright.keys.getFirst());
        if(!isLeaf){
            ObtNode movedNode = Orderright.sons.getFirst();
            Orderright.sons.remove(movedNode);
            this.sons.add(movedNode);
            movedNode.parent = this;
            this.keys.add(parent.keys.get(index));
            parent.keys.set(index,moved);
        }else {
            this.keys.add(moved);
            parent.keys.set(index,Orderright.keys.getFirst());
        }
        this.update();
        Orderright.update();
    }

    private boolean isBrother(ObtNode node){
        if(node == null || node.parent == null) return false;
        return node.parent == parent;
    }

    private void insertKeyAndSon(int pos ,CmpItem item, ObtNode left , ObtNode right){
        if(keys.size() == 0){
            tree.setRoot(this);
            tree.incHight();
        }
        keys.add(pos,item);
        if(left != null && right != null){
            left.parent = this;
            right.parent = this;
            sons.add(pos,left);
            sons.add(pos + 1,right);
        }
        if(keys.size() >= tree.getN()){   //结点存储量大于限定值，n阶B+树中任意结点最大为n-1，即5阶B+数最多存储4个值，故用>=符号而不是>符号
            devide();
        }
        ObtNode saved = this;
        while (saved != null){
            saved.update();
            saved = saved.parent;
        }
    }

    //结点分裂，一个结点分解为两个结点，叶子节点分裂：3 ——>(1,2)   ,   4 ——>(2,2)
    //非叶子节点分裂：3 ——>(1,1),1   ,   4 ——>(2,1)，1
    //中间元素（right 中 第一个key） 提升为父节点key
    private void devide(){
        CmpItem riseKey = keys.get(keys.size() / 2);
        ObtNode left = createNode();
        ObtNode right = createNode();
        devideLeft(left);
        devideRight(right);

        setOrder(left,right);
        setOrder(Orderleft,left);
        setOrder(right,Orderright);

        left.update();
        right.update();

        if (this == tree.getRoot()){
            ObtNode root = createNode();
            root.insertKeyAndSon(0 ,riseKey, left , right);
        }else {
            int index = parent.sons.indexOf(this);
            parent.sons.remove(this);
            parent.insertKeyAndSon(index,riseKey,left,right);
        }
    }

    private void devideRight(ObtNode right){
        if(isLeaf){
            right.isLeaf = true;
            for (int i = ( keys.size() / 2 ); i < keys.size(); i++) {
                right.keys.add(keys.get(i));
            }
        }else {
            for (int i = ( keys.size() / 2 ) + 1; i < keys.size(); i++) {
                right.keys.add(keys.get(i));
            }
            for (int i = ( keys.size() / 2 ) + 1; i < sons.size(); i++) {
                sons.get(i).parent = right;
                right.sons.add(sons.get(i));
            }
        }
    }

    private void devideLeft(ObtNode left){
        if(isLeaf){
            left.isLeaf = true;
            for (int i = 0; i < ( keys.size() / 2 ); i++) {
                left.keys.add(keys.get(i));
            }
        }else {
            for (int i = 0; i < ( keys.size() / 2 ); i++) {
                left.keys.add(keys.get(i));
            }
            for (int i = 0; i < left.keys.size() + 1; i++) {
                sons.get(i).parent = left;
                left.sons.add(sons.get(i));
            }
        }
    }

    private void setOrder(ObtNode left ,ObtNode right){
        if(left != null){
            left.Orderright = right;
        }
        if (right != null){
            right.Orderleft = left;
        }
    }

    @Override
    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    @Override
    public boolean contains(CmpItem item) {
        if(isLeaf){
            for (CmpItem key : keys) {
                if (item.isEqualWith(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    void printKeys(int layer){
        if(layer > 0){
            String s = "++++";
            StringBuilder pos = new StringBuilder();
            for (int i = 0; i < tree.getHight() - layer; i++) {
                pos.append(s);
            }
            System.out.println(pos + keys.toString());
            for (ObtNode son : sons) {
                son.printKeys(layer - 1);
            }
        }
    }

    public abstract ObtNode createNode();

    public abstract void update();
}
