import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ASymetricCrypto {
    public static void main(String[] args) throws Exception {

        System.out.println("================ Encryption =======================");
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        String pvk="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEApgoxrTumGC4LwvB+pWEGe3UC1aio0J/u6L2nytnJy3r1TlYM4epKcJW+rAalSqG6UjaVwLBLJ2q8d+fBvcf5HwIDAQABAkBZU76qDh8rfwpA1BWub2aHYMonnFdF9eUYPYBZ95VC7H/9Mf5KkwT1591FK+8zVBbFn2qRSndUr7/jL98jTSpBAiEA+GjkdB8ZlBVGMbeKQZOnsg781GDf6NcLvceKUB5kc7ECIQCrHP0b17s1lmwBFzZvxeGE6uARrFW+x1aNv4qwyGF9zwIhAKaVSOaqWG/a2GjMyZVyXFnm/Ru7H0y4sXICFznlN73xAiAlfuez5zUdzAWEw6ppGcqOTs2k8cekU5gS3asti+tZHwIgez+hZ1JbX5tzoLrGAr62efeR6aY1QBtTf22/9W98La8=";
        String pbcKey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKYKMa07phguC8LwfqVhBnt1AtWoqNCf7ui9p8rZyct69U5WDOHqSnCVvqwGpUqhulI2lcCwSydqvHfnwb3H+R8CAwEAAQ==";
        PublicKey publicKey1=keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(pbcKey)));
        String messageToEncrypt="Hello from ENSET UH2C";
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey1);
        byte[] encryptedMessageBytes = cipher.doFinal(messageToEncrypt.getBytes());
        System.out.println("Encrypted Message in Base 64");
        System.out.println(Base64.getEncoder().encodeToString(encryptedMessageBytes));
        System.out.println("================ Decryption =======================");
        String prvKey="MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAmt7l+tiWr58cYAG6TQE5kie9xifewifgB0WebJLz/uzKDhZPV11S8NwYsdLrAMzjNCRnP59T+Plr0NNaRpq/uQIDAQABAkEAhvpkWPeyhYhYc7itxfWeVqr6dJ1FQdT9JXlg9WIy9GfR8V2cCtjg+VZQcgTdguZ1p44WOhuE0bdLH4939HcccQIhANi+bvz8SxUviJvCwCt5dnhaVf7rj24z0Q0B49pNMYL1AiEAtuulqoMZZSU3fsdnHfQqn16K451kIVHY/PhBeJRYNzUCIC0nNw4DLU0jku0IHGqyKikWgLXTsxS9PL7d9u7ih/8FAiEAtcGw+50IvtgcLH02mSzkrTbkCwd5VyJc2gBUDdxZS4ECIQDE0vRIbUAa1GJL9lSJ6FghBgJD9XcB3zQsnggyebxXYg==";
        String encryptedMessage="a8jt7MvGuYR3c4JJYvQcMhgnJxNlhcnA6LTWNck6Q5r9WUlXq+r6ZOfUj6ucJ2wo+FF8JLT9CwsGGU5eoVbRkA==";
        KeyFactory keyFactory1=KeyFactory.getInstance("RSA");
        PrivateKey privateKey1=keyFactory1.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(prvKey)));
        Cipher cipher1=Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE,privateKey1);
        byte[] decryptedMessageBytes = cipher1.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String decryptedMessage=new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        System.out.println(decryptedMessage);

    }
}
