
import java.security.*;  
import javax.crypto.*;  
  
import java.util.Base64.Encoder;
import java.util.Base64;
import java.util.Base64.Decoder;
  
public class Triple_DES {  
    public static String DES = "DESede"; // optional value AES/DES/DESede  
      
    public static String CIPHER_ALGORITHM = "DESede"; // optional value AES/DES/DESede  
      
  
    public static Key getSecretKey(String key) throws Exception
    {  
        SecretKey securekey = null;  
        if(key == null){
            key = "";
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance(DES);  
        keyGenerator.init(new SecureRandom(key.getBytes()));  
        securekey = keyGenerator.generateKey();
        return securekey;  
    }  

    public static String encrypt(String data,String key) throws Exception {
    	Encoder encoder = Base64.getEncoder();
        SecureRandom sr = new SecureRandom();
        Key securekey = getSecretKey(key);  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
        byte[] bt = cipher.doFinal(data.getBytes());  
        String strs = encoder.encodeToString(bt);//BASE64Encoder().encode(bt);  
        return strs;  
    }
      
      
    public static String decrypt(String message,String key) throws Exception{ 
    	Decoder decoder = Base64.getDecoder();
        SecureRandom sr = new SecureRandom();  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        Key securekey = getSecretKey(key);  
        cipher.init(Cipher.DECRYPT_MODE, securekey,sr);  
        byte[] res = decoder.decode(message);  
        res = cipher.doFinal(res);  
        return new String(res);  
    }  
      
   
}  