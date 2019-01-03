package obt;

import ore.CmpOre;
import ore.ORE;
import org.junit.Test;
import utils.Timer;

import static org.junit.Assert.*;

/**
 * @author zhzz
 * @Data create in 16:41 2019/1/14
 */
public class ObtTreeTest {

    @Test
    public void insertOre() {
        ObtTree obtTree = new ObtTreeRaw();
        ObtNode node;
        ORE ore = ORE.getDefaltORE();
        for (int i = 0; i < 200; i++) {
            obtTree.insert(new CmpOre(i,ore));
            node = obtTree.getRoot();
            System.out.println("waiting");
        }
    }

    @Test
    public void removeOre() {
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeRaw();
        ObtNode node;
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        for (int i = 0; i < 1000; i++) {
            obtTree.insert(new CmpOre(i,ore));
        }
        System.out.println(timer.stop(0));
        System.out.println(obtTree.getHight());
        timer.start(1);
        for (int i = 1; i < 994; i++) {
            obtTree.delete(new CmpOre(i,ore));
            obtTree.printTree(3);
        }
        System.out.println(timer.stop(1));
        System.out.println(obtTree.getHight());
    }

    @Test
    public void removeOreR() {
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeRaw();
        ObtNode node;
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        for (int i = 20000; i >= 0 ; i --) {
            obtTree.insert(new CmpOre(i,ore));
        }
        System.out.println(timer.stop(0));
        System.out.println(obtTree.getHight());
        timer.start(1);
        for (int i = 18708; i >= 1 ; i --) {
            obtTree.delete(new CmpOre(i,ore));
        }
        System.out.println(timer.stop(1));
        System.out.println(obtTree.getHight());
    }

    @Test
    public void removeOreRomdom() {
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
    public void cutTree(){
        Timer timer = new Timer();
        ObtTree obtTree = new ObtTreeRaw();
        ObtNode node;
        ORE ore = ORE.getDefaltORE();
        timer.start(0);
        for (int i = 2000; i >= 0 ; i --) {
            CmpOre add = new CmpOre(i,ore);
            obtTree.insert(add);
            add.compress();
        }
        System.out.println(timer.stop(0));
        System.out.println(obtTree.getHight());
        timer.start(1);
        for (int i = 1870; i >= 1 ; i --) {
            obtTree.delete(new CmpOre(i,ore));
        }
        System.out.println(timer.stop(1));
        System.out.println(obtTree.getHight());
    }


    @Test
    public void search() {
    }

    @Test
    public void rangeSearch() {
    }
}