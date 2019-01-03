package ore.ore_env;

import java.io.Serializable;

/**
 * @author zhzz
 * @Data create in 19:44 2019/1/9
 */
public abstract class OreSk implements Serializable {
    protected byte[] k1;    // 只是byte数组而已
    protected byte[] k2;    // 只是byte数组而已
    protected OreEnv env;

    public void setK1(byte[] k1) {
        this.k1 = k1;
    }

    public void setK2(byte[] k2) {
        this.k2 = k2;
    }

    public byte[] getK1() {
        return k1;
    }

    public byte[] getK2() {
        return k2;
    }
}
