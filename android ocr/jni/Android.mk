LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# Tegra optimized OpenCV.mk
include F:\adt-bundle-windows-x86_64-20140702\OpenCV-2.4.9-android-sdk\sdk\native\jni/OpenCV.mk

# Linker
LOCAL_LDLIBS += -llog

# Our module sources
LOCAL_MODULE    := PanoHDR
LOCAL_SRC_FILES := PanoHDR.cpp Panorama.cpp HDR.cpp NativeLogging.cpp

include $(BUILD_SHARED_LIBRARY)
