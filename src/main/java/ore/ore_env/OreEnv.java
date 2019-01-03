package ore.ore_env;

import utils.PRFs.PRF;
import utils.PRFs.PRFbyAES;

/**
 * @author zhzz
 * @Data create in 17:09 2018/12/20
 */
public class OreEnv {
    //ore保序加密环境设置
    private int n;   //d^^n = 2 ^^ 32
    private int d;   //长度，默认为4，最好值为2^^i
    private int lambda;  //即为安全参数λ
    private long romdomseed;
    private PRF prf;

    private OrePI orePI;
    private OreH oreH;

    private OreSk sk;

    private OreEnv(){}

    private void setDefaultEncInt(){ // 初始化顺序不能任意选择
        n = 16;
        d = 4;
        lambda = 128;
        romdomseed = 1;
        prf = new PRFbyAES();
        orePI = new OrePIbyArray();
        orePI.setEnv(this);
        oreH = new OreHfirstByte();
        oreH.setEnv(this);
        sk = new OreSkBySR(this);
    }

    public static OreEnv getDefaultEnv(){
        OreEnv oreEnv = new OreEnv();
        oreEnv.setDefaultEncInt();
        return oreEnv;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getLambda() {
        return lambda;
    }

    public long getRomdomseed() {
        return romdomseed;
    }

    public void setRomdomseed(long romdomseed) {
        this.romdomseed = romdomseed;
    }

    public PRF getPrf() {
        return prf;
    }

    public void setPrf(PRF prf) {
        this.prf = prf;
    }

    public OreSk getSk() {
        return sk;
    }

    public void setSk(OreSk sk) {
        this.sk = sk;
    }

    public OrePI getOrePI() {
        return orePI;
    }

    public void setOrePI(OrePI orePI) {
        this.orePI = orePI;
    }

    public OreH getOreH() {
        return oreH;
    }

    public void setOreH(OreH oreH) {
        this.oreH = oreH;
    }

    public void setLambda(int lambda) {
        if(lambda % 8 != 0 ){
            throw new RuntimeException("lambda 应当为8的倍数");
        }
        this.lambda = lambda;
    }
}



