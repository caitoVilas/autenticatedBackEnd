package com.caito.util;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Component
public class KeyGeneratedUtility {

    public static KeyPair generateRsaKey(){
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }catch (Exception e){
            throw new IllegalStateException();
        }
        return keyPair;
    }
}
