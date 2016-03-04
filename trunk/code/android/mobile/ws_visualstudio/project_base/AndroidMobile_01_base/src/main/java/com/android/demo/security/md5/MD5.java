package com.android.demo.security.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Copyright 2013 FARA SFARSIT Team
 *
 * @author Eli.Yng 2013-8-5下午2:51:43
 *         <p/>
 *         一年以后，即1991年，Rivest开发出技术上更为趋近成熟的MD5算法。
 *         它在MD4的基础上增加了"安全-带子"（Safety-Belts）的概念。
 *         虽然MD5比MD4稍微慢一些，但却更为安全。这个算法很明显的由四个和
 *         MD4设计有少许不同的步骤组成。在MD5算法中，信息-摘要的大小和填
 *         充的必要条件与MD4完全相同。Den Boer和Bosselaers曾发现MD5
 *         算法中的假冲突（Pseudo-Collisions），但除此之外就没有其他
 *         被发现的加密后结果了。Van Oorschot和Wiener曾经考虑过一个在
 *         散列中暴力搜寻冲突的函数（Brute-Force Hash Function），
 *         而且他们猜测一个被设计专门用来搜索MD5冲突的机器（这台机器在1994年的
 *         制造成本大约是一百万美元）可以平均每24天就找到一个冲突。但单从1991年
 *         到2001年这10年间，竟没有出现替代MD5算法的MD6或被叫做其他什么名
 *         字的新算法这一点，我们就可以看出这个瑕疵并没有太多的影响MD5的安全性。
 *         上面所有这些都不足以成为MD5的在实际应用中的问题。并且，由于MD5算法的
 *         使用不需要支付任何版权费用的，所以在一般的情况下（非绝密应用领域。但
 *         即便是应用在绝密领域内，MD5也不失为一种非常优秀的中间技术），MD5
 *         怎么都应该算得上是非常安全的了。在一些初始化处理后，MD5以512位分
 *         组来处理输入文本，每一分组又划分为16个32位子分组。算法的输出由四个
 *         32位分组组成，将它们级联形成一个128位散列值。首先填充消息使其长度
 *         恰好为一个比512位的倍数仅小64位的数。填充方法是附一个1在消息后面，
 *         后接所要求的多个0，然后在其后附上64位的消息长度（填充前）。这两步的作
 *         用是使消息长度恰好是512位的整数倍（算法的其余部分要求如此），同时
 *         确保不同的消息在填充后不相同
 */
public class MD5 {
    private static String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "A", "B", "C", "D", "E", "F",};

    // 将原始password加密成Md5格式的password
    public static String encodeByMd5(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] results = md5.digest(password.getBytes());
        // [!!??w?M+??,3?]
        return byteArrayToHexString(results);
    }

    // 将byte[]字节数组转成16进制字符串
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            sb.append(byteToHexChar(byteArray[i]));
        }
        return sb.toString();
    }

    // 将byte字节转成16进制字符串，例如(重点)
    private static String byteToHexChar(byte b) {// 0-255共256个
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hex[d1] + hex[d2];
    }

    public void testMd5() throws Exception {
        System.out.println(encodeByMd5("1212121"));
    }
}
