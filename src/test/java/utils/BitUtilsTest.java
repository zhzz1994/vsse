package utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhzz
 * @Data create in 14:55 2019/1/9
 */
public class BitUtilsTest {

    @Test
    public void isOne() {
        byte[] i = new byte[1];
        i[0] = 2;
        System.out.println(BitUtils.isOne(i,1));
        byte[] is = BitUtils.setOne(i,7);
        System.out.println(is[0]);



    }
}