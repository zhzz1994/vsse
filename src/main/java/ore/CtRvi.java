package ore;

import ore.ore_env.OreSk;
import ore.ore_env.*;
import utils.BitUtils;
import utils.DataTools;
import utils.PRFs.PRF;

/**
 * @author zhzz
 * @Data create in 16:05 2019/1/8
 */
class CtRvi {
    byte[] vi;

    private CtRvi(){}

    public static CtRvi getCtRvi(Oredary daryY , int i , byte[] r ,OreEnv env){
        CtRvi ctRvi = new CtRvi();
        ctRvi.genVi(daryY,i,r,env);
        return ctRvi;
    }

    private void genVi(Oredary daryY ,int i , byte[] r ,OreEnv env){
        int size = env.getD() % 4 == 0 ?  env.getD() / 4 : env.getD() / 4 + 1;
        vi = new byte[size];
        for (int j = 0; j < env.getD(); j++) {
            setZij(daryY,j,i,r,env);
        }
    }

    private void setZij(Oredary daryY ,int j , int i, byte[] r ,OreEnv env){
        PRF PRF = env.getPrf();
        OreSk sk = env.getSk();
        OrePI orePI = env.getOrePI();
        OreH oreH = env.getOreH();

        int cmped;
        int jr = orePI.applyR(PRF.applyF(sk.getK2(),daryY.getPerfixI(i),env.getLambda() / 8),j);
        if(jr == daryY.getXi(i)){
            cmped = 0;
        }else {
            cmped = jr < daryY.getXi(i) ? 1 : 2;
        }
        //  CMP  :    CMP(jr,yi)        CMP(1,1)  =  0     j* = yi ， 结果设为 0       jr 即 文中 j*
        //                              CMP(1,2)  =  1     j* < yi ， 结果设为 1
        //                              CMP(2,1)  =  2     j* > yi ， 结果设为 2

        byte[] bytej = new byte[1];
        bytej[0] = (byte) j;
        int h = oreH.applyH(PRF.applyF(sk.getK1(), DataTools.bytesConjunction(daryY.getPerfixI(i), bytej),env.getLambda() / 8),r ,3);
        int Zij = ( cmped + h ) % 3;

        //用两位编码0,1,2 ，
        //       0 --> 00       不用编码
        //       1 --> 01       后一位设1
        //       2 --> 10       前一位设1
        if(Zij == 1){
            vi = BitUtils.setOne(vi,j * 2);
        }else if(Zij == 2){
            vi = BitUtils.setOne(vi,j * 2 + 1);
        }
    }

    public byte[] getVi() {
        return vi;
    }
}
