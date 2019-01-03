package ore;

import keys.PublicKeys;
import keys.PrivateKeys;
import obt.AccEnc;
import obt.CmpItem;

import it.unisa.dia.gas.jpbc.Element;
import obt.ObtNodeM;
import utils.cryptMethod.GeneralHash;

import static utils.DataTools.bytesConjunction;

/**
 * @author zhzz
 * @Data create in 15:58 2019/1/14
 */
public class CmpOre implements CmpItem {
    private CtL ctL;
    private CtR ctR;
    private int i;
    private ORE ore;
    private AccEnc accEnc;

    public CmpOre(int i , ORE ore){
        this.i = i;
        ctL = ore.encNumCtL(i);
        ctR = ore.encNumCtR(i);
        this.ore = ore;
    }

    @Override
    public boolean isBiggerThan(CmpItem item) {
        return CmpWith(item) == 1;
    }

    @Override
    public boolean isSmallerThan(CmpItem item) {
        return CmpWith(item) == - 1;
    }

    @Override
    public boolean isEqualWith(CmpItem item) {
        return CmpWith(item) == 0;
    }

    @Override
    public int CmpWith(CmpItem item) {
        if(item instanceof CmpOre){
            CmpOre cmp = (CmpOre) item;
            return ore.encCMP(ctR,cmp.ctL);
        }else {
            throw new RuntimeException("类型错误，不可比较");
        }
    }

    @Override
    public byte[] getMHash() {
        return GeneralHash.Hash(GeneralHash.HashMode.SHA256,bytesConjunction(ctL.getMHash(),accEnc.getAccEnc()));
    }

    public int getI(){
        return i;
    }

    //压缩，即元素插入后删除ore，ctr信息
    public void compress(){
        ctR = null;
        ore = null;
    }

    @Override
    public String toString() {
        return "" + i;
    }

    public void setAccEnc(AccEnc accEnc) {
        this.accEnc = accEnc;
    }

    public void initAccEnc() {
        accEnc = new AccEnc();
    }

    public AccEnc getAccEnc() {
        return accEnc;
    }
}
