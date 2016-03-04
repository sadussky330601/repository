LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# Here we give our module name and source file(s)

LOCAL_MODULE := Kevin_Security

SRCS := Kevin_SecurityAES.c  \
		Kevin_SecurityAES_Jni.c

LOCAL_SRC_FILES := $(SRCS)

LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog

include $(BUILD_SHARED_LIBRARY)
