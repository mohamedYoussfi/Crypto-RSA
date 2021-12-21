package rsa;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignatureTest {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator= KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        PrivateKey privateKey=keyPair.getPrivate();
        PublicKey publicKey=keyPair.getPublic();
        System.out.println("Public Key:");
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        // Output : MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANcloT42EgmE83IX2RkL/hnLiN0DJBaUH0v2Xfc08pKCYEijy7mFasu8XTM+vVOlyAZnZkVnaCa1eOJTmbIsSmUCAwEAAQ==
        Signature signature=Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey,new SecureRandom());
        String messageToSigne="Hello from ENSET UH2C";
        signature.update(messageToSigne.getBytes(StandardCharsets.UTF_8));
        byte[] sgnBytes = signature.sign();
        String strSignature= Base64.getEncoder().encodeToString(sgnBytes);
        System.out.println("Signature:");
        System.out.println(strSignature);
        //Output : IbxZ00aleIYKfOxAe+b0Rg7/Y7HluaC9pTvpeqhF6/TMET5aMkJrEkXHYBabS1UHzpdcGphQbFJ2lLuZryv1UA==
        System.out.println("=============== Signature verification ===============");
        String messageToVerify="Hello from ENSET UH2C";
        String sign2="ZvCyjdejyx9OO9+5DYgqDex2IjyY3fP3p+aa5aMH5S3h9I4nNbrZuZv4oydiVem7rMd4Z4k/NPUL0uNC+1uGmw==";
        String strPublicKey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJbeSzBMJ0y5EHdeJXKhBcyugfGWFGwXrzySHENGlNaS7LhUne+6SW5c//lDGdC7KAw3OZ23PnrlyvbIrmXSX/8CAwEAAQ==";
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        PublicKey publicKey1=keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(strPublicKey)));
        Signature signature1=Signature.getInstance("SHA256withRSA");
        signature1.initVerify(publicKey1);
        signature1.update(messageToVerify.getBytes(StandardCharsets.UTF_8));
        boolean verify = signature1.verify(Base64.getDecoder().decode(sign2));
        System.out.println(verify?"Success Signature verification":"Failure Signature verification");
    }
}
