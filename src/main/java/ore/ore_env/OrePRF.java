package ore.ore_env;

public abstract class OrePRF {
    //伪随机数发生器，F : {0,1}^λ × [N] -->{0,1}^λ   128位


    abstract public byte[] apply(byte[] b1, byte[] b2);


}
