package ore;

import ore.ore_env.OreEnv;
import ore.ore_env.OreH;
import utils.BitUtils;
import utils.DataTools;

/**
 * @author zhzz
 * @Data create in 19:38 2019/1/8
 */
public class ORE {
    private OreEnv env;

    private ORE(){}

    public static ORE getDefaltORE(){
        ORE ore = new ORE();
        ore.env = OreEnv.getDefaultEnv();
        return ore;
    }

    //如果 ctr > ctl  , return   -1
    //如果 ctr < ctl  , return   1
    //如果 ctr = ctl  , return   0
    public int encCMP(CtR ctR, CtL ctL){
        OreEnv env = OreEnv.getDefaultEnv();
        byte[] r = ctR.getR();
        OreH oreH = env.getOreH();
        for (int i = 0; i < env.getN(); i++) {
            CtRvi vi = ctR.getCtrVi(i);
            CtLui ui = ctL.getCtLui(i);
            int h = oreH.applyH(ui.getUiBytes(),r,3);

            int Zihjf = BitUtils.isOne(vi.getVi(),2 * ui.getX()) ? 1 : 0;
            int Zihjs = BitUtils.isOne(vi.getVi(),2 * ui.getX() + 1) ? 1 : 0;
            int Zihj = Zihjf + Zihjs * 2;
            int ret = ( Zihj - h ) % 3 > 0 ? ( Zihj - h ) % 3 : ( Zihj - h ) % 3 + 3;
            if(ret == 1){
                return 1;
            }else if(ret == 2){
                return - 1;
            }
        }
        return 0;
    }

    public CtL encNumCtL(int num){
        return CtL.encNum(num,env);
    }

    public CtR encNumCtR(int num){
        return CtR.encNum(num,env);
    }

    public OreEnv getEnv() {
        return env;
    }

    public void setEnv(OreEnv env) {
        this.env = env;
    }
}
