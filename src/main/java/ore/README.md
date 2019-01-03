# ORE顺序可见加密算法


## 算法来源
Lewi K , Wu D J . Order-Revealing Encryption: New Constructions, Applications, and Lower Bounds[C]// Computer and Communications Security (CCS) 2016. ACM, 2016.

第四章 Domain Extension: A Large-Domain ORE


## 关键参数与函数

通过OreEnv静态方法设定运行参数

###加密int类型时参数选择：

程序只完全实现了int类型的数据加密

d ： 每块密文大小 ，可以任意选择，但最好为 2 的乘方 ，如 2,4,8,16 等 ，默认为4

n ： 密文分块块数 ，应当根据d值设定，使得 d^n >= 2 ^ 32 ,如d = 2,4,8,16时，
分别分块为1,2,3,4位，相应n >= 32，16,11,8 等， 
即32 / 1 = 32 ，32 / 2 = 16 ，32 / 3 = 11 ，32 / 4 = 8 ，
否则报错。 默认为16

lambda ： 安全参数λ，规范各函数输入输出长度，默认为128，不应改变，
实际程序中可选择 OreF 为 OreFbySHA256()，
即`OreEnv.setOreF(new OreFbySHA256());`,
可以任意设置为56 -- 256 （OrePIbyArray 中需要其大于56位，即 Long 类型长度为 56 位） 之间的任意8的倍数，默认为OreFbyAES，无法设置。

romdomseed ： 任意长整型数，用作程序中所有ramdom函数的随机种子，默认为1

OreH : 随机预言机模型，任意确定输入，确定（0,1,2）中的一个值作为输出。程序中只实现了一种随机预言机模型OreHfirstByte，
为默认选择

OreF ： 伪随机函数（pseudorandom functions）， 包含两种实现，分别为AES-128 与 SHA- 256，默认选择AES实现

OrePI : 置换函数，文章中PI函数，包括apply（）函数与其逆applyR（）。程序只实现了OrePIbyArray。

OreSk ： sk = (k1; k2); 程序只实现 OreSkBySR ，实际通过 SHA-256 生成密钥。


###密文类：

包含两种加密方法CtL，CtR,即为left，right加密。密文可通过ORE.encCMP(CtR ctR, CtL ctL)方法进行比较。



















