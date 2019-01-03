package ore.ore_env;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhzz
 * @Data create in 19:21 2018/12/20
 */
public class OrePRPTest {
    //OrePRP oreprp = new OrePRP();

    @Test
    public void testListInt(){
        OrePRP oreprp = new OrePRP(10);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        List<Integer> listex =  oreprp.apply(list);
        List<Integer> listexR =  oreprp.applyR(listex);
        Assert.assertEquals(list,listexR);
        System.out.println(list);
        System.out.println(listex);
        System.out.println(listexR);
    }

    @Test
    public void testListStr(){
        OrePRP oreprp = new OrePRP(20);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("str" + i + i);
        }
        List<String> listex =  oreprp.apply(list);
        List<String> listexR =  oreprp.applyR(listex);
        Assert.assertEquals(list,listexR);
        System.out.println(list);
        System.out.println(listex);
        System.out.println(listexR);
    }

    @Test
    public void testListLong(){
        OrePRP oreprp = new OrePRP(20);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("str" + i + i);
        }
        String key = "adfghfjemhk";
        oreprp.setRomdomseed(key.getBytes());
        List<String> listex =  oreprp.apply(list);
        List<String> listexR =  oreprp.applyR(listex);
        Assert.assertEquals(list,listexR);
        System.out.println(list);
        System.out.println(listex);
        System.out.println(listexR);
    }


}