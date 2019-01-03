package utils.cryptMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import utils.stdlib.StdOut;


public class SymmetricStreamEnc {
    private static final int DEFUALT_BLOCK_SIZE = 128;

    public static byte[] enc_RC4(byte[] key, byte[] plaintext){
        // Make sure the validity of key, and plaintext
        assert (key != null && plaintext != null);
        KeyParameter kp = new KeyParameter(key);
        StreamCipher streamCipher = new RC4Engine();
        streamCipher.init(true, kp);

        byte[] ciphertext = new byte[plaintext.length];
        streamCipher.processBytes(plaintext, 0, plaintext.length, ciphertext, 0);
        return ciphertext;
    }

    public static byte[] dec_RC4(byte[] key, byte[] ciphertext){
        // Make sure the validity of key, and plaintext
        assert (key != null && ciphertext != null);
        KeyParameter kp = new KeyParameter(key);
        StreamCipher streamCipher = new RC4Engine();
        streamCipher.init(false, kp);

        byte[] plaintext = new byte[ciphertext.length];
        streamCipher.processBytes(ciphertext, 0, ciphertext.length, plaintext, 0);
        return plaintext;
    }

    public static void enc_RC4(byte[] key, InputStream in, OutputStream out){
        // Make sure the validity of key, and plaintext
        assert (key != null && in != null && out != null);
        KeyParameter kp = new KeyParameter(key);
        StreamCipher streamCipher = new RC4Engine();
        streamCipher.init(true, kp);
        try {
            int inBlockSize = DEFUALT_BLOCK_SIZE;
            int outBlockSize = DEFUALT_BLOCK_SIZE;
            byte[] inblock = new byte[inBlockSize];
            byte[] outblock = new byte[outBlockSize];
            int inL;
            byte[] rv = null;
            while ((inL=in.read(inblock, 0, inBlockSize)) > 0)
            {
                streamCipher.processBytes(inblock, 0, inL, outblock, 0);
                rv = Hex.encode(outblock, 0, inL);
                out.write(rv, 0, rv.length);
                out.write('\n');
            }
        } catch (DataLengthException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void dec_RC4(byte[] key, InputStream in, OutputStream out){
        // Make sure the validity of key, and plaintext
        assert (key != null && in != null && out != null);
        KeyParameter kp = new KeyParameter(key);
        StreamCipher streamCipher = new RC4Engine();
        streamCipher.init(false, kp);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            byte[] inblock = null;
            byte[] outblock = null;
            String rv = null;

            while ((rv = br.readLine()) != null){
                inblock = Hex.decode(rv);
                outblock = new byte[DEFUALT_BLOCK_SIZE];
                streamCipher.processBytes(inblock, 0, inblock.length, outblock, 0);
                out.write(outblock, 0, inblock.length);
            }
        } catch (DataLengthException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void Test_RC4_String(){
        String key = "6206c34e2186e752c74e6df32ab8fa5b";
        StdOut.println("Test RC4.");
        String message = "Message123456";
        StdOut.println("Message = " + message);
        byte[] ciphertext = enc_RC4(Hex.decode(key), message.getBytes());
        StdOut.println("Encrypted Ciphertext = " + Hex.encode(ciphertext));
        String plaintext = new String(dec_RC4(Hex.decode(key), ciphertext));
        StdOut.println("Decrypted Plaintext = " + plaintext);
        StdOut.println();
    }

    public static void Test_RC4_File(){
        try {
            String key = "6206c34e2186e752c74e6df32ab8fa5b";
            File fileIn = new File("docs1.5on.zip");
            File fileEnc = new File("docs1.5on.enc");
            File fileDec = new File("docs1.5on.dec");
            FileInputStream in = new FileInputStream(fileIn);
            FileOutputStream out = new FileOutputStream(fileEnc);

            enc_RC4(Hex.decode(key), in, out);
            in.close();
            out.close();

            in = new FileInputStream(fileEnc);
            out = new FileOutputStream(fileDec);

            dec_RC4(Hex.decode(key), in, out);
            in.close();
            out.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
