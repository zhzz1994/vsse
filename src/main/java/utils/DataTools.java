package utils;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

public class DataTools {

    //    作者：A_客
    //    链接：https://www.jianshu.com/p/17e771cb34aa
    //    來源：简书
    //    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static ByteBuffer buffer = ByteBuffer.allocate(8);
    //byte 数组与 long 的相互转换
    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.clear();
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static long bytesToLong8byte(byte[] bytes) {
        byte[] byte8 = new byte[8];
        System.arraycopy(bytes,0,byte8,0,8);
        return bytesToLong(byte8);
    }

    public static byte[] bytesConjunction(byte[] a,byte[] b){
        if(a == null){
            return b;
        }else if(b == null){
            return a;
        }else {
            byte[] newhash = new byte[a.length + b.length];
            for(int i = 0;i< a.length;i++){
                newhash[i] = a[i];
            }
            for(int i = 0;i< b.length;i++){
                newhash[i + a.length] = b[i];
            }
            return newhash;
        }
    }

    public static byte[] subByte(byte[] bytes,int start , int end){
        start = start >= 0 ? start : 0;
        assert end >= start;
        byte[] ret = new byte[end - start];
        System.arraycopy(bytes,start,ret,0,end - start);
        return ret;
    }

    public static byte[] hashToBooleanArray(byte[] hash){
        if (hash == null || hash.length < 0)
            return null;
        byte[] BooleanArray = new byte[8 * hash.length];
        byte[] hashcopy = hash.clone();
        for(int i = 0 ; i < hashcopy.length ; i++){
            for(int j = 0 ; j < 8 ; j++){
                BooleanArray[8*i +j] = (byte)((hashcopy[i]&0x80)>>7);
                hashcopy[i] = (byte)(hashcopy[i] << 1);
            }
        }
        return BooleanArray;
    }

    public static byte[] hashToBooleanArray(byte[] hash , int removeHead){
        if (hash == null || hash.length < 0)
            return null;
        byte[] BooleanArray = new byte[8 * hash.length - 16];
        byte[] hashcopy = hash.clone();
        for(int i = removeHead ; i < hashcopy.length ; i++){
            for(int j = 0 ; j < 8 ; j++){
                BooleanArray[8*i + j - removeHead*8] = (byte)((hashcopy[i]&0x80)>>7);
                hashcopy[i] = (byte)(hashcopy[i] << 1);
            }
        }
        return BooleanArray;
    }

    public static byte[] booleanArrayToHash(byte[] booleanArray){
        if (booleanArray == null || booleanArray.length < 0)
            return null;
        byte[] hash;
        if(booleanArray.length%8 == 0)
        {
            hash = new byte[booleanArray.length/8];
            for(int i = 0;i <booleanArray.length/8;i++ ){
                for(int j = 0;j<8;j++){
                    hash[i] = (byte) (hash[i] + (booleanArray[i*8 + j] << (7-j)));
                }
            }
        }else{
            hash = new byte[booleanArray.length/8 + 1];
            for(int i = 0;i <booleanArray.length/8;i++ ){
                for(int j = 0;j<8;j++){
                    hash[i] = (byte) (hash[i] + (booleanArray[i*8 + j] << (7-j)));
                }
            }
            for(int j = 0;j <booleanArray.length%8;j++ ){
                hash[booleanArray.length/8] = (byte) (hash[booleanArray.length/8] + (booleanArray[(booleanArray.length/8)*8 + j] << (7-j)));
            }
        }
        return hash;
    }

    public static String hashToString(byte[] hash){
        String hashstr = "";
        if (hash == null || hash.length < 0)
            return null;
        byte[] hashcopy = hash.clone();
        for(int i = 0 ; i < hashcopy.length ; i++){
            for(int j = 0 ; j < 8 ; j++){
                String hashBit = Integer.toString((hashcopy[i]&0x80)>>7);
                hashcopy[i] = (byte)(hashcopy[i] << 1);
                hashstr = hashstr + hashBit;
            }
            hashstr = hashstr + ",";
        }
        return hashstr;
    }

    public static String hashToString(byte[] hash,int removeHead){
        String hashstr = "";
        if (hash == null || hash.length < 0)
            return null;
        byte[] hashcopy = hash.clone();
        for(int i = removeHead ; i < hashcopy.length ; i++){
            for(int j = 0 ; j < 8 ; j++){
                String hashBit = Integer.toString((hashcopy[i]&0x80)>>7);
                hashcopy[i] = (byte)(hashcopy[i] << 1);
                hashstr = hashstr + hashBit;
            }
            hashstr = hashstr + ",";
        }
        return hashstr;
    }

    public static byte[] listTobytes(List<Byte> list){
        if (list == null || list.size() < 0){
            return null;
        };
        byte[] bytes = new byte[list.size()];
        int i = 0;
        Iterator<Byte> iterator = list.iterator();
        while (iterator.hasNext()) {
            bytes[i] = iterator.next();
            i++;
        }
        return bytes;
    }




}
