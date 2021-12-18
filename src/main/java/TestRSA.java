import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class TestRSA {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        byte[] pubKeyBytes=publicKey.getEncoded();
        System.out.println("Public key=>"+Base64.getEncoder().encodeToString(pubKeyBytes));

        byte[] privateKeyBytes=privateKey.getEncoded();
        System.out.println("Private key=>"+Base64.getEncoder().encodeToString(privateKeyBytes));

        System.out.println("====================================");
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        PrivateKey privateKey1=keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        PublicKey publicKey1=keyFactory.generatePublic(new X509EncodedKeySpec(pubKeyBytes));
        String message ="Hello Mohamed";
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey1);
        byte[] cryptedBytes = cipher.doFinal(message.getBytes());
        String encryptedMessage= Base64.getEncoder().encodeToString(cryptedBytes);
        System.out.println(encryptedMessage);

        System.out.println("------------------------");

        Cipher cipher1=Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE,privateKey1);
        byte[] bytes = cipher1.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String decryptedMessage=new String(bytes,StandardCharsets.UTF_8);
        System.out.println(decryptedMessage);

        System.out.println("----------------------");
        Signature signature=Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey, new SecureRandom());
        String messageToSign="12345678";
        byte[] messageToSignBytes = messageToSign.getBytes();
        signature.update(messageToSignBytes);
        byte[] sigBytes = signature.sign();
        System.out.println(Base64.getEncoder().encodeToString(sigBytes));

        System.out.println("-----------------------");
        Signature signature1 = Signature.getInstance("SHA256withRSA");
        signature1.initVerify(publicKey);
        //messageToSign+="-";
        signature1.update(messageToSign.getBytes());
        boolean result = signature1.verify(sigBytes);
        System.out.println(result);

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        String pkHex="30820154020100300D06092A864886F70D01010105000482013E3082013A020100024100B401A9941EA64A503C310E5A17FF0AFF9BAE33129FF33E6E489FE44175ABB51CA8ADEE710D21636C45BF23D1A10911FB52F530DAEE3C4748C894D6AD80595EF30203010001024100A49F609735F09EC602465AF9CA3855B6AE8A01EB669CEEDBAD4F8D50D06CD8946F4C1352A060D8157DCB2F5B6A67A53BB08F083D17648D46359AA3ACB9CC0B01022100FCBA3E8139026B433D486C4B60619BA009B13A880D0AB0E9049B80ED0D4B21D7022100B6565BBF7F28FAA96B01A9D40CB3F852FE0BD15E5CBE9ECD5EB0EC7A3508C04502201C8751A2F8B2833535E7C501C5417F75D362443F3369AF8ABE50677F5AEECE4502203CDAEBBA2905934B5204771AE7B6752DB5CE59ED6584D55973BE16DEDEAE7205022019A244DF00E5D385E08356C0C28CEB338CCA48ED4817DDAF43C6353F30FB6C99";
        byte[] pkBytes= DatatypeConverter.parseHexBinary(pkHex);
        PrivateKey pk=keyFactory.generatePrivate(new PKCS8EncodedKeySpec(pkBytes));
        RSAPrivateCrtKey privk = (RSAPrivateCrtKey)pk;
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
        PublicKey myPublicKey = keyFactory.generatePublic(publicKeySpec);

        System.out.println("============= PUBK ===================");
        System.out.println(DatatypeConverter.printHexBinary(myPublicKey.getEncoded()));
        System.out.println("============= PRIK ===================");
        System.out.println(DatatypeConverter.printHexBinary(privk.getEncoded()));

    }
}
