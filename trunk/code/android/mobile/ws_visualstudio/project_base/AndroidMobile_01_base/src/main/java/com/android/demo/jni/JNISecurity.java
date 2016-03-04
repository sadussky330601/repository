package com.android.demo.jni;

import com.android.demo.cons.Cons;

/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 */
public class JNISecurity {

    static {
        System.load(Cons.LIB_SO_SECURITY);
    }


    public synchronized static native int Encrypt(byte[] msg, byte[] key, byte[] cipher, int length);

    public synchronized static native int Decrypt(byte[] cipher, byte[] key, byte[] result, int length);

    public static native byte[] getDefaultPartner();

    public static native byte[] getDefaultSeller();

    public static native byte[] getPrivateKey();

    public static native byte[] getPublicKey();

}
