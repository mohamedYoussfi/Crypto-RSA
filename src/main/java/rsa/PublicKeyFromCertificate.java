package rsa;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Base64;

public class PublicKeyFromCertificate {
    public static void main(String[] args) throws Exception {
        CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
        FileInputStream fileInputStream=new FileInputStream("publickey.cert");
        Certificate certificate=certificateFactory.generateCertificate(fileInputStream);
        PublicKey publicKey=certificate.getPublicKey();
        System.out.println("============ Public Key =================");
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("============== Certificate Verification ===============");
        try {
            certificate.verify(publicKey);
            System.out.println("Success Certificate Verification");
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

    }
}
