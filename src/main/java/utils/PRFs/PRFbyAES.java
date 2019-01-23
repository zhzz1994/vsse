package utils.PRFs;


import utils.DataTools;
import utils.PRFs.PRF;
import utils.cryptMethod.SymmetricBlockEnc;

import static utils.cryptMethod.SymmetricBlockEnc.enc_AES;

/**
 * @author zhzz
 * @Data create in 15:55 2018/12/21
 */
public class PRFbyAES implements PRF {
    //伪随机数发生器，F : {0,1}^λ × [N] -->{0,1}^λ   128位

    @Override
    public byte[] applyF(byte[] b1, byte[] b2 ,int len) {
        return DataTools.subByte(enc_AES(SymmetricBlockEnc.Mode.ECB, b1, null, b2),0,len);
    }
}
