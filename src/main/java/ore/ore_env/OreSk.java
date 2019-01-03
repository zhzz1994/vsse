package ore.ore_env;

import utils.cryptMethod.SecureRomdom;

import javax.crypto.KeyGenerator;
import java.util.Random;

public class OreSk {
    //私钥，随机数
    private int len;
    private long romdomseed = 1;
    private byte[] k1;
    private byte[] k2;

    public OreSk(int len) {
        this.len = len;
        init();
    }

    public void setRomdomseed(long romdomseed) {
        this.romdomseed = romdomseed;
        init();
    }

    public void setK1(byte[] k1) {
        this.k1 = k1;
    }

    public void setK2(byte[] k2) {
        this.k2 = k2;
    }

    public int getLen() {
        return len;
    }

    public byte[] getK1() {
        return k1;
    }

    public byte[] getK2() {
        return k2;
    }

    private void init(){
        Random random = new Random(romdomseed);
        k1 = SecureRomdom.getRomdom(len,"k1"+random.nextInt());
        k2 = SecureRomdom.getRomdom(len,"k2"+random.nextInt());
    }
}
