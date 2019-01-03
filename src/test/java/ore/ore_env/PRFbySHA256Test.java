package ore.ore_env;

import org.junit.Test;
import utils.PRFs.PRF;
import utils.PRFs.PRFbyAES;
import utils.Timer;

/**
 * @author zhzz
 * @Data create in 16:56 2019/1/4
 */
public class PRFbySHA256Test {

    @Test
    public void apply() {
        int count = 0;
        Timer timer = new Timer();
        PRF PRF = new PRFbyAES();
        timer.start(0);
        for (int i = 0; i < 1000; i++) {
            String key = "6206c34e2186e752c74e6df32ab8fa5b";
            String msg = "sjfgnfdhghjhk" + count;
            count ++;
            PRF.applyF(key.getBytes(),msg.getBytes(),128/8);
        }
        System.out.println(timer.stop(0));
        timer.start(1);
        for (int i = 0; i < 10000; i++) {
            String key = "6206c34e2186e752c74e6df32ab8fa5b";
            String msg = "sjfgnfdhghjhk" + count;
            count ++;
            PRF.applyF(key.getBytes(),msg.getBytes(),128/8);
        }
        System.out.println(timer.stop(1));
        timer.start(2);
        for (int i = 0; i < 100000; i++) {
            String key = "6206c34e2186e752c74e6df32ab8fa5b";
            String msg = "sjfgnfdhghjhk" + count;
            count ++;
            PRF.applyF(key.getBytes(),msg.getBytes(),128/8);
        }
        System.out.println(timer.stop(2));
        timer.start(3);
        for (int i = 0; i < 1000000; i++) {
            String key = "6206c34e2186e752c74e6df32ab8fa5b";
            String msg = "sjfgnfdhghjhk" + count;
            count ++;
            PRF.applyF(key.getBytes(),msg.getBytes(),128/8);
        }
        System.out.println(timer.stop(3));
    }
}