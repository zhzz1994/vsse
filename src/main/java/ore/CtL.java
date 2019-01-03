package ore;

import ore.ore_env.OreEnv;
import utils.cryptMethod.GeneralHash;

import static utils.DataTools.bytesConjunction;

/**
 * @author zhzz
 * @Data create in 17:07 2018/12/20
 */
public class CtL {
    private CtLui[] ctLuis;  //密文

    private CtL() {
    }   //禁止调用构造函数初始化

    public static CtL encNum(int num, OreEnv env){     //静态工厂方法
        CtL ctL = new CtL();
        ctL.NumberEnc(num,env);
        return ctL;
    }

    private void NumberEnc(int num , OreEnv env){
        int n = env.getN();
        ctLuis = new CtLui[n];
        Oredary daryX = Oredary.genByNum(num,env);
        for (int i = 0; i < n; i++) {
            ctLuis[i] = CtLui.getCtLui(daryX , i ,env);
        }
    }

    public CtLui getCtLui(int i) {
        return ctLuis[i];
    }

    public byte[] getMHash(){
        byte[] con = new byte[0];
        for (CtLui ctLui : ctLuis) {
            con = bytesConjunction(con,ctLui.getUiBytes());
        }
        return GeneralHash.Hash(GeneralHash.HashMode.SHA256,con);
    }
}
