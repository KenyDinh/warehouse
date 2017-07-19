/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *
 * @author KenyDinh
 */
public class HashContent {

    private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-#&$<=>%@~?/\\|";
    private static final String BASE_REG = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789=+/";
    private static final Integer LENGTH_REG = BASE_REG.length();
    private static String HASH_REG;
    private static String KEY_REG;
    private static final int LENGTH_KEY = 20;

    public static String getEncryptContent(String content) {
        return vigenereEncrypt(getEncodeBase64(content.trim()));
    }

    public static String getDecryptContent(String content) {
        if (content.length() < LENGTH_KEY + LENGTH_REG) {
            return null;
        }
        return getDecodeBase64(vigenereDecrypt(content.trim()));
    }

    // ----------function support encrypt----------//
    private static String addTwoChar(String t, String k) {
        int index_t = BASE_REG.indexOf(t);
        int index_k = BASE_REG.indexOf(k);
        int index_result = (index_t + index_k) % LENGTH_REG;
        return String.valueOf(HASH_REG.charAt(index_result));
    }

    private static String minusTwoChar(String t, String k) {
        int index_t = HASH_REG.indexOf(t);
        int index_k = BASE_REG.indexOf(k);
        int index_result = changeKey(index_t - index_k) % LENGTH_REG;
        return String.valueOf(BASE_REG.charAt(index_result));
    }

    private static int changeKey(int key) {
        while (key < 0) {
            key += LENGTH_REG;
        }
        return key;
    }

    // ----------function get random hash----------//
    private static String getRandomString(String base, int len) {
        StringBuilder hash = new StringBuilder();
        if (len > base.length()) {
            return hash.toString();
        }
        while (hash.length() < len) {
            String s = String.valueOf(getRandomChar(base));
            base = base.replace(s, "");
            hash.append(s);
        }
        return hash.toString();
    }

    private static Character getRandomChar(String base) {
        int base_len = base.length();
        int index = (int) (Math.random() * base_len);
        return base.charAt(index);
    }

    // ----------base64 function----------//
    private static String getEncodeBase64(String content) {
        String encode = null;
        try {
            encode = Base64.getEncoder().encodeToString(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
        }
        return encode;
    }

    private static String getDecodeBase64(String encode) {
        String decode = null;
        try {
            byte[] b_decode = Base64.getDecoder().decode(encode);
            decode = new String(b_decode);
        } catch (IllegalArgumentException ex) {
        }
        return decode;
    }

    // ----------end base64 function----------//
    // ----------Vigenere encrypt text----------//
    private static String vigenereEncrypt(String text) {
        HASH_REG = getRandomString(BASE_CHAR, LENGTH_REG);
        KEY_REG = getRandomString(BASE_REG, LENGTH_KEY);
        StringBuilder encript = new StringBuilder();
        String keyText = "";
        int sizeText = text.length();
        int sizeKey = KEY_REG.length();
        if (sizeKey > sizeText) {
            keyText = KEY_REG.substring(0, sizeText);
        } else {
            int n = sizeText / sizeKey;
            int m = sizeText % sizeKey;
            for (int i = 0; i < n; i++) {
                keyText += KEY_REG;
            }
            keyText += KEY_REG.substring(0, m);
        }
        for (int i = 0; i < sizeText; i++) {
            String t = String.valueOf(text.charAt(i));
            String k = String.valueOf(keyText.charAt(i));
            encript.append(addTwoChar(t, k));
        }
        return KEY_REG + HASH_REG + encript.toString();
    }

    // ----------Vigenere decode text----------//
    private static String vigenereDecrypt(String text) {
        KEY_REG = text.substring(0, LENGTH_KEY);
        HASH_REG = text.substring(LENGTH_KEY, LENGTH_KEY + LENGTH_REG);
        text = text.substring(LENGTH_KEY + LENGTH_REG, text.length());
        StringBuilder decode = new StringBuilder();
        String keyText = "";
        int sizeText = text.length();
        int sizeKey = KEY_REG.length();
        if (sizeKey > sizeText) {
            keyText = KEY_REG.substring(0, sizeText);
        } else {
            int n = sizeText / sizeKey;
            int m = sizeText % sizeKey;
            for (int i = 0; i < n; i++) {
                keyText += KEY_REG;
            }
            keyText += KEY_REG.substring(0, m);
        }
        for (int i = 0; i < sizeText; i++) {
            String t = String.valueOf(text.charAt(i));
            String k = String.valueOf(keyText.charAt(i));
            decode.append(minusTwoChar(t, k));
        }
        return decode.toString();
    }
}
