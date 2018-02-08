//
//  QiniuManager.m
//  RNQiniu
//
//  Created by RockyTsui on 2018/2/8.
//  Copyright © 2018年 RockyTsui. All rights reserved.
//

#import "QiniuManager.h"
#import "QiniuSDK.h"

@interface QiniuManagerSingleton : NSObject<NSCopying>
+ (instancetype) shared;
@end

static QiniuManagerSingleton *_shared = nil;

@interface QiniuManagerSingleton()

@property (nonatomic, strong) QNUploadManager *upManager;

@end

@implementation QiniuManagerSingleton
    
- (instancetype) init {
    if (self = [super init]) {
        self.upManager = [[QNUploadManager alloc] init];
    }
    
    return self;
}
    
+ (instancetype) shared {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^ {
        _shared = [[super allocWithZone: nil] init];
    });
    
    return _shared;
}

+ (instancetype) allocWithZone:(struct _NSZone *)zone {
    return [QiniuManagerSingleton shared];
}
    
- (instancetype) copyWithZone:(NSZone *)zone {
    return [QiniuManagerSingleton shared];
}
    
@end

@implementation QiniuManager
    
RCT_EXPORT_MODULE()
    
RCT_EXPORT_METHOD(uploadFile: (NSDictionary*)params
                  resolver: (RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject) {
    
}
    
@end
