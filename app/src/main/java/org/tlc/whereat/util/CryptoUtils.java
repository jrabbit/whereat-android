package org.tlc.whereat.util;

import org.whispersystems.curve25519.JCESecureRandomProvider;

import static android.util.Base64.encode;

public class CryptoUtils {
//    public CryptoUtils(){}

    public static String makeRandomId(){
        JCESecureRandomProvider srp = new JCESecureRandomProvider();
        byte[] idBytes = new byte[32];
        srp.nextBytes(idBytes);
//        return new String(idBytes);
        return new String(encode(idBytes,0));
    }

}
