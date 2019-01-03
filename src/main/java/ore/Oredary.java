package ore;

import ore.ore_env.OreEnv;

/**
 * @author zhzz
 * @Data create in 17:24 2018/12/20
 */
class Oredary {
    private byte[] dary;

    private Oredary() {}

    public static Oredary genByNum(int num ,OreEnv env){
        Oredary oredary = new Oredary();
        oredary.setDaryByNum(num,env);
        return oredary;
    }

    private void setDaryByNum(int num ,OreEnv env){
        int n = env.getN();
        int d = env.getD();
        dary = new byte[n];
        int count = num;
        for (int i = n - 1; i >= 0; i--) {
            dary[i] =  (byte) (count % d);
            count = count / d;
        }
    }

    public byte getXi(int i){  //与论文不同，程序从0开始
        return dary[i];
    }

    public byte[] getPerfixI(int i){
        byte[] result = new byte[i];
        System.arraycopy(dary,0,result,0,i);
        return result;
    }
}
