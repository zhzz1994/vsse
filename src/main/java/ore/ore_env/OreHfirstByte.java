package ore.ore_env;

import utils.PRFs.PRF;

/**
 * @author zhzz
 * @Data create in 17:05 2019/1/8
 */
class OreHfirstByte implements OreH{
    private OreEnv env;

    @Override
    public int applyH(byte[] b1, byte[] b2 ,int mod) {
        PRF PRF = env.getPrf();
        byte[] bytes = PRF.applyF(b1,b2,env.getLambda() / 8);
        int i = bytes[0];
        return i % mod  >= 0 ? i % mod : i % mod + mod;
    }

    @Override
    public void setEnv(OreEnv env) {
        this.env = env;
    }
}
