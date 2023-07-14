
#import "RNZrMap.h"
#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>
#import <AMapSearchKit/AMapSearchKit.h>

@interface RNZrMap () <AMapSearchDelegate>
@property (nonatomic,strong) AMapSearchAPI * search;
@property (nonatomic,copy) RCTResponseSenderBlock  callback;
@end

@implementation RNZrMap

- (AMapSearchAPI *)search {
    if (!_search) {
        _search = [[AMapSearchAPI alloc] init];
        _search.delegate = self;
    }
    return _search;
}

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
    [AMapSearchAPI updatePrivacyShow:(AMapPrivacyShowStatusDidShow) privacyInfo:(AMapPrivacyInfoStatusDidContain)];
    [AMapSearchAPI updatePrivacyAgree:AMapPrivacyAgreeStatusDidAgree];
    
    [AMapServices sharedServices].apiKey = key;
    [AMapServices sharedServices].enableHTTPS = YES;

}

RCT_EXPORT_METHOD(geocodeSeatch:(NSString *)str callback:(RCTResponseSenderBlock)callback) {
    self.callback = callback;
    AMapGeocodeSearchRequest *geo = [[AMapGeocodeSearchRequest alloc] init];
    geo.address = str;
    [self.search AMapGeocodeSearch:geo];
}


- (void)onGeocodeSearchDone:(AMapGeocodeSearchRequest *)request response:(AMapGeocodeSearchResponse *)response
{
    if (response.geocodes.count == 0)
    {
        if (self.callback) {
            self.callback(@[[NSNull null]]);
            self.callback = nil;
        }
        return;
    }
    if (self.callback) {
        AMapGeocode * ge = response.geocodes[0];
        AMapGeoPoint * loc = ge.location;
        NSDictionary * dic = @{@"latitude":@(loc.latitude),@"longitude":@(loc.longitude)};
        self.callback(@[dic]);
        self.callback = nil;
    }
}

@end
  
