package tech.mystox.test.maintest;

/**
 * Created by mystoxlol on 2019/4/9, 14:07.
 * company: kongtrolink
 * description:
 * update record:
 */
public class CRCTest {
    public static void main(String[] args) {
        byte[] bytes = {0x0a,0x05,0x32,0x67};
//        String s = "1aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2";
//        ByteString bb = ByteString.copyFromUtf8(s);
//        bytes = bb.toByteArray();
        int result = getCRC(bytes);
        System.out.println(Integer.toBinaryString(result));
        byte[] bytes1 = new byte[2];
        System.out.println(Integer.toBinaryString(result >> 4));
        bytes1[0] = (byte) (result & 0xFF);
        bytes1[1] = (byte) (result>>8 & 0xFF);
        for (byte b : bytes1)
            System.out.println(Integer.toHexString(b & 0xFF));
    }



    public static byte[] intToBytes2(int n){
        byte[] b = new byte[4];

        for(int i = 0;i < 4;i++)
        {
            b[i]=(byte)(n>>(24-i*8));

        }
        return b;
    }
    /**
     * 计算CRC16校验码
     *
     * @param bytes
     * @return
     */
    public static int getCRC(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return CRC;
    }
}
