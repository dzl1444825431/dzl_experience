#ifndef _CALC_SERVICE_H
#define _CALC_SERVICE_H

#include <utils/Log.h>
#include "ICalcService.h"

namespace android {
    /**********************************************/
    /*! @class ICalcService
        @brief Calc Service Proxy Interface class
    ***********************************************/
    class BnCalcService : public BnInterface<ICalcService> {
        public:
            virtual status_t onTransact(uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags);
    };
    class CalcService : public BnCalcService {
        public:
            CalcService();
            ~CalcService();
            virtual int32_t sum(int32_t x, int32_t y);
            virtual int32_t minus(int32_t x, int32_t y);
            static void instantiate();
    };
}
#endif
