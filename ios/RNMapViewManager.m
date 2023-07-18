//
//  RNMapViewManager.m
//  example
//
//  Created by zr on 2023/7/6.
//

#import "RNMapViewManager.h"
#import <React/RCTUIManager.h>

#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>
#import "RNMapMarkerManager.h"


@interface MapView : UIView <MAMapViewDelegate>

@property(nonatomic,strong) MAMapView * map;
@property(nonatomic,assign) CLLocationCoordinate2D initialCoordinate;
@property(nonatomic,assign) int mapType;
@property(nonatomic,assign) BOOL zoomEnabled;
@property(nonatomic,assign) BOOL scrollEnabled;
@property(nonatomic,assign) BOOL rotateEnabled;

@end

@implementation MapView

-(instancetype)init {
    self = [super init];
    _map = [[MAMapView alloc] init];
    _map.delegate = self;
    [self addSubview:_map];
    return self;
}

-(void)didAddSubview:(UIView *)subview {
    [super didAddSubview:subview];
    NSLog(@"didAddSubview %@",subview);
    if ([subview isKindOfClass:[MapMarker class]]) {
        MapMarker * mar = (MapMarker *) subview;
        [_map addAnnotation:mar.annotation];
    }
}

-(void)removeReactSubview:(UIView *)subview {
    [super removeReactSubview:subview];
    NSLog(@"removeReactSubview %@",subview);
    if ([subview isKindOfClass:[MapMarker class]]) {
        MapMarker * mar = (MapMarker *) subview;
        [_map removeAnnotation:mar.annotation];
    }
}

- (MAAnnotationView *)mapView:(MAMapView *)mapView viewForAnnotation:(id <MAAnnotation>)annotation {
    
    if ([annotation isKindOfClass:[MapAnnotation class]]) {
        MapAnnotation * an = (MapAnnotation *) annotation;
        MAAnnotationView * view = an.marker.view;
        NSLog(@"viewForAnnotation %@",view);
        return view;
    }
    
    return nil;
}

- (void)setInitialCoordinate:(CLLocationCoordinate2D)coordinate {
      [_map setCenterCoordinate:coordinate];
}
-(void)setMapType:(int)mapType {
    [_map setMapType:mapType];
}
-(void)setZoomEnabled:(BOOL)zoomEnabled {
    [_map setZoomEnabled:zoomEnabled];
}
-(void)setScrollEnabled:(BOOL)scrollEnabled {
    [_map setScrollEnabled:scrollEnabled];
}
-(void)setRotateEnabled:(BOOL)rotateEnabled {
    [_map setRotateEnabled:rotateEnabled];
}


-(void)layoutSubviews {
    [super layoutSubviews];
    _map.frame = CGRectMake(0, 0, self.frame.size.width, self.frame.size.height);
}

@end


@implementation RNMapViewManager

RCT_EXPORT_MODULE(ZRMapView)


RCT_EXPORT_VIEW_PROPERTY(initialCoordinate,CLLocationCoordinate2D)
RCT_EXPORT_VIEW_PROPERTY(mapType,int)
RCT_EXPORT_VIEW_PROPERTY(zoomEnabled,BOOL)
RCT_EXPORT_VIEW_PROPERTY(scrollEnabled,BOOL)
RCT_EXPORT_VIEW_PROPERTY(rotateEnabled,BOOL)


RCT_EXPORT_METHOD(setCameraPosition:(nonnull NSNumber *)reactTag position:(CLLocationCoordinate2D)position) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *,UIView *> *viewRegistry) {
        MapView *view = (MapView *) viewRegistry[reactTag];
        [view.map setCenterCoordinate:position animated:YES];
    }];
}

- (UIView *)view
{
    MapView * map = [[MapView alloc] init];
    return map;
}





@end



