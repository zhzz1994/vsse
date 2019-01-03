package ore;

import ore.ore_env.OreEnv;
import utils.PRFs.PRFbySHA256;
import org.junit.Test;
import utils.Timer;

/**
 * @author zhzz
 * @Data create in 18:49 2019/1/9
 */
public class ORETest {

    @Test
    public void encCMP() {
        ORE ore = ORE.getDefaltORE();
        CtL ctL = ore.encNumCtL(555);
        CtR ctR = ore.encNumCtR(555);
        System.out.println(ore.encCMP(ctR,ctL));
        ore.getEnv().setD(6);
        ore.getEnv().setN(16);
        ore.getEnv().setPrf(new PRFbySHA256());
        ore.getEnv().setLambda(128);
        CtL ctl = ore.encNumCtL(555);
        CtR ctr = ore.encNumCtR(545);
        System.out.println(ore.encCMP(ctr,ctl));
    }

    @Test
    public void timeCostCtR(){
        ORE ore = ORE.getDefaltORE();
        CtL ctl = ore.encNumCtL(555);
        CtR[] ctRS = new CtR[1000000];
        Timer timer = new Timer();
        timer.start(0);
        for (int i = 0; i < ctRS.length; i++) {
            ctRS[i] = ore.encNumCtR(i);
        }
        System.out.println(timer.stop(0));
        timer.start(1);
        for(CtR ctr : ctRS){
            //System.out.println(ORE.encCMP(ctr,ctl));
            ore.encCMP(ctr,ctl);
        }
        System.out.println(timer.stop(1));
    }

    @Test
    public void timeCostCtL(){
        ORE ore = ORE.getDefaltORE();
        CtR ctr = ore.encNumCtR(555);
        CtL[] ctlS = new CtL[1000000];
        Timer timer = new Timer();
        timer.start(0);
        for (int i = 0; i < ctlS.length; i++) {
            ctlS[i] = ore.encNumCtL(i);
        }
        System.out.println(timer.stop(0));
        timer.start(1);
        for(CtL ctl : ctlS){
            ore.encCMP(ctr,ctl);
            //System.out.println(ORE.encCMP(ctr,ctl));
        }
        System.out.println(timer.stop(1));
    }
}