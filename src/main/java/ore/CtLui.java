package ore;

import ore.ore_env.OreEnv;
import utils.PRFs.PRF;
import ore.ore_env.OrePI;
import ore.ore_env.OreSk;
import utils.DataTools;

/**
 * @author zhzz
 * @Data create in 16:05 2019/1/8
 */
class CtLui {
    //文中ui
    private byte[] uiBytes;
    private int x;

    private CtLui() {
    }

    public static CtLui getCtLui(Oredary daryX, int i ,OreEnv env){
        CtLui ctLui = new CtLui();
        ctLui.genX(daryX, i ,env);
        ctLui.genUiBytes(daryX, i ,env);
        return ctLui;
    }

    private void genUiBytes(Oredary daryX, int i , OreEnv env) {
        PRF PRF = env.getPrf();
        OreSk sk = env.getSk();

        byte[] byteX = new byte[1];
        byteX[0] = (byte) x;
        uiBytes = PRF.applyF(sk.getK1(), DataTools.bytesConjunction(daryX.getPerfixI(i), byteX),env.getLambda() / 8);   //文章中应为i-1，此处不处理空前缀，故前缀包含其本身，应该无影响
    }

    private void genX(Oredary daryX, int i , OreEnv env) {
        PRF PRF = env.getPrf();
        OreSk sk = env.getSk();
        OrePI orePI = env.getOrePI();

        byte[] oreprf = PRF.applyF(sk.getK2(), daryX.getPerfixI(i),env.getLambda() / 8);  //文章中应为i-1，此处不处理空前缀，故前缀包含其本身，应该无影响
        x = orePI.apply(oreprf, daryX.getXi(i));
    }

    public byte[] getUiBytes() {
        return uiBytes;
    }

    public int getX() {
        return x;
    }
}
