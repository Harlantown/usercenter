package com.songshu.usercenter.common.utils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-05 上午 11:12
 */
public class StringUtil {

    @Test
    public static String md5(String inputString){
        try {
            if (inputString == null || inputString.length() == 0) {
                throw new RuntimeException();
            }

            // 不能使用魔法值
            String algorithm = "md5";

            MessageDigest encrypt = MessageDigest.getInstance(algorithm);

            byte[] inputBytes = inputString.getBytes("utf-8");

            //得到加密后的二进制串
            byte[] outputBytes = encrypt.digest(inputBytes);

            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, outputBytes);

            int radix = 16;
            String outputString = bigInteger.toString(radix).toUpperCase();

            return outputString;

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
            throw new RuntimeException();
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            throw new RuntimeException();
        }

//        String outputString = byte2hex(outputBytes);

//        System.out.println(outputString);
    }

    public String byte2hex(byte[] byteArray) {

        String hs = "";
        String stmp = "";
        for (int n = 0; n < byteArray.length; n++) {
            stmp = (java.lang.Integer.toHexString(byteArray[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

}
