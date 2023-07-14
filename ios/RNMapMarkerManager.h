//
//  RNMapMarkerManager.h
//  example
//
//  Created by x1 on 2023/7/10.
//

#import <React/RCTViewManager.h>
#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>

NS_ASSUME_NONNULL_BEGIN
@class MapMarker;
@interface MapAnnotation : MAPointAnnotation

@property (nonatomic,weak) MapMarker *marker;

@end


@interface RNMapMarkerManager : RCTViewManager

@end


@interface MapMarker : UIView

@property (nonatomic,strong) MapAnnotation * annotation;
@property (nonatomic,strong) MAPinAnnotationView * view;

@end

NS_ASSUME_NONNULL_END
