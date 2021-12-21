package rsa;

import java.security.*;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("================ KeyPair =======================");
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey=keyPair.getPrivate();
        System.out.println("==== Private key =====");
        String privateKeyBase64= Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println(privateKeyBase64);
        System.out.println("==== Public Key ======");
        PublicKey publicKey=keyPair.getPublic();
        String publicKeyBase64=Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println(publicKeyBase64);
        /*
        ==== Private key =====
MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEArhtrccn5FtYdZK43QbycJw7Its1egcuIFIAFnPWrnfbgPynU+dvf5brxdL4a16ng0raaTZ9q0JuLnWXOpTiEPwIDAQABAkAw5+DZeLtwT7q38jvxSjKuwfddafi3Ct3xrDhna5bGav8juhyYLBial4362jxnVN/Xyu1Egn1b72fV1dDrlGyBAiEA+lQpt03WVFjty3nILsPh99HAaVVG/OGaABG0oP10J/8CIQCyDTFBj119xfQs27XWsgtBc7usSNoe8LSz0JamDNGjwQIhAKEKXd/nujd/ElOxX7/+EjPsg10AGG/87qaWvV5IT16fAiBu1b9/rBrqnDsvYWWETDitebmtuXvuHrCd7o6YtwoZAQIhAKwRNnzpNNx6/Zvp3Bj1RQk6iMzDiweDibDzy0KTSkqh
==== Public Key ======
MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK4ba3HJ+RbWHWSuN0G8nCcOyLbNXoHLiBSABZz1q5324D8p1Pnb3+W68XS+Gtep4NK2mk2fatCbi51lzqU4hD8CAwEAAQ==
         */
    }
}
