package ore;

import ore.ore_env.OreEnv;
import utils.cryptMethod.SecureRomdom;

import java.util.Random;

/**
 * @author zhzz
 * @Data create in 17:07 2018/12/20
 */
public class CtR {
    private byte[] r;     //随机数r
    private CtRvi[] ctrVis;   //密文vi

    private CtR(){}  //禁止调用构造函数初始化

    public static CtR encNum(int num, OreEnv env){     //静态工厂方法
        CtR ctR = new CtR();
        ctR.NumberEnc(num,env);
        return ctR;
    }

    private void NumberEnc(int num ,OreEnv env) {
        Random random = new Random();
        r = SecureRomdom.getRomdom(env.getLambda(),"r"+random.nextInt());

        int n = env.getN();
        ctrVis = new CtRvi[n];
        Oredary daryY = Oredary.genByNum(num,env);
        for (int i = 0; i < n; i++) {
            ctrVis[i] = CtRvi.getCtRvi(daryY , i , r ,env);
        }
    }

    public byte[] getR() {
        return r;
    }

    public CtRvi getCtrVi(int i) {
        return ctrVis[i];
    }


}
