package ore.ore_env;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author zhzz
 * @Data create in 15:32 2018/12/21
 */
public class OreSkTest {

    @Test
    public void testSetRomdomseed() {
        OreSk sk = new OreSk(128);
        System.out.println(Arrays.toString(sk.getK1()));
        System.out.println(Arrays.toString(sk.getK2()));
        sk.setRomdomseed(50);
        System.out.println(Arrays.toString(sk.getK1()));
        System.out.println(Arrays.toString(sk.getK2()));
    }
}