package rsa;

import com.sun.security.cert.internal.x509.X509V1CertImpl;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;

public class KeyPairFromJKS {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("youssfi.jks");
        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
        String keystorePassord="123456";
        keyStore.load(fileInputStream,keystorePassord.toCharArray());
        String alias="youssfi";
        Key key = keyStore.getKey(alias, keystorePassord.toCharArray());
        PrivateKey privateKey=(PrivateKey) key;
        Certificate certificate=keyStore.getCertificate("youssfi");
        PublicKey publicKey=certificate.getPublicKey();
        System.out.println("======================== /Certificate ===================");
        System.out.println("Private Key :");
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println("Private Key :");
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));

    }
}
