package aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SymetricCryptoAES {
    public static void main(String[] args) throws Exception {
        System.out.println("==================== AES Encryption =====================");
        KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
        SecretKey secretKey1=keyGenerator.generateKey(); // Generate random Secret Key
        String secret="azerty_azerty_azerty_aze"; // 16, 24 or 32 bytes
        String secretBase64=Base64.getEncoder().encodeToString(secret.getBytes());
        SecretKey secretKey2=new SecretKeySpec(secret.getBytes(),0,secret.length(),"AES");
        System.out.println("Secret 1 in Base64 :");
        System.out.println(Base64.getEncoder().encodeToString(secretKey1.getEncoded()));
        System.out.println("Secret 2 in Base64 :");
        System.out.println(Base64.getEncoder().encodeToString(secretKey2.getEncoded()));
        System.out.println("Secret 2 UTF-8 :");
        System.out.println(new String(secretKey2.getEncoded(),StandardCharsets.UTF_8));
        String messageToEncrypt="Hello from ENSET UH2C";
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey2);
        byte[] encryptedMessageBytes = cipher.doFinal(messageToEncrypt.getBytes());
        System.out.println("Encrypted Message in Base64 :");
        String encryptedMessage=Base64.getEncoder().encodeToString(encryptedMessageBytes);
        System.out.println(encryptedMessage);
        System.out.println("==================== AES Decryption =====================");

        String receivedEncryptedMessage="mWCSG1uMDT4rD6Tc73XCem5keVt6MEYEaw3CHtVewss=";
        String sharedEncodedSecret="YXplcnR5X2F6ZXJ0eV9hemVydHlfYXpl";
        String sharedDecodedSecret = new String(Base64.getDecoder().decode(sharedEncodedSecret));
        System.out.println("Shared decoded secret :");
        System.out.println(sharedDecodedSecret);
        SecretKey sharedSecretKey=new SecretKeySpec(sharedDecodedSecret.getBytes(),0,sharedDecodedSecret.length(),"AES");
        Cipher cipher2=Cipher.getInstance("AES");
        cipher2.init(Cipher.DECRYPT_MODE,sharedSecretKey);
        byte[] decryptedBytes = cipher2.doFinal(Base64.getDecoder().decode(receivedEncryptedMessage));
        String decryptedMessage=new String(decryptedBytes,StandardCharsets.UTF_8);
        System.out.println("Decrypted Message in Base64 :");
        System.out.println(decryptedMessage);
    }
}
