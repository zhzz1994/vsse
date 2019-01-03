package ore.ore_env;

import utils.cryptMethod.DataTools;

import java.math.BigInteger;
import java.util.*;

public class OrePRP {
    // π : {0,1}^λ × [d] --> [d] ,置换函数及逆置换
    private int[] array;
    private int[] arrayR;
    private int d;    //元素个数
    private long romdomseed = 1;
    Map<Integer,Integer> map = new HashMap<Integer, Integer>();

    public OrePRP(int d) {
        this.d = d;
        initArray();
        initArrayR();
    }

    public void setRomdomseed(long r){
        romdomseed = r;
        initArray();
        initArrayR();
    }

    public void setRomdomseed(byte[] key){
        setRomdomseed(DataTools.bytesToLong8byte(key));
    }

    public <T> List<T> apply(List<T> list){
        List<T> ret = new ArrayList<T>();
        for (int i : array) {
            ret.add(list.get(i));
        }
        return ret;
    }

    public <T> List<T> applyR(List<T> list){
        List<T> ret = new ArrayList<T>();
        for (int i : arrayR) {
            ret.add(list.get(i));
        }
        return ret;
    }

    private void initArray(){
        array = new int[d];
        for (int i = 0; i < d; i++) {
            array[i] = i;
        }
        Random random = new Random(romdomseed);
        for (int i = 0; i < d; i++) {
            int saved = array[i];
            int index = random.nextInt(d);
            array[i] = array[index];
            array[index] = saved;
        }
    }

    private void initArrayR(){
        arrayR = new int[d];
        for (int i = 0; i < d; i++) {
            map.put(array[i],i);
        }
        for (int i = 0; i < d; i++) {
            arrayR[i] = map.get(i);
        }
        map.clear();
    }
}
