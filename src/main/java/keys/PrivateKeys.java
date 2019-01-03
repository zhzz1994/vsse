package keys;


import it.unisa.dia.gas.jpbc.Element;
import ore.ore_env.OreSk;
import utils.cryptMethod.SecureRomdom;
import utils.cryptMethod.SymmetricBlockEnc;

import static utils.cryptMethod.SymmetricBlockEnc.dec_AES;
import static utils.cryptMethod.SymmetricBlockEnc.enc_AES;

/**
 * @author zhzz
 * @Data create in 15:15 2019/1/16
 */
public class PrivateKeys {
    private PublicKeys pks;
    private Element s;           //密钥s
    private OreSk oreSk;         //保序加密密钥

    private byte[] aesKey;

    public void genAesKey(){
        aesKey =  SecureRomdom.getRomdom(128,pks.getPairing().getZr().newRandomElement().toBytes());
    }

    public void genS(){
        this.s = pks.getPairing().getZr().newRandomElement().getImmutable();
    }

    public byte[] aesEnc(byte[] msg){
        return enc_AES(SymmetricBlockEnc.Mode.ECB, aesKey, null, msg);
    }

    public byte[] aesDec(byte[] encs){
        return dec_AES(SymmetricBlockEnc.Mode.ECB, aesKey, null, encs);
    }

    public PublicKeys getPks() {
        return pks;
    }

    public void setPks(PublicKeys pks) {
        this.pks = pks;
    }

    public Element getS() {
        return s.duplicate();
    }

    public void setS(Element s) {
        this.s = s;
    }

    public OreSk getOreSk() {
        return oreSk;
    }

    public void setOreSk(OreSk oreSk) {
        this.oreSk = oreSk;
    }
}
