//
//  RNMapViewManager.m
//  example
//
//  Created by zr on 2023/7/6.
//

#import "RNMapViewManager.h"
#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>


@implementation RNMapViewManager

RCT_EXPORT_MODULE(RNZrMapViewMan)

- (UIView *)view
{
  return [[MAMapView alloc] init];
}


@end
