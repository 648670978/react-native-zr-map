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


@interface MapView : MAMapView <MAMapViewDelegate>

@end

@implementation MapView


-(void)didAddSubview:(UIView *)subview {
    [super didAddSubview:subview];
    NSLog(@"didAddSubview %@",subview);
    if ([subview isKindOfClass:[MapMarker class]]) {
        MapMarker * mar = (MapMarker *) subview;
        [self addAnnotation:mar.annotation];
    }
    
    
}

-(void)removeReactSubview:(UIView *)subview {
    [super removeReactSubview:subview];
    NSLog(@"removeReactSubview %@",subview);
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

@end


@implementation RNMapViewManager

RCT_EXPORT_MODULE(ZRMapView)

RCT_EXPORT_METHOD(setCameraPosition:(nonnull NSNumber *)reactTag position:(CLLocationCoordinate2D)position) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *,UIView *> *viewRegistry) {
        MapView *view = (MapView *) viewRegistry[reactTag];
        [view setCenterCoordinate:position animated:YES];
    }];
}

- (UIView *)view
{
    MapView * map = [[MapView alloc] init];
    map.delegate = map;
    return map;
}





@end



