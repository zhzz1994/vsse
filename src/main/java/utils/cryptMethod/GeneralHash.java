package utils.cryptMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.util.encoders.Hex;

import utils.stdlib.StdOut;

public class GeneralHash {
    public enum HashMode{
        SHA256,
        SHA384,
        SHA512,
    }

    private static final int DEFAULT_BLOCK_SIZE = 1024;

    public static byte[] Hash(HashMode hashMode, byte[] message){
        MessageDigest md = null;
        try{
            switch(hashMode){
                case SHA256:
                    md = MessageDigest.getInstance("SHA-256");
                    break;
                case SHA384:
                    md = MessageDigest.getInstance("SHA-384");
                    break;
                case SHA512:
                    md = MessageDigest.getInstance("SHA-512");
                    break;
                default:
                    //Default Hash is SHA256
                    md = MessageDigest.getInstance("SHA-256");
                    break;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(message);
        return md.digest();
    }

    public static byte[] Hash(HashMode hashMode, InputStream in){
        MessageDigest md = null;
        try{
            switch(hashMode){
                case SHA256:
                    md = MessageDigest.getInstance("SHA-256");
                    break;
                case SHA384:
                    md = MessageDigest.getInstance("SHA-384");
                    break;
                case SHA512:
                    md = MessageDigest.getInstance("SHA-512");
                    break;
                default:
                    //Default Hash is SHA256
                    md = MessageDigest.getInstance("SHA-256");
                    break;
            }
            int inL;
            byte[] b = new byte[DEFAULT_BLOCK_SIZE];
            while ((inL = in.read(b)) != -1) {
                md.update(b, 0, inL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md.digest();
    }

    private static void Test_Hash_String(){
        String message1 = "TestGeneralHash-1";
        String message2 = "TestGeneralHash-2";
        byte[] byte256 = GeneralHash.Hash(HashMode.SHA256, message1.getBytes());
        String sha256 = new String(Hex.encode(byte256));
        StdOut.println("SHA256, message = " + message1);
        StdOut.println("Result = " + sha256 + ", length = " + byte256.length);
        //Hash result for the same value should be equal
        byte[] byte256_1 = GeneralHash.Hash(HashMode.SHA256, message1.getBytes());
        String sha256_1 = new String(Hex.encode(byte256_1));
        StdOut.println("SHA256, message = " + message1);
        StdOut.println("Result = " + sha256_1 + ", length = " + byte256_1.length);
        assert(sha256.equals(sha256_1));
        //Hash result for different values should be distinct
        byte[] byte256_2 = GeneralHash.Hash(HashMode.SHA256, message2.getBytes());
        String sha256_2 = new String(Hex.encode(byte256_2));
        StdOut.println("SHA256, message = " + message2);
        StdOut.println("Result = " + sha256_2 + ", length = " + byte256_2.length);
        StdOut.println();
        assert(!sha256.equals(sha256_2));

        byte[] byte384 = GeneralHash.Hash(HashMode.SHA384, message1.getBytes());
        String sha384 = new String(Hex.encode(byte384));
        StdOut.println("SHA384, message = " + message1);
        StdOut.println("Result = " + sha384 + ", length = " + byte384.length);
        //Hash result for the same value should be equal
        byte[] byte384_1 = GeneralHash.Hash(HashMode.SHA384, message1.getBytes());
        String sha384_1 = new String(Hex.encode(byte384_1));
        StdOut.println("SHA384, message = " + message1);
        StdOut.println("Result = " + sha384_1 + ", length = " + byte384_1.length);
        assert(sha384.equals(sha384_1));
        //Hash result for different values should be distinct
        byte[] byte384_2 = GeneralHash.Hash(HashMode.SHA384, message2.getBytes());
        String sha384_2 = new String(Hex.encode(byte384_2));
        StdOut.println("SHA384, message = " + message2);
        StdOut.println("Result = " + sha384_2 + ", length = " + byte384_2.length);
        StdOut.println();
        assert(!sha384.equals(sha384_2));

        byte[] byte512 = GeneralHash.Hash(HashMode.SHA512, message1.getBytes());
        String sha512 = new String(Hex.encode(byte512));
        StdOut.println("SHA512, message = " + message1);
        StdOut.println("Result = " + sha512 + ", length = " + byte512.length);
        //Hash result for the same value should be equal
        byte[] byte512_1 = GeneralHash.Hash(HashMode.SHA512, message1.getBytes());
        String sha512_1 = new String(Hex.encode(byte512_1));
        StdOut.println("SHA512, message = " + message1);
        StdOut.println("Result = " + sha512_1 + ", length = " + byte512_1.length);
        assert(sha512.equals(sha512_1));
        //Hash result for different values should be distinct
        byte[] byte512_2 = GeneralHash.Hash(HashMode.SHA512, message2.getBytes());
        String sha512_2 = new String(Hex.encode(byte512_2));
        StdOut.println("SHA512, message = " + message2);
        StdOut.println("Result = " + sha512_2 + ", length = " + byte512_2.length);
        StdOut.println();
        assert(!sha512.equals(sha512_2));
    }

    private static void Test_Hash_File(){
        try {
            File fileIn = new File("docs1.5on.zip");
            FileInputStream in;

            in = new FileInputStream(fileIn);
            byte[] byte256 = GeneralHash.Hash(HashMode.SHA256, in);
            String sha256 = new String(Hex.encode(byte256));
            StdOut.println("File SHA256 = " + sha256 + ", length = " + byte256.length);
            in.close();

            in = new FileInputStream(fileIn);
            byte[] byte384 = GeneralHash.Hash(HashMode.SHA384, in);
            String sha384 = new String(Hex.encode(byte384));
            StdOut.println("File SHA384 = " + sha384 + ", length = " + byte384.length);
            in.close();

            in = new FileInputStream(fileIn);
            byte[] byte512 = GeneralHash.Hash(HashMode.SHA512, in);
            String sha512 = new String(Hex.encode(byte512));
            StdOut.println("File SHA512 = " + sha512 + ", length = " + byte512.length);
            in.close();

        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args){
        Test_Hash_String();
        //Test_Hash_File();
    }
}
