//
// Created by Administrator on 2015/9/17.
//

#include <android/log.h>

#ifndef ANDROIDSTUDIO_LOG_H_H
#define ANDROIDSTUDIO_LOG_H_H

#define  ANDROID_LOG_TAG "Native"

#ifndef LOGV
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, ANDROID_LOG_TAG, __VA_ARGS__)
#endif
#ifndef LOGD
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , ANDROID_LOG_TAG, __VA_ARGS__)
#endif
#ifndef LOGI
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO   , ANDROID_LOG_TAG, __VA_ARGS__)
#endif
#ifndef LOGW
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN   , ANDROID_LOG_TAG, __VA_ARGS__)
#endif
#ifndef LOGE
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , ANDROID_LOG_TAG, __VA_ARGS__)
#endif
#ifndef IN
#define IN
#endif
#ifndef OUT
#define OUT
#endif

#endif //ANDROIDSTUDIO_LOG_H_H
