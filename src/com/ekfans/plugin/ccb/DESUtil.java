package com.ekfans.plugin.ccb;

import javax.crypto.Cipher;
import java.security.Key;

public class DESUtil {
    public static final String ALGORITHM = "DES";


    /**
     * 【对外接口】 使用UTF8编码进行解密
     *
     * @param keyStr          密钥字符串
     * @param encryptedString 待解密字符串
     * @return 解密结果
     * @throws Exception
     */
    public static String DecryptUFT8(String keyStr, String encryptedString) throws Exception {
        return decryptUTF8(getKey(keyStr), encryptedString);
    }

    /**
     * 【对外接口】 使用UTF8编码进行加密
     *
     * @param keyStr    密钥字符串
     * @param srcString 待加密字符串
     * @return 解密结果
     * @throws Exception
     */
    public static String EncryptUTF8(String keyStr, String srcString) throws Exception {
        return encryptUTF8(getKey(keyStr), srcString);
    }


    @SuppressWarnings("restriction")
    private static Key getKey(String strKey) throws Exception {
        //Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return getKey(strKey.getBytes());
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp 构成该字符串的字节数组
     * @return 生成的密钥
     * @throws Exception
     */
    private static Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key;
        key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }


    /**
     * 根据密匙进行DES加密,使用UTF-8编码
     *
     * @param key  密匙对象
     * @param info 要加密的信息
     * @return String 加密后的信息
     */
    private static String encryptUTF8(Key key, String srcString) throws Exception {
        // 定义要生成的密文
        byte[] cipherByte = null;

        // 得到加密/解密器
        Cipher c1 = Cipher.getInstance(ALGORITHM);
        // 用指定的密钥和模式初始化Cipher对象
        // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
        c1.init(Cipher.ENCRYPT_MODE, key);
        // 对要加密的内容进行编码处理,
        cipherByte = c1.doFinal(srcString.getBytes("UTF-8"));

        // 返回密文的十六进制形式
        return byte2hex(cipherByte);
    }


    /**
     * 根据密匙进行DES解密 UTF8编码
     *
     * @param key   密匙对象
     * @param sInfo 要解密的密文
     * @return String 返回解密后信息
     */
    private static String decryptUTF8(Key key, String encryptedString)
            throws Exception {

        byte[] cipherByte = null;
        // 得到加密/解密器
        Cipher c1 = Cipher.getInstance(ALGORITHM);
        // 用指定的密钥和模式初始化Cipher对象
        c1.init(Cipher.DECRYPT_MODE, key);
        // 对要解密的内容进行编码处理
        cipherByte = c1.doFinal(hex2byte(encryptedString));

        // return byte2hex(cipherByte);
        return new String(cipherByte, "UTF-8");
    }

    /**
     * 将二进制转化为16进制字符串
     *
     * @param b 二进制字节数组
     * @return String
     */
    private static String byte2hex(byte[] b) {

        if (null == b) {
            return null;
        }

        StringBuffer hs = new StringBuffer("");
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    /**
     * 十六进制字符串转化为2进制
     *
     * @param hex
     * @return
     */
    private static byte[] hex2byte(String hex) throws Exception {
        if (null == hex || hex.length() % 2 != 0) {
            System.out
                    .println("[ERROR]DESUtil:string to hex is null or invalid length!");
            throw new Exception();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

}