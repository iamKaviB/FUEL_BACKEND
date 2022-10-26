package com.fuel.ead.service;

import java.util.Base64;

public class PasswordHashing {

    public static String encrypt(String plain){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(plain.getBytes());
    }
    public static  String decrypt(String encrypted){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encrypted));

    }
}
