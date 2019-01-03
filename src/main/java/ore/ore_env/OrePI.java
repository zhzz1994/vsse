package ore.ore_env;

/**
 * @author zhzz
 * @Data create in 16:46 2019/1/8
 */
public interface OrePI {
    int apply(byte[] prf, int x);
    int applyR(byte[] prf, int x);
    void setEnv(OreEnv env);
}
