package com.czh.automation.api;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @PackageName: com.czh.automation.api
 * @ClassName: XbbDigestUtil
 * @Description: XbbDigestUtil/description:
 * @Author: ChengZhiHao
 * @Date: 2023/9/22 10:11
 * @Version: v1.0
 */
public class XbbDigestUtil {

    /**
     * parameter strSrc is a string will be encrypted,
     * parameter encName is the algorithm name will be used.
     * encName dafault to "MD5"
     *
     * @throws UnsupportedEncodingException
     */
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        try {
            byte[] bt = strSrc.getBytes("utf-8");
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            // to HexString
            strDes = bytes2Hex(md.digest());
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException.");
            return null;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String strSrc = "可以加密汉字.Oh,and english";

        System.out.println("Source String:" + strSrc);
        System.out.println("Encrypted String:");
        System.out.println("Use Def:" + XbbDigestUtil.Encrypt(strSrc, null));
        System.out.println("Use MD5:" + XbbDigestUtil.Encrypt(strSrc, "MD5"));
        System.out.println("Use SHA-1:" + XbbDigestUtil.Encrypt(strSrc, "SHA-1"));
        System.out.println("Use SHA-256:" + XbbDigestUtil.Encrypt(strSrc, "SHA-256"));
    }

}
