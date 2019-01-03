package ore.ore_env;

import ore.ore_env.OreSk;
import ore.ore_env.OreEnv;
import utils.cryptMethod.SecureRomdom;

import java.util.Random;

public class OreSkBySR extends OreSk {

    public OreSkBySR(OreEnv oreEnv) {
        this.env = oreEnv;
        init();
    }

    private void init(){
        Random random = new Random();
        k1 = SecureRomdom.getRomdom(env.getLambda(),"k1"+random.nextInt());
        k2 = SecureRomdom.getRomdom(env.getLambda(),"k2"+random.nextInt());
    }
}
