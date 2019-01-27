package obt;

import utils.cryptMethod.GeneralHash;
import java.util.ArrayList;
import java.util.List;

import static utils.DataTools.bytesConjunction;

/**
 * @author zhzz
 * @Data create in 16:58 2019/1/21
 */
public class ObtNodeM extends ObtNode {

    protected byte[] mHash;

    ObtNodeM(ObtTree tree) {
        super(tree);
    }

    @Override
    public ObtNode createNode() {
        return new ObtNodeM(tree);
    }

    @Override
    public void update(){
        if(isLeaf){
            byte[] con = new byte[0];
            for(CmpItem cmpItem : keys){
                con = bytesConjunction(con,cmpItem.getMHash());
            }
            mHash = GeneralHash.Hash(GeneralHash.HashMode.SHA256,con);
        }else {
            byte[] con = new byte[0];
            for(ObtNode obtNode : sons){
                ObtNodeM obtNodeM = (ObtNodeM) obtNode;
                con = bytesConjunction(con,obtNodeM.getMHash());
            }
            mHash = GeneralHash.Hash(GeneralHash.HashMode.SHA256,con);
        }
    }

    @Override
    public ObtNodeM copy() {
        ObtNodeM node = new ObtNodeM(tree);
        node.mHash = getMHash();
        return node;
    }

    public byte[] getMHash() {
        return mHash;
    }

    public void setMHash(byte[] mHash) {
        this.mHash = mHash;
    }

    public List<byte[]> getSonsHash(){
        List<byte[]> list = new ArrayList<>();
        if(isLeaf){
            for(CmpItem cmpItem : keys){
                list.add(cmpItem.getMHash());
            }
        }else {
            for (ObtNode obtNode : sons){
                ObtNodeM obtNodeM = (ObtNodeM) obtNode;
                list.add(obtNodeM.getMHash());
            }
        }
        return list;
    }
}
