//
//  QiniuManager.h
//  RNQiniu
//
//  Created by RockyTsui on 2018/2/8.
//  Copyright © 2018年 RockyTsui. All rights reserved.
//
#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#else // Compatibility for RN version < 0.40
#import "RCTBridgeModule.h"
#endif
#if __has_include(<React/RCTLog.h>)
#import <React/RCTLog.h>
#else // Compatibility for RN version < 0.40
#import "RCTLog.h"
#endif
#import <Foundation/Foundation.h>

@interface QiniuManager : NSObject<RCTBridgeModule>

@end
