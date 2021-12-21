package rsa;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class DecryptionTest {
    public static void main(String[] args) throws Exception {
        String encryptedMessage="kd+KyggT72plzcplEsDXgt3Pr5DRj1fMch9csdyrhjVcw4rJjw4TmIpOZahvqyMIZPzR2Xoj3H1GyEtX5vgIFA==";
        String pvk="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEArhtrccn5FtYdZK43QbycJw7Its1egcuIFIAFnPWrnfbgPynU+dvf5brxdL4a16ng0raaTZ9q0JuLnWXOpTiEPwIDAQABAkAw5+DZeLtwT7q38jvxSjKuwfddafi3Ct3xrDhna5bGav8juhyYLBial4362jxnVN/Xyu1Egn1b72fV1dDrlGyBAiEA+lQpt03WVFjty3nILsPh99HAaVVG/OGaABG0oP10J/8CIQCyDTFBj119xfQs27XWsgtBc7usSNoe8LSz0JamDNGjwQIhAKEKXd/nujd/ElOxX7/+EjPsg10AGG/87qaWvV5IT16fAiBu1b9/rBrqnDsvYWWETDitebmtuXvuHrCd7o6YtwoZAQIhAKwRNnzpNNx6/Zvp3Bj1RQk6iMzDiweDibDzy0KTSkqh";
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        PrivateKey privateKey=keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pvk)));
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String originalMessage=new String(decryptedBytes, StandardCharsets.UTF_8);
        System.out.println("Decrypted Message :");
        System.out.println(originalMessage);
        // => Hello from ENSET UH2C
    }
}
