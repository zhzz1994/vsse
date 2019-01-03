package ore.ore_env;

import utils.DataTools;
import java.util.Random;

/**
 * @author zhzz
 * @Data create in 15:39 2019/1/4
 */
class OrePIbyArray implements OrePI {
    private int[] array;
    private OreEnv env;

    private void initArray(byte[] prf){
        int d = env.getD();
        array = new int[d];
        for (int i = 0; i < d; i++) {
            array[i] = i;
        }
        Random random = new Random(DataTools.bytesToLong8byte(prf));
        for (int i = 0; i < d; i++) {
            int saved = array[i];
            int index = random.nextInt(d);
            array[i] = array[index];
            array[index] = saved;
        }
    }

    @Override
    public int apply(byte[] prf, int x){
        int d = env.getD();
        initArray(prf);
        if (x < 0 || x >= d) {
            throw new RuntimeException("x is wrong ！！！！！");
        }
        return array[x];
    }

    @Override
    public int applyR(byte[] prf, int x){
        int d = env.getD();
        initArray(prf);
        if (x < 0 || x >= d) {
            throw new RuntimeException("x is wrong ！！！！！");
        }
        for (int i = 0; i < d; i++) {
            if(x == array[i]){
                return i;
            }
        }
        return -1; // 不可能返回-1
    }

    public void setEnv(OreEnv env) {
        this.env = env;
    }
}
