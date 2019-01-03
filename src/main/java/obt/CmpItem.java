package obt;

/**
 * @author zhzz
 * @Data create in 15:06 2019/1/10
 */
public interface CmpItem {
    //b+树中可比较元素接口
    //可用来包装其他密文类型，包括CtL，CtR等密文类型，以及数值，字符串等类型
    boolean isBiggerThan(CmpItem item);

    boolean isSmallerThan(CmpItem item);

    boolean isEqualWith(CmpItem item);

    int CmpWith(CmpItem item);
    //返回 -1,0,1 ，表示小于，等于，大于

    byte[] getMHash();
}
