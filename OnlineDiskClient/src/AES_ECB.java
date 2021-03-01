import java.util.Arrays;
//import java.util.Base64;
//import java.util.Base64.Decoder;
//import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Administrator
 *
 */
public class AES_ECB {

    // 加密
    public static byte[] encrypt(byte[] sSrc, String sKey) throws Exception {
    	
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc);

        return encrypted;//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static byte[] decrypt(byte[] sSrc, String sKey) throws Exception {
    	//Decoder decoder = Base64.getDecoder();
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //byte[] encrypted1 = decoder.decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(sSrc);
                //String originalString = new String(original,"utf-8");
                return original;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
       
         //此处使用AES-128-ECB加密模式，key需要为16位。
        
    	String cKey = "1234567890123456";
        // 需要加密的字串
    	byte[] cSrc = new byte[1024];
    	for (int j = 0; j<cSrc.length; j++){  
    		cSrc[j] = (byte) (j % 16);
    		}  
        System.out.println(cSrc);
        // 加密
        byte[] enString = AES_ECB.encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + Arrays.toString(enString));

        // 解密
        byte[] DeString = AES_ECB.decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + Arrays.toString(DeString));
    }
}
