LOCAL_PATH:= $(call my-dir) 
include $(CLEAR_VARS) 
LOCAL_SRC_FILES:= com_dzl_ketchapp_stickhero_InstallApk.h
LOCAL_C_INCLUDES := \ $(JNI_H_INCLUDE) 
LOCAL_SHARED_LIBRARIES := libutils 
LOCAL_PRELINK_MODULE := false 
LOCAL_MODULE := libJNITest 
include $(BUILD_SHARED_LIBRARY) 