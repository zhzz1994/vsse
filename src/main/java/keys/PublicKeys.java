package keys;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import utils.stdlib.Out;

import java.io.*;

/**
 * @author zhzz
 * @Data create in 15:15 2019/1/16
 */
public class PublicKeys implements Serializable{
    private transient Element g;                              //g
    private transient Pairing pairing;                       //e(*,*)
    private PairingParameters typeAParams;                   //用于序列化
    private byte[] g_bytes;                                  //用于序列化

    private PublicKeys(){}

    public static PublicKeys getNewPublicKeys(){
        return getNewPublicKeys(160, 512);
    }

    public static PublicKeys getNewPublicKeys(int rbits , int qbits){
        PublicKeys pks = new PublicKeys();
        TypeACurveGenerator pg = new TypeACurveGenerator(rbits, qbits);
        pks.typeAParams = pg.generate();
        pks.setPairingAndG();
        return pks;
    }

    private void setPairingAndG(){
        pairing = PairingFactory.getPairing(typeAParams);
        if(g_bytes == null){
            g = pairing.getG1().newRandomElement();
            g_bytes = g.toBytes();
        }else {
            g = pairing.getG1().newRandomElement();
            g.setFromBytes(g_bytes);
        }
    }

    public void save(){
        save("C:\\Users\\zz\\Desktop\\data\\vsse\\PublicKeys.txt");
    }

    public void save(String url){

//        Out out = new Out("vsseJPBC.properties");
//        out.println(typeAParams);
//        Pairing pairing = PairingFactory.getPairing("vsseJPBC.properties");

        File file = new File(url);
        file.deleteOnExit();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("无法创建文件");
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PublicKeys loadPublicKeys(){
        return loadPublicKeys("C:\\Users\\zz\\Desktop\\data\\vsse\\PublicKeys.txt");
    }

    public static PublicKeys loadPublicKeys(String url){
        File file = new File(url);
        PublicKeys pks = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            pks = (PublicKeys) objectInputStream.readObject();
            pks.setPairingAndG();
            objectInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return pks;
    }

    public Element getG() {
        return g.duplicate();
    }

    public Pairing getPairing() {
        return pairing;
    }
}
