package obt;

/**
 * @author zhzz
 * @Data create in 15:40 2019/1/27
 */
class NodePair {
    ObtNode main;
    ObtNode bro;

    private NodePair(ObtNode main, ObtNode bro) {
        this.main = main;
        this.bro = bro;
    }


    public static NodePair createNodePair(ObtNode main, ObtNode bro) {
        return new NodePair(main, bro);
    }

    public static NodePair createNodePair(ObtNode main) {
        return new NodePair(main, null);
    }

    public static NodePair createCopyNodePair(ObtNode main, ObtNode bro) {
        return new NodePair(main.copy(), bro.copy());
    }

    public static NodePair createCopyNodePair(ObtNode main) {
        return new NodePair(main.copy(), null);
    }

    boolean isSingle() {
        return bro == null;
    }

    public void updateNP(){
        if(main != null){
            main.update();
        }
        if(bro != null){
            bro.update();
        }
    }
}
