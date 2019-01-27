package obt;

import it.unisa.dia.gas.jpbc.Element;
import keys.PrivateKeys;
import keys.PublicKeys;

/**
 * @author zhzz
 * @Data create in 15:08 2019/1/23
 */
public class AccEnc {
    private byte[] accEnc;

    public void addAccBySk(byte[] bytes , PrivateKeys sks){
        PublicKeys pks = sks.getPks();
        Element ei = pks.getPairing().getZr().newRandomElement();
        ei.setFromBytes(bytes);
        Element sum = sks.getS().add(ei);
        accChange(sum,sks);
    }

    public void delAccBySk(byte[] bytes , PrivateKeys sks){
        PublicKeys pks = sks.getPks();
        Element ei = pks.getPairing().getZr().newRandomElement();
        ei.setFromBytes(bytes);
        Element sum = sks.getS().add(ei).negate();
        accChange(sum,sks);
    }

    public void addAccBySk(int num , PrivateKeys sks){
        PublicKeys pks = sks.getPks();
        Element ei = pks.getPairing().getZr().newRandomElement().set(num);
        Element sum = sks.getS().add(ei);
        accChange(sum,sks);
    }

    public void delAccBySk(int num , PrivateKeys sks){
        PublicKeys pks = sks.getPks();
        Element ei = pks.getPairing().getZr().newRandomElement().set(num);
        Element sum = sks.getS().add(ei).invert();
        accChange(sum,sks);
    }

    private void accChange(Element sum ,PrivateKeys sks){
        PublicKeys pks = sks.getPks();
        if(accEnc == null){
            accEnc = sks.aesEnc(sum.toBytes());
        }else {
            byte[] accDec = sks.aesDec(accEnc);
            Element acc = pks.getPairing().getZr().newRandomElement();
            acc.setFromBytes(accDec);
            Element as = acc.mul(sum);
            accEnc = sks.aesEnc(as.toBytes());
        }
    }

    public byte[] getAccEnc() {
        return accEnc == null ? new byte[0] : accEnc;
    }

    public void setAccEnc(byte[] accEnc) {
        this.accEnc = accEnc;
    }
}
