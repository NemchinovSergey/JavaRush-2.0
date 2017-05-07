package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject("test string");
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true
    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(byteArrayOutputStream.toByteArray());
        byte[] hashBytes = md.digest();
        StringBuilder hashMD5 = new StringBuilder();

        for (byte hashByte : hashBytes) {
            String s = Integer.toHexString(0x000000ff & hashByte); // 0xff => trim int to unsigned byte
            s = (s.length() == 1) ? "0" + s : s;
            hashMD5.append(s);
        }
        return hashMD5.toString().equals(md5);
    }
}
