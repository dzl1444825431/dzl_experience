LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

ifeq ($(TARGET_BUILD_TYPE),debug)
LOCAL_CFLAGS += -DDEBUG
endif

LOCAL_PRELINK_MODULE := false

#Binder Proxy
LOCAL_MODULE := libCalcService
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := CalcService.cpp 

LOCAL_SHARED_LIBRARIES := libbinder libutils

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

# calc
LOCAL_MODULE := calc
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := Calc.cpp 

LOCAL_PRELINK_MODULE := false
LOCAL_SHARED_LIBRARIES := libbinder libutils libCalcService


include $(BUILD_EXECUTABLE)