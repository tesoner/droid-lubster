package io.sozin.droidlubster.utils;



import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by anake on 19/04/17.
 */
public class Hash {
    private final static String CYPHER_ALGORITHM = "AES";
    // Definición del modo de cifrado a utilizar
    private final static String CYPHER_MODE = "AES/CBC/PKCS5Padding";
    private static final String INITIALIZATION_VECTOR = "0123456789ABCDEF";

    public static String crypt(String original){
        return original;
    }
    public static String decrypt(String hash){
        return hash;
    }
    public static String toB64(String original){
        byte[] hash = Base64.encode(original.getBytes(), Base64.DEFAULT);
        return new String(hash);
    }
    public static String fromB64(String hash){
        byte[] original = Base64.decode(hash, Base64.DEFAULT);
        return new String(original);
    }
    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA)


    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea cifrar
     * @param key la llave en tipo String a utilizar
     * @param iv el vector de inicialización a utilizar
     * @param original el texto sin cifrar a encriptar
     * @return el texto cifrado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
     */
    public static String crypt(String key, String iv, String original) throws Exception {
        Cipher cipher = Cipher.getInstance(CYPHER_MODE);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), CYPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] hash = cipher.doFinal(original.getBytes());
        return toB64(new String(hash));
    }

    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea descifrar
     * @param key La llave en tipo String a utilizar
     * @param iv El vector de inicialización a utilizar
     * @param hash El texto cifrado en modo String
     * @return el texto desencriptado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException
     */
    public static String decrypt(String key, String iv, String hash) throws Exception {
        Cipher cipher = Cipher.getInstance(CYPHER_MODE);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), CYPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        byte[] enc = fromB64(hash).getBytes();//decodeBase64(encrypted);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(enc);
        return new String(decrypted);
    }
}
