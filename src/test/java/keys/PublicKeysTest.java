package keys;

import it.unisa.dia.gas.jpbc.Element;
import org.junit.Test;

/**
 * @author zhzz
 * @Data create in 15:29 2019/1/16
 */
public class PublicKeysTest {

    @Test
    public void jpbcTest(){
        PublicKeys pks = PublicKeys.getNewPublicKeys(80,128);
        Element z1 = pks.getPairing().getZr().newRandomElement().getImmutable();
        Element z2 = pks.getPairing().getZr().newRandomElement().getImmutable();
        Element g1 = pks.getPairing().pairing(pks.getG(),pks.getG().powZn(z1)).getImmutable();
        Element g2 = pks.getPairing().pairing(pks.getG(),pks.getG().powZn(z2)).getImmutable();
        Element z1z2 = z1.mul(z2).getImmutable();
        System.out.println(g1.mul(g2));
        System.out.println(pks.getPairing().pairing(pks.getG(),pks.getG().powZn(z1).mul(pks.getG().powZn(z2))));
        System.out.println(pks.getPairing().pairing(pks.getG(),pks.getG().powZn(z1.add(z2))));
        System.out.println(pks.getPairing().pairing(pks.getG(),pks.getG()).powZn(z1.add(z2)));
    }

    @Test
    public void save() {
        PublicKeys pks = PublicKeys.getNewPublicKeys(80,128);
        pks.save();
        pks = PublicKeys.getNewPublicKeys(80,128);
        pks = PublicKeys.loadPublicKeys();
        System.out.println("aaaaa");
    }

    @Test
    public void load() {
    }
}