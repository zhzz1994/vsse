package obt;


import utils.cryptMethod.GeneralHash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.DataTools.bytesConjunction;

/**
 * @author zhzz
 * @Data create in 19:20 2019/1/17
 */
public class MerkleHashPath {
    private List<OneLayerHash> layerHashes;

    private MerkleHashPath(){}

    public static MerkleHashPath createMerkleHashPath(ObtNodeM nodeM){
        MerkleHashPath path = new MerkleHashPath();
        path.addEveryLayer(nodeM);
        return path;
    }

    private void addEveryLayer(ObtNodeM nodeM){
        layerHashes = new ArrayList<>();
        ObtNodeM layer = nodeM;
        while (layer != nodeM.tree.getRoot()){
            layerHashes.add(new OneLayerHash(layer));
            layer = (ObtNodeM) layer.parent;
        }
    }

    public boolean verify(ObtNodeM nodeM){
        ObtNodeM root = (ObtNodeM) nodeM.tree.getRoot();
        byte[] theHash = nodeM.getMHash();
        for (OneLayerHash layerHash : layerHashes) {
            theHash = layerHash.conHash(theHash);
        }
//        System.out.println(Arrays.toString(theHash));
//        System.out.println(Arrays.toString(root.getMHash()));
        return Arrays.equals(theHash, root.getMHash());
    }

    class OneLayerHash{
        int pos; //获得结点位置
        List<byte[]> hashs;
        OneLayerHash(ObtNodeM nodeM){
            init(nodeM);
        }
        void init(ObtNodeM nodeM){
            hashs = new ArrayList<>();
            ObtNodeM parent = (ObtNodeM) nodeM.parent;
            pos = parent.sons.indexOf(nodeM);
            for (int i = 0; i < parent.sons.size(); i++) {
                ObtNodeM broM = (ObtNodeM) parent.sons.get(i);
                if(i != pos){
                    hashs.add(broM.getMHash());
                }
            }
        }

        byte[] conHash(byte[] node){
            List<byte[]> allHashes = new ArrayList<>(hashs);
            allHashes.add(pos,node);
            byte[] con = new byte[0];
            for (byte[] bytes : allHashes) {
                con = bytesConjunction(con,bytes);
            }
            return GeneralHash.Hash(GeneralHash.HashMode.SHA256,con);
        }
    }
}
