import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;

public class TestSecure {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("youssfi.jks");
        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(fileInputStream,"123456".toCharArray());
        String alias="youssfi";
        Key key=keyStore.getKey(alias,"123456".toCharArray());
        KeyPair keyPair=null;
        if(key instanceof PrivateKey){
            Certificate certificate=keyStore.getCertificate("youssfi");
            System.out.println("*****************************");
            System.out.println(certificate.toString());
            System.out.println("*****************************");
            PublicKey publicKey=certificate.getPublicKey();
            PrivateKey privateKey= (PrivateKey) key;
            System.out.println("Public key ");
            System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            System.out.println("Private key");
            System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            keyPair=new KeyPair(publicKey, privateKey);
        }

        PublicKey publicKey=keyPair.getPublic();
        String message="azerty";
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] bytes = cipher.doFinal(message.getBytes());
        String encyptedMessage=Base64.getEncoder().encodeToString(bytes);
        System.out.println("Encypted:");
        System.out.println(encyptedMessage);
        System.out.println("-------------------------");
        PrivateKey privateKey=keyPair.getPrivate();
        Cipher cipher1=Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes1 = cipher1.doFinal(Base64.getDecoder().decode(encyptedMessage));
        String decrypted=new String(bytes1, StandardCharsets.UTF_8);
        System.out.println("Decrypted :");
        System.out.println(decrypted);
        System.out.println("========================================================");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream fis=new FileInputStream("publickey.cert");
        Certificate certificate = cf.generateCertificate(fis);
        PublicKey pk=certificate.getPublicKey();
        System.out.println("******************* PK ***********************************");
        System.out.println(Base64.getEncoder().encodeToString(pk.getEncoded()));
        System.out.println("******************* PK ***********************************");
        System.out.println("******************* Certificate ********************************");
        System.out.println(certificate.toString());

    }
}
