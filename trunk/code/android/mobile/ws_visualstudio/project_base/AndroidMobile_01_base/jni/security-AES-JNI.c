#include <string.h>
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>
#include "security-AES-JNI.h"
#include <stdlib.h>
#include "malloc.h"

#ifndef LOGV
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, "TestSecurity", __VA_ARGS__)
#endif
#ifndef LOGD
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , "TestSecurity", __VA_ARGS__)
#endif
#ifndef LOGI
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO   , "TestSecurity", __VA_ARGS__)
#endif
#ifndef LOGW
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN   , "TestSecurity", __VA_ARGS__)
#endif
#ifndef LOGE
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , "TestSecurity", __VA_ARGS__)
#endif
#ifndef IN
#define IN
#endif
#ifndef OUT
#define OUT
#endif

extern int Encrypt(char *Msg, char *Key, char *Cipher, int length);
extern int Decrypt(char *Cipher, char *Key, char *Msg, int length);
#define MSG_DEFAULT_PARTNER_LENGTH  16
#define MSG_DEFAULT_SELLER_LENGTH  18
#define MSG_PRIVATE_KEY_LENGTH  812
#define MSG_PUBLIC_KEY_LENGTH  216
const unsigned char key[] = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
char msgDefaultPartner[] = {98, -118, -124, 76, -41, -42, 82, 7, -87, 109, -59,
                            -113, -124, 106, 49, 121};
char msgDefaultSeller[] = {44, -46, -59, -40, -100, 36, -94, -3, 71, -104, -34,
                           50, -77, 18, -74, -70, 115, -41, 71, -25, 1, 49, -64,
                           -50, 39, -111, 17, 26, -126, -20, 0, 24};
char msgPrivateKey[] = {-108, 50, -82, -49, -21, 67, -1, 77, 48, 47, -83, -36,
                        99, 82, 115, 17, 122, 48, 77, -116, -106, 32, -94, -78, -30, 7, 76, 4,
                        91, 32, 13, 22, -37, -120, -122, -5, -94, 30, -115, -101, 54, 24, -32,
                        -32, 110, 105, -99, -55, -102, 43, -123, -94, -49, 65, 118, 111, 1, 62,
                        -61, 72, 104, 47, 41, 117, 61, -92, -93, -13, -64, 15, 0, 22, 15, 122,
                        41, 99, -119, 124, 24, 29, -63, -79, 36, -119, 1, -22, -46, -30, 114,
                        92, 91, 62, -52, -27, -47, -120, -57, 63, 70, 3, 105, 26, 62, 72, 114,
                        -28, -26, 11, 87, 106, 35, 23, 17, -30, 85, 105, -24, -52, -36, 33, -43,
                        106, -22, -45, 82, 51, -19, -95, -25, 77, 112, -88, 112, 90, -80, 103,
                        38, -56, -25, 84, -1, -92, -119, -83, -99, -57, 93, 66, 63, 104, 98,
                        -115, 15, 75, -19, 5, 123, -86, -44, -62, 75, -66, 68, 104, 93, 11,
                        -100, -101, 25, 36, 67, -34, 109, 59, 52, -17, 117, -128, 99, 92, -84,
                        16, 38, 66, 57, -15, 123, -23, 4, 46, 9, 85, 125, -79, -54, -71, -78,
                        -60, -23, -80, -67, 19, -61, 39, 67, -111, -97, 127, -98, 82, 67, 35,
                        -126, -75, -6, -100, -22, 76, -58, 44, 79, 17, -3, 86, -122, 107, 111,
                        -25, -75, 33, 42, 46, -5, -123, -45, 81, 84, 97, -70, 49, -6, 5, 40,
                        -53, 109, -59, 88, -54, -13, -52, 26, 122, 83, -2, -11, -91, 38, -121,
                        123, 123, 52, -108, 44, 82, 127, 49, -65, 28, 53, -4, 86, -55, -78, 97,
                        14, -48, -65, -88, 123, 43, 103, 91, -98, -8, 13, -105, -76, 88, 20,
                        -49, -112, 96, -95, 117, -54, 109, 111, 42, -88, 36, -128, -17, -9, -30,
                        -79, -60, -87, 119, -101, 31, 35, 126, 68, 110, -63, -107, 39, -32, -43,
                        -33, -66, -70, 88, -121, 111, 33, -112, -9, 38, 78, 82, 75, -15, 68,
                        -20, -116, 46, -75, 112, 1, 113, -124, 48, -119, 116, -42, -74, 51, 94,
                        -61, 105, -19, 1, 115, 108, -89, 44, -115, -18, 18, 71, -28, 112, 78,
                        -37, -56, -9, -81, 1, -62, -35, -12, 18, 9, -8, -19, 87, -71, -127, 126,
                        18, 73, 98, 33, 83, 17, -91, 49, -79, 20, 80, 114, 1, -108, -78, 36, 66,
                        -75, 122, -6, 13, 63, 77, -17, 23, -110, 125, 106, -98, 32, -104, -14,
                        -117, 73, 11, 83, 35, -21, 42, 54, -89, -99, -124, -14, -69, -77, 108,
                        -108, 58, 82, 90, 51, -105, 17, 118, 88, -99, 122, 124, 91, 29, 74, 127,
                        -85, -87, -77, -104, 76, 86, 72, -122, -54, 104, 17, 33, -43, -50, -84,
                        102, 41, -9, -48, -89, 82, 33, -104, 111, -15, -60, -60, -122, -41, 45,
                        -75, -77, 96, 58, 37, -61, 100, -56, 63, -17, 127, -97, -97, -56, 93,
                        -113, 38, 63, 28, 87, -123, 27, 92, 69, 105, -59, -126, 64, -21, 6, -74,
                        -16, -128, -90, 123, -86, 110, 90, -53, -31, -7, -115, 2, 87, -76, -4,
                        -117, 45, 12, -67, -64, -71, 11, 92, 90, -26, 8, 123, 29, -78, 110, 2,
                        -72, -92, -56, 86, -118, -103, 30, 3, 46, -11, 112, -89, 18, -46, -105,
                        20, -123, -108, 2, -124, 86, 118, 7, 105, 51, 7, 79, -59, 13, -25, 11,
                        -82, -52, 105, -61, -5, 83, -124, -102, 126, 99, -98, 64, 64, 64, -39,
                        -33, 102, 74, 103, 111, -45, 120, -108, 18, -106, 26, -57, -62, -63, -3,
                        72, -62, 102, -102, 99, 37, 5, 71, 17, 25, -64, -124, -11, -92, 66, 60,
                        59, 62, 100, 1, 115, -80, -43, -6, -82, 39, 124, 95, 0, 47, -113, -64,
                        -116, 27, 82, 55, 55, 92, -4, -87, 87, -85, -34, 36, 7, -66, 107, -41,
                        66, -97, -115, -35, 59, -45, -44, -64, -104, -18, 99, 63, -11, -107, 28,
                        37, 5, -72, -121, 27, -21, -122, -92, -66, 99, 121, -17, 75, -3, 15,
                        121, -22, 26, -11, -87, -99, 84, -60, -43, 84, -84, -63, 19, 2, 16, 62,
                        43, 86, -81, -9, 78, 29, 121, 15, -70, 69, -84, -72, -90, -54, 99, -23,
                        -20, -100, -16, -28, 18, 41, -57, -25, 2, -109, -45, 89, 125, 112, 61,
                        -57, 43, -117, -74, -42, -47, 43, -110, 100, -50, -12, -14, -49, -50,
                        51, 56, -79, 30, -3, 3, 18, -50, 63, -99, -50, 113, -109, 17, -99, 12,
                        20, -78, -97, -96, -112, 65, 92, 30, 105, -80, -43, -66, 122, -83, 10,
                        -113, 106, -27, 58, -60, 105, 99, 65, -22, 0, 31, 8, 52, -92, 15, 2,
                        -72, 56, -47, 124, 5, 70, -48, -13, 67, -30, -57, 91, 30, 99, -13, 12,
                        -90, -23, 15, 38, 122, -13, -6, -92, -44, -42, 83, -87, 57, -78, -9,
                        -61, -48, -43};
char msgPublicKey[] = {3, -39, -78, -91, 105, -123, 15, 34, 53, 48, 101, 106,
                       43, 1, -39, 108, -75, 35, 75, 39, 37, 76, 49, 4, 41, -6, 41, -41, 41,
                       116, 120, 41, -55, 27, -66, 57, 68, 87, 4, 46, -41, -30, -82, 43, 95,
                       23, 103, 104, 55, 95, -110, 30, 44, 94, 85, 46, -72, -70, 90, -80, 42,
                       -54, -83, 10, 6, 68, 7, 51, 28, -1, 86, -56, -103, 93, 73, 28, 100, 36,
                       68, -86, -41, 59, 83, -20, 54, 12, -49, -33, -86, 0, 53, -66, -84, 73,
                       91, -29, 10, -29, 97, 3, 56, -72, 54, -102, 8, -113, -113, -27, -36,
                       114, 111, 104, -40, -23, -40, -32, -100, -107, -18, -105, -109, -59,
                       -120, 58, 15, 54, 124, 78, 0, -77, 4, 31, 3, -103, 21, 96, 86, -41, 19,
                       54, 33, 8, 4, 5, -34, -60, 122, -124, 36, -94, 108, -21, -108, 72, 106,
                       2, -97, -73, 126, 56, -70, -119, 110, -62, 90, -34, -32, -114, -7, 121,
                       -37, 115, 65, -108, 115, 52, 102, 1, 127, 58, 3, -87, -113, 76, -64,
                       -23, 92, -102, 78, -5, -68, -127, -34, 41, -68, 90, 10, -123, 41, 21,
                       -4, 66, -106, 71, -94, -107, -64, 19, 57, 42, -43, -60, -110, -67, 30,
                       113, -112, -84, -63, 111, -109, 84, 96, -35};

#ifdef __cplusplus
extern "C" {
#endif


jbyteArray getDecryptCharArray(JNIEnv *env, jclass thiz, char *pCipher,
                               int length, int targetLength) {
//	LOGI("getArrayLength with length:%d ", length);
//	__android_log_print(ANDROID_LOG_INFO, "TestSecurity",
//			"getArrayLength with length : %d", length);
    char *pKey = (char *) key;
//	char* pResult = malloc(1024);
    int requestLength = request2size(targetLength);
    char *pResult = malloc(requestLength);
    if (!pResult)
        return;
    int flag = Decrypt(pCipher, pKey, pResult, length);
    jbyteArray resultArray = (*env)->NewByteArray(env, targetLength);

    (*env)->SetByteArrayRegion(env, resultArray, 0, targetLength,
                               (jbyte *) pResult);
    if (!pResult)
        free(pResult);
    return resultArray;
}

/*
 * Class:     com_csz_pay_Keys
 * Method:    initDefaultPartner
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jbyteArray JNICALL Java_com_android_demo_jni_JNISecurity_getDefaultPartner(
        JNIEnv *env, jclass thiz) {
int length = sizeof(msgDefaultPartner) / sizeof(msgDefaultPartner[0]);
return getDecryptCharArray(env, thiz, msgDefaultPartner, length,
MSG_DEFAULT_PARTNER_LENGTH);
}

JNIEXPORT jbyteArray JNICALL Java_com_android_demo_jni_JNISecurity_getDefaultSeller(
        JNIEnv *env, jclass thiz) {
int length = sizeof(msgDefaultSeller) / sizeof(msgDefaultSeller[0]);
return getDecryptCharArray(env, thiz, msgDefaultSeller, length,
MSG_DEFAULT_SELLER_LENGTH);
}

JNIEXPORT jbyteArray JNICALL Java_com_android_demo_jni_JNISecurity_getPrivateKey(
        JNIEnv *env, jclass thiz) {
int length = sizeof(msgPrivateKey) / sizeof(msgPrivateKey[0]);
return getDecryptCharArray(env, thiz, msgPrivateKey, length,
MSG_PRIVATE_KEY_LENGTH);
}

JNIEXPORT jbyteArray JNICALL Java_com_android_demo_jni_JNISecurity_getPublicKey(
        JNIEnv *env, jclass thiz) {
int length = sizeof(msgPublicKey) / sizeof(msgPublicKey[0]);
return getDecryptCharArray(env, thiz, msgPublicKey, length,
MSG_PUBLIC_KEY_LENGTH);
}


JNIEXPORT jint JNICALL Java_com_android_demo_jni_JNISecurity_Encrypt(
        JNIEnv *env, jclass thiz, jbyteArray msg, jbyteArray key,
        jbyteArray cipher, jint length) {
jbyte *pMsg = (jbyte * )(*env)->GetByteArrayElements(env, msg, 0);
jbyte *pKey = (jbyte * )(*env)->GetByteArrayElements(env, key, 0);
jbyte *pCipher = (jbyte * )(*env)->GetByteArrayElements(env, cipher, 0);

if (!pMsg || !pKey || !pCipher) {
return -1;
}
int flag = Encrypt(pMsg, pKey, pCipher, length);
(*env)->ReleaseByteArrayElements(env, msg, pMsg, 0);
(*env)->ReleaseByteArrayElements(env, key, pKey, 0);
(*env)->ReleaseByteArrayElements(env, cipher, pCipher, 0);
return flag;
}

JNIEXPORT jint JNICALL Java_com_android_demo_jni_JNISecurity_Decrypt(
        JNIEnv *env, jclass thiz, jbyteArray cipher, jbyteArray key,
        jbyteArray result, jint length) {
jbyte *pCipher = (jbyte * )(*env)->GetByteArrayElements(env, cipher, 0);
jbyte *pKey = (jbyte * )(*env)->GetByteArrayElements(env, key, 0);
jbyte *pResult = (jbyte * )(*env)->GetByteArrayElements(env, result, 0);

if (!pResult || !pKey || !pCipher) {
return -1;
}
int flag = Decrypt(pCipher, pKey, pResult, length);
(*env)->ReleaseByteArrayElements(env, result, pResult, 0);
(*env)->ReleaseByteArrayElements(env, key, pKey, 0);
(*env)->ReleaseByteArrayElements(env, cipher, pCipher, 0);
return flag;
}

#ifdef __cplusplus
}
#endif

