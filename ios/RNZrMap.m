
#import "RNZrMap.h"
#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>

@implementation RNZrMap

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

- (NSArray<NSString *> *)supportedEvents {
    return @[];
}

RCT_EXPORT_MODULE(RNZrMap)

RCT_EXPORT_METHOD(initMap:(NSString *)key) {
    [MAMapView updatePrivacyShow:AMapPrivacyShowStatusDidShow privacyInfo:AMapPrivacyInfoStatusDidContain];
    [MAMapView updatePrivacyAgree:AMapPrivacyAgreeStatusDidAgree];
    [AMapServices sharedServices].apiKey = key;
    [AMapServices sharedServices].enableHTTPS = YES;
}

@end
  
