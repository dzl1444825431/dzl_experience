#define TAG "CalcService"
#include "CalcService.h"
#include <utils/Log.h>

namespace android {
class BpCalcService : public BpInterface<ICalcService> {
    public:
    BpCalcService(const sp<IBinder>& impl) : BpInterface<ICalcService>(impl)
    {
    }
    virtual int32_t sum(int32_t x, int32_t y)
    {
         LOGD("BpCalcService sum.");
         Parcel data, reply;
         data.writeInterfaceToken(ICalcService::getInterfaceDescriptor());
         data.writeInt32(x);
         data.writeInt32(y);
         remote()->transact(CALC_SUM,data,&reply);
         int32_t sumxy = reply.readInt32();
         LOGD("sumxy=%d",sumxy);
         return sumxy;
    }
    virtual int32_t minus(int32_t x, int32_t y)
    {
         LOGD("BpCalcService sum.");
         Parcel data, reply;
         data.writeInterfaceToken(ICalcService::getInterfaceDescriptor());
         data.writeInt32(x);
         data.writeInt32(y);
         remote()->transact(CALC_MINUS,data,&reply);
         int32_t mxy = reply.readInt32();
         LOGD("minuxsy=%d",mxy);
         return mxy;
    }

};

IMPLEMENT_META_INTERFACE(CalcService,"com.test.ICalcService");

status_t BnCalcService::onTransact(uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags)
{
    LOGD("onTransact received request.");
    reply->writeInt32(0);
    switch(code)
    {
        case CALC_SUM:
        {
            LOGD("calc sum");
            CHECK_INTERFACE(ICalcService,data,reply);
            int32_t x = data.readInt32();
            int32_t y = data.readInt32();
            int32_t sumxy = sum(x,y);
            reply->writeInt32(sumxy);
            return NO_ERROR;
        }
        case CALC_MINUS:
        {
            LOGD("calc minus");
            CHECK_INTERFACE(ICalcService,data,reply);
            int32_t x = data.readInt32();
            int32_t y = data.readInt32();
            int32_t minusxy = minus(x,y);
            reply->writeInt32(minusxy);
            return NO_ERROR;
        }
        default:{
            return BBinder::onTransact(code,data,reply,flags);
        }
    }
}
CalcService::CalcService()
{
    LOGD("constructor of CalcService");
}
CalcService::~CalcService()
{
    LOGD("destructor of CalcService");
}
int32_t CalcService::sum(int32_t x, int32_t y)
{
    return (x+y);
}
int32_t CalcService::minus(int32_t x, int32_t y)
{
    return (x-y);
}
void CalcService::instantiate()
{
    LOGD("CalcService instantiate.");
    LOGD("CalcService:ServiceManager: start\n");
    defaultServiceManager()->addService(String16("calcservice"),new android::CalcService());
}
}
