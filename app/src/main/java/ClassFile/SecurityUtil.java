package ClassFile;
import java.security.*;

public class SecurityUtil {
    public String encryptSHA256(String str){
        String sha256 = "";

        try{
            MessageDigest md = MessageDigest.getInstance( "SHA-256" );
            md.update(str.getBytes());

            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer(  );

            for(int i = 0; i<byteData.length; i++){
                sb.append(Integer.toString( (byteData[i]&0xff)+0x100,16 ).substring( 1 ));
            }
            sha256 = sb.toString();
        }

        catch (NoSuchAlgorithmException e) {
            System.out.println( "Encrypt Error - NoSuchAlgorithmException" );
            sha256 = null;
        }
        return sha256;
    }
}
