package ore.ore_env;

import org.junit.Assert;
import org.junit.Test;
import utils.Timer;

import static org.junit.Assert.*;

/**
 * @author zhzz
 * @Data create in 17:44 2019/1/7
 */
public class OrePITest {

    @Test
    public void apply() {
        String key = "sdafdghfnjmryrEAGRWHNTWEJMTARYYHSYJ";
        OrePI orePI = new OrePIbyArray();
        int index;
        Timer timer = new Timer();
        timer.start(0);
        for (int i = 0; i < 10000000; i++) {
            index = orePI.apply(key.getBytes(),2);
            System.out.println(index);
            Assert.assertEquals(orePI.applyR(key.getBytes(),index),2);
        }
        System.out.println(timer.stop(0));
    }

    @Test
    public void testException(){
        StringBuilder key = new StringBuilder("sdafdghfnjmryrEAGRWHNTWEJMTARYYHSYJ");
        OrePI orePI = new OrePIbyArray();
        orePI.apply(key.toString().getBytes(),15);
    }
}