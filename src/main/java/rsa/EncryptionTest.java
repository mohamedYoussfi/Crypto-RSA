package rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionTest {
    public static void main(String[] args) throws Exception {
        String pbcKey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK4ba3HJ+RbWHWSuN0G8nCcOyLbNXoHLiBSABZz1q5324D8p1Pnb3+W68XS+Gtep4NK2mk2fatCbi51lzqU4hD8CAwEAAQ==";
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        PublicKey publicKey=keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(pbcKey)));
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        String messageToEncrypt="Hello from ENSET UH2C";
        byte[] encryptedDataBytes = cipher.doFinal(messageToEncrypt.getBytes());
        String encryptedDataBase64=Base64.getEncoder().encodeToString(encryptedDataBytes);
        System.out.println(encryptedDataBase64);
        // => fBgut5tpQGRwRhJw2ai+GtCxXMYyw/SuNkK+c3cnXN3e4fGxRqzuFtFwKUR3gs8UJWCnAdpUHRqwpVa6u6jljw==

    }
}
