package utils.PRFs;

public interface PRF {
    //伪随机数发生器，F : {0,1}^λ × [N] -->{0,1}^λ   128位
    //PRFbyAES 为其 AES实现
    //PRFbySHA256 为其 SHA256 实现
    byte[] applyF(byte[] b1, byte[] b2 ,int len);
}
