package ore;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import keys.PublicKeys;
import keys.PrivateKeys;
import org.junit.Test;

/**
 * @author zhzz
 * @Data create in 16:35 2019/1/14
 */
public class CmpOreTest {

    @Test
    public void cmpWith() {
        ORE ore = ORE.getDefaltORE();
        CmpOre[] cmpOres = new CmpOre[20];
        for (int i = 0; i < 20; i++) {
            cmpOres[i] = new CmpOre(i,ore);
        }
        CmpOre c11 = new CmpOre(19,ore);
        for (int i = 0; i < cmpOres.length; i++) {
            System.out.println(c11.isBiggerThan(cmpOres[i]));
        }
    }

    @Test
    public void accTest(){
        PublicKeys pks = PublicKeys.getNewPublicKeys();
        PrivateKeys privateKeys = new PrivateKeys();
        privateKeys.setPks(pks);
        privateKeys.genS();
        privateKeys.genAesKey();
        Pairing pairing = pks.getPairing();
        ORE ore = ORE.getDefaltORE();
        CmpOre c = new CmpOre(5,ore);
        c.initAccEnc();
        c.getAccEnc().addAccBySk(55, privateKeys);
        c.getAccEnc().addAccBySk(66, privateKeys);
        c.getAccEnc().delAccBySk(66, privateKeys);
        System.out.println("ss");


        Element gg = pairing.pairing(pks.getG(),pks.getG()).getImmutable();
        Element one = pairing.getZr().newRandomElement().setToOne().getImmutable();
        Element five = pairing.getZr().newRandomElement().set(5).getImmutable();
        Element four = pairing.getZr().newRandomElement().set(4).getImmutable();
        Element to = pairing.getZr().newRandomElement().set(20).getImmutable();
        Element g4 = pks.getG().powZn(four).getImmutable();
        Element g5 = pks.getG().powZn(five).getImmutable();
        Element g15 = pks.getG().powZn(one.div(five)).getImmutable();
        System.out.println(one.div(five));
        System.out.println(one.div(five).mul(five));
        Element e1 = pairing.pairing(pks.getG(),g4);
        Element e2 = pairing.pairing(pks.getG(),g5);
        Element e51 = pairing.pairing(pks.getG(),g15);
        Element e4 = e1.duplicate().mul(e2.duplicate()).getImmutable();
        Element e20 = pairing.pairing(pks.getG(),pks.getG().powZn(to)).getImmutable().mul(gg);
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e4);
        System.out.println(e20);
        Element e3 = pairing.pairing(pks.getG(),pks.getG().powZn(one.div(five))).getImmutable();
        Element e5 = e4.duplicate().mul(e3.duplicate()).getImmutable();
        System.out.println(e3);
        System.out.println(e5);
        System.out.println(gg);
        System.out.println(e2.mul(e51));
        System.out.println(e5.mul(gg));

    }

    @Test
    public void aesTest(){
        PublicKeys pks = PublicKeys.getNewPublicKeys();
        PrivateKeys privateKeys = new PrivateKeys();
        privateKeys.setPks(pks);
        privateKeys.genS();
        privateKeys.genAesKey();
        ORE ore = ORE.getDefaltORE();
        String s = "adsfgthrahstnznfdggfGNafhmsgjsEHTABRNY2561SGG6TR16AE498g5tarE65R65TGBAT1R65hn";
        byte[] enc = privateKeys.aesEnc(s.getBytes());
        System.out.println(new String(enc));
        byte[] dec = privateKeys.aesDec(enc);
        System.out.println(new String(dec));
    }

    @Test
    public void accEncTest(){
        PublicKeys pks = PublicKeys.getNewPublicKeys();
        PrivateKeys privateKeys = new PrivateKeys();
        privateKeys.setPks(pks);
        privateKeys.genS();
        privateKeys.genAesKey();
        Pairing pairing = pks.getPairing();
        ORE ore = ORE.getDefaltORE();
        CmpOre c = new CmpOre(5,ore);
        c.initAccEnc();
        c.getAccEnc().addAccBySk(55, privateKeys);
        c.getAccEnc().addAccBySk(66, privateKeys);
        c.getAccEnc().delAccBySk(66, privateKeys);
        System.out.println("aaa");
        Element one = pairing.getZr().newRandomElement().setToOne();
        Element zero = pairing.getZr().newRandomElement().setToZero().getImmutable();
        Element five = pairing.getZr().newRandomElement().set(5).getImmutable();
        Element four = pairing.getZr().newRandomElement().set(4).getImmutable();
        Element sum = privateKeys.getS().add(five).getImmutable();
        Element ne = sum.negate().getImmutable();
        Element z = sum.add(ne).getImmutable();
        System.out.println("aa");
        byte[] sbe = privateKeys.aesEnc(sum.toBytes());
        one.setFromBytes(privateKeys.aesDec(sbe));
        System.out.println(one);





    }
}