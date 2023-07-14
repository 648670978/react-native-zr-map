//
//  RNMapMarkerManager.m
//  example
//
//  Created by x1 on 2023/7/10.
//

#import "RNMapMarkerManager.h"



@implementation MapAnnotation



@end


@implementation MapMarker

-(MapAnnotation *)annotation {
    if (!_annotation) {
        _annotation = [[MapAnnotation alloc] init];
        _annotation.marker = self;
    }
    return _annotation;
}

-(MAPinAnnotationView *)view {
  if (!_view) {
    _view = [[MAPinAnnotationView alloc] initWithAnnotation:self.annotation reuseIdentifier:nil];
    _view.canShowCallout= YES;
  }
  return _view;
}

- (void)setCoordinate:(CLLocationCoordinate2D)coordinate {
  self.annotation.coordinate = coordinate;
}                   

- (void)setTitle:(NSString *)title {
    self.annotation.title = title;
}

- (void)setSubtitle:(NSString *)subtitle {
    self.annotation.subtitle = subtitle;
}

@end

@interface RNMapMarkerManager ()
@property (nonatomic,strong) MAPointAnnotation * annotation;

@end

@implementation RNMapMarkerManager

RCT_EXPORT_MODULE(ZRMapMarker)

RCT_EXPORT_VIEW_PROPERTY(coordinate,CLLocationCoordinate2D)
RCT_EXPORT_VIEW_PROPERTY(title,NSString)
RCT_EXPORT_VIEW_PROPERTY(subtitle,NSString)

+ (BOOL)requiresMainQueueSetup
{
  return YES;  // only do this if your module initialization relies on calling UIKit!
}


- (UIView *)view {
  MapMarker * view = [[MapMarker alloc] init];
  return view;
}



@end

