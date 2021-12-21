package hmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class TestHMAC {
    public static void main(String[] args) throws Exception {
        SecretKeySpec secretKeySpec=new SecretKeySpec("123456".getBytes(),"HmacSHA256");
        Mac mac=Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        String message="Hello Mohamed";
        byte[] bytes = mac.doFinal(message.getBytes());
        String hash=DatatypeConverter.printHexBinary(bytes);
        System.out.println(hash);

    }
}
