LOCAL_PATH:= $(call my-dir)  
include $(CLEAR_VARS)  
OPENCV_INSTALL_MODULES:=on  
OPENCV_CAMERA_MODULES:=off  
include F:\adt-bundle-windows-x86_64-20140702\OpenCV-2.4.9-android-sdk\sdk\native\jni/OpenCV.mk  
  
LOCAL_C_INCLUDES:= F:\adt-bundle-windows-x86_64-20140702\OpenCV-2.4.9-android-sdk\sdk\native\jni\include  
LOCAL_MODULE    := nonfree  
LOCAL_CFLAGS    := -Werror -O3 -ffast-math  
LOCAL_LDLIBS    += -llog  
LOCAL_SRC_FILES := nonfree_init.cpp \  
 precomp.cpp \  
 sift.cpp \  
 surf.cpp  
include $(BUILD_SHARED_LIBRARY)  