import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TestAES {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
        //SecretKey secretKey=keyGenerator.generateKey();
        String pass="azertyazertyazertyaaaaaa"; // 16, 24 or 32 bytes
        SecretKey originalKey = new SecretKeySpec(pass.getBytes(), 0, pass.length(), "AES");
        System.out.println("Secret Key :");
        System.out.println(Base64.getEncoder().encodeToString(originalKey.getEncoded()));
        Cipher cipher=Cipher.getInstance("AES");
        String message="Hello World";
        cipher.init(Cipher.ENCRYPT_MODE,originalKey);
        byte[] bytes = cipher.doFinal(message.getBytes());
        String encryptedMessage= Base64.getEncoder().encodeToString(bytes);
        System.out.println(encryptedMessage);
        System.out.println("********* Decrypt ******************");
        String secret="azertyazertyazertyaaaaaa"; // 16, 24 or 32 bytes
        SecretKey originalKey2 = new SecretKeySpec(secret.getBytes(), 0, secret.length(), "AES");
        Cipher cipher1=Cipher.getInstance("AES");
        cipher1.init(Cipher.DECRYPT_MODE,originalKey2);
        byte[] bytes1 = cipher1.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String decryptedMessage=new String(bytes1, StandardCharsets.UTF_8);
        System.out.println(decryptedMessage);
    }
}
