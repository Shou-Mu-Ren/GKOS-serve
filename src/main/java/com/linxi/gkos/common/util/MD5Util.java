package com.linxi.gkos.common.util;
import java.security.MessageDigest;

public class MD5Util {

    public static final String KEY_MD5 = "MD5";

    /***
     * MD5加密（生成唯一的MD5值）
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        byte[] bytes = md5.digest();
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            String tmp = Integer.toHexString(bytes[i] & 0xFF);
            if (tmp.length() == 1) {
                result += "0" + tmp;
            } else {
                result += tmp;
            }
        }
        return result;
    }

}