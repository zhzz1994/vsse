package utils.cryptMethod;

import java.util.Arrays;

/**
 * @author zhzz
 * @Data create in 15:04 2018/12/21
 */
public class SecureRomdom {

    //hash生成随机数
    public static byte[] getRomdom(int len){
        String key = "hash生成随机数";
        byte[] seed = key.getBytes();
        return getRomdom(len,seed);
    }

    public static byte[] getRomdom(int len , String s){
        byte[] seed = s.getBytes();
        return getRomdom(len,seed);
    }

    public static byte[] getRomdom(int len , byte[] seed){
        byte[] byte256 = GeneralHash.Hash(GeneralHash.HashMode.SHA256,seed);
        return Arrays.copyOfRange(byte256,0,len/8);
    }
}
