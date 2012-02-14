//
//  Junk.h
//  JunkBird
//
//  Created by Matt Weaver on 2/13/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

extern NSString* MY_MESSAGES;

@interface Junk : NSObject

@property (nonatomic, copy) NSString* name;
@property (nonatomic, retain) NSMutableDictionary* myDatums;

- (void) printMyDatums;

+ (void) notifyMeAsynchronouslyWithMessage: (NSString*) message;

@end
