package obt;

import ore.CmpOre;
import ore.ORE;
import org.junit.Test;
import utils.Timer;
import utils.cryptMethod.GeneralHash;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static utils.DataTools.bytesConjunction;
import static utils.DataTools.subByte;

/**
 * @author zhzz
 * @Data create in 21:31 2019/1/21
 */
public class ObtNodeMTest {

    @Test
    public void update() {
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeM();
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        for (int i = 0; i < 400 ; i ++) {
            int n = ( i * 19 ) % (3*7*11*17*23);
            CmpOre add = new CmpOre(n,ore);
            obtTree.insert(add);
        }
        System.out.println(timer.stop(0));
        System.out.println(obtTree.getHight());
        timer.start(1);
        for (int i = 3; i < 397 ; i ++) {
            int n = ( i * 19 ) % (3*7*11*17*23);
            obtTree.delete(new CmpOre(n,ore));
            obtTree.printTree(4);
            System.out.println("********************");
            System.out.println("********************");
        }
        System.out.println(timer.stop(1));
        System.out.println(obtTree.getHight());
    }

    @Test
    public void merkleTest(){
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeM();
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        for (int i = 0; i < 400 ; i ++) {
            int n = ( i * 19 ) % (3*7*11*17*23);
            CmpOre add = new CmpOre(n,ore);
            obtTree.insert(add);
        }
        System.out.println(timer.stop(0));
        System.out.println(obtTree.getHight());
    }

    @Test
    public void hashTest(){
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeM();
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        for (int i = 0; i < 400 ; i ++) {
            int n = ( i * 19 ) % (3*7*11*17*23);
            CmpOre add = new CmpOre(n,ore);
            obtTree.insert(add);
            if(i % 54 == 0){
                eqHash((ObtNodeM) obtTree.getRoot());
            }
        }
        System.out.println("********************");
        System.out.println("********************");
        eqSons((ObtNodeM) obtTree.getRoot());
        System.out.println(timer.stop(0));
        System.out.println(obtTree.getHight());
        timer.start(1);
        for (int i = 3; i < 397 ; i ++) {
            int n = ( i * 19 ) % (3*7*11*17*23);
            obtTree.delete(new CmpOre(n,ore));
//            obtTree.printTree(4);
//            System.out.println("********************");
//            System.out.println("********************");
            if(i % 44 == 0){
                eqHash((ObtNodeM) obtTree.getRoot());
            }
        }
        eqSons((ObtNodeM) obtTree.getRoot());
        System.out.println(timer.stop(1));
        System.out.println(obtTree.getHight());
    }

    @Test
    public void hashPathTest(){
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeM();
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        int n;
        for (int i = 0; i < 400 ; i ++) {
            n = ( i * 19 ) % (3*7*11*17*23);
            CmpOre add = new CmpOre(n,ore);
            obtTree.insert(add);
        }
        System.out.println("********************");
        System.out.println("********************");
        n = ( 4 * 19 ) % (3*7*11*17*23);
        ObtNode find = obtTree.find(new CmpOre(n,ore));
        MerkleHashPath path = MerkleHashPath.createMerkleHashPath((ObtNodeM) find);
        System.out.println(path.verify((ObtNodeM) find));
        for (int i = 3; i < 397 ; i ++) {
            n = ( i * 19 ) % (3*7*11*17*23);
            obtTree.delete(new CmpOre(n,ore));
        }
        n = ( 19 * 356 ) % (3*7*11*17*23);
        find = obtTree.find(new CmpOre(n,ore));
        path = MerkleHashPath.createMerkleHashPath((ObtNodeM) find);
        System.out.println(path.verify((ObtNodeM) find));
    }

    @Test
    public void cloneNodeTest(){
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeM();
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        int n;
        for (int i = 0; i < 400 ; i ++) {
            n = ( i * 19 ) % (3*7*11*17*23);
            CmpOre add = new CmpOre(n,ore);
            obtTree.insert(add);
        }
        System.out.println("********************");
        System.out.println("********************");
        n = ( 4 * 19 ) % (3*7*11*17*23);
        ObtNode find = obtTree.find(new CmpOre(n,ore));
        MerkleHashPath path = MerkleHashPath.createMerkleHashPath((ObtNodeM) find);
        ObtNode findc = find.copy();
        System.out.println(path.verify((ObtNodeM) find));
        for (int i = 3; i < 337 ; i ++) {
            n = ( i * 19 ) % (3*7*11*17*23);
            obtTree.delete(new CmpOre(n,ore));
        }
        n = ( 19 * 356 ) % (3*7*11*17*23);
        find = obtTree.find(new CmpOre(n,ore));
        path = MerkleHashPath.createMerkleHashPath((ObtNodeM) find);
        System.out.println(path.verify((ObtNodeM) find));
    }












    private void eqHash(ObtNodeM nodeM){
        List<byte[]> list = nodeM.getSonsHash();
        byte[] con = new byte[0];
        for (byte[] bytes : list) {
            con = bytesConjunction(con,bytes);
        }
        byte[] mHash = GeneralHash.Hash(GeneralHash.HashMode.SHA256,con);
        System.out.println(Arrays.toString(nodeM.getMHash()));
        System.out.println(Arrays.toString(mHash));
    }

    private void eqSons(ObtNodeM nodeM){
        if(!nodeM.isLeaf){
            for (ObtNode node : nodeM.sons) {
                eqSons((ObtNodeM) node);
            }
            eqHash(nodeM);
        }else {
            eqHash(nodeM);
        }
    }
}