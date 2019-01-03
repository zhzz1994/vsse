package ore.ore_env;

public interface OreH {
    //H : {0,1}^λ ×{0,1}^λ  -->  Z3 , 随机hash函数

    int applyH(byte[] b1, byte[] b2 , int mod);
    void setEnv(OreEnv env);
}
