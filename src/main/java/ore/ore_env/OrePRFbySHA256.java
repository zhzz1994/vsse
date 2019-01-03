package ore.ore_env;

/**
 * @author zhzz
 * @Data create in 15:56 2018/12/21
 */
public class OrePRFbySHA256 extends OrePRF {
    //伪随机数发生器，F : {0,1}^λ × [N] -->{0,1}^λ   128位

    @Override
    public byte[] apply(byte[] b1, byte[] b2) {
        return new byte[0];
    }
}
