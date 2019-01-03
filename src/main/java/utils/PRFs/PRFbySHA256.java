package utils.PRFs;

import utils.PRFs.PRF;
import utils.cryptMethod.GeneralHash;

import static utils.DataTools.bytesConjunction;
import static utils.DataTools.subByte;
import static utils.cryptMethod.SymmetricBlockEnc.enc_AES;

/**
 * @author zhzz
 * @Data create in 15:56 2018/12/21
 */
public class PRFbySHA256 implements PRF {
    //伪随机数发生器，F : {0,1}^λ × [N] -->{0,1}^λ   128位

    @Override
    public byte[] applyF(byte[] b1, byte[] b2 ,int len) {
        return subByte(GeneralHash.Hash(GeneralHash.HashMode.SHA256,bytesConjunction(b1,b2)),0,len);
    }
}
